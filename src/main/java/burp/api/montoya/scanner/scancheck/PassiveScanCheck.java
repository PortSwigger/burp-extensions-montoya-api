package burp.api.montoya.scanner.scancheck;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.scanner.AuditResult;
import burp.api.montoya.scanner.ConsolidationAction;
import burp.api.montoya.scanner.Scanner;
import burp.api.montoya.scanner.audit.issues.AuditIssue;

import static burp.api.montoya.scanner.ConsolidationAction.KEEP_BOTH;

/**
 * Extensions can implement this interface, then call
 * {@link Scanner#registerPassiveScanCheck(PassiveScanCheck, ScanCheckType)} to register a custom passive scan
 * check. During an audit, Burp invokes the check to perform a
 * passive audit on the base request according to the specified {@link ScanCheckType}. Burp reports any audit issues
 * that are identified.
 */
public interface PassiveScanCheck
{
    /**
     * The name Burp will use to identify this scan check.
     *
     * @return Name of the scan check.
     */
    String checkName();

    /**
     * The Scanner invokes this method at the registered {@link ScanCheckType}.
     * <b>Note:</b>
     * Extensions should only analyze the
     * HTTP messages provided during a passive audit, and should not make any
     * new HTTP requests of their own.
     *
     * @param baseRequestResponse The base {@link HttpRequestResponse} that
     *                            should be actively audited.

     * @return An {@link AuditResult} object with a list of {@link AuditIssue}
     * objects, or an empty {@link AuditResult} object if no issues are identified.
     */
    AuditResult doCheck(HttpRequestResponse baseRequestResponse);

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
