package burp.api.montoya.scanner.scancheck;

import burp.api.montoya.http.Http;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.scanner.AuditResult;
import burp.api.montoya.scanner.ConsolidationAction;
import burp.api.montoya.scanner.Scanner;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint;
import burp.api.montoya.scanner.audit.issues.AuditIssue;

import static burp.api.montoya.scanner.ConsolidationAction.KEEP_BOTH;

/**
 * Extensions can implement this interface, then call
 * {@link Scanner#registerActiveScanCheck(ActiveScanCheck, ScanCheckType)} to register a custom active scan
 * check. During an audit, Burp invokes the check to perform an
 * active audit on the base request according to the specified {@link ScanCheckType}. Burp reports any audit issues
 * that are identified.
 */
public interface ActiveScanCheck
{
    /**
     * The name Burp will use to identify this scan check.
     *
     * @return Name of the scan check.
     */
    String checkName();

    /**
     * The Scanner invokes this method according to the registered {@link ScanCheckType}.
     *
     * Use the {@link Http} object to issue HTTP requests during an active audit.
     * Use the {@link AuditInsertionPoint} object to build requests with specific payloads.
     *
     * <b>Note:</b>
     * Scan checks should submit non-encoded payloads to insertion points.
     * The insertion point should handle any necessary encoding based on its type and location.
     *
     * @param baseRequestResponse The base {@link HttpRequestResponse} that
     *                            should be actively audited.
     *
     * @param insertionPoint An {@link AuditInsertionPoint} object.
     *                       This can be queried to obtain details of the insertion point being tested.
     *                       It can also be used to build requests for particular payloads.
     *                       <b>Note:</b> This object is only available if the scan check is registered with the {@link ScanCheckType#PER_INSERTION_POINT} type.
     *                       For other types, a {@link AuditInsertionPoint} object is returned, but it only contains placeholder data.
     *
     * @param http A {@link Http} object. This can be used to send and retrieve HTTP requests within the custom scan check.
     *             Any requests you send are automatically linked to the current scan task.
     *
     * @return An {@link AuditResult} object that contains a list of {@link AuditIssue}
     * objects, or an empty {@link AuditResult} object if no issues are identified.
     */
    AuditResult doCheck(HttpRequestResponse baseRequestResponse, AuditInsertionPoint insertionPoint, Http http);

    /**
     * The Scanner invokes this method when the custom Scan check has
     * reported multiple issues for the same URL path. This can arise either
     * because there are multiple distinct vulnerabilities, or because the same
     * (or a similar) request has been scanned more than once. The custom check
     * should determine whether the issues are duplicates. In most cases, where
     * a check uses distinct issue names or descriptions for distinct issues,
     * the consolidation process will simply be a matter of comparing these
     * features for the two issues.
     *
     * @param existingIssue An {@link AuditIssue} that was previously reported
     *                      by this Scan check.
     * @param newIssue      An {@link AuditIssue} at the same URL path that has been
     *                      newly reported by this Scan check.
     *
     * @return A {@link ConsolidationAction} to determine which issue(s) should
     * be reported in the main Scanner results.
     */
    default ConsolidationAction consolidateIssues(AuditIssue existingIssue, AuditIssue newIssue)
    {
        return KEEP_BOTH;
    }
}
