/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner;

import burp.api.montoya.core.Registration;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.MarkedHttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.scanner.audit.AuditIssueHandler;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPointProvider;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.scanner.audit.issues.AuditIssueConfidence;
import burp.api.montoya.scanner.audit.issues.AuditIssueDefinition;
import burp.api.montoya.scanner.audit.issues.AuditIssueSeverity;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * This interface provides access to the functionality of the Scanner tool.
 */
public interface Scanner
{
    /**
     * This method is used to register a handler which will be notified of new
     * audit issues that are reported by the Scanner tool. Extensions can
     * perform custom analysis or logging of audit issues by registering an
     * audit issue handler.
     *
     * @param auditIssueHandler An object created by the extension that
     * implements the {@link AuditIssueHandler} interface.
     * @return The {@link Registration} for the handler.
     */
    Registration registerAuditIssueHandler(AuditIssueHandler auditIssueHandler);

    /**
     * This method is used to register a custom Scanner check. When performing
     * scanning, Burp will ask the check to perform active or passive scanning
     * on the base request, and report any Scanner issues that are identified.
     *
     * @param scanCheck An object created by the extension that implements the
     * {@link ScanCheck} interface.
     * @return The {@link Registration} for the check.
     */
    Registration registerScanCheck(ScanCheck scanCheck);

    /**
     * This method is used to register a provider of Scanner insertion points.
     * For each base request that is actively scanned, Burp will ask the
     * provider to provide any custom Scanner insertion points that are
     * appropriate for the request.
     *
     * @param insertionPointProvider An object created by the extension that
     * implements the {@link AuditInsertionPointProvider} interface.
     * @return The {@link Registration} for the provider.
     */
    Registration registerInsertionPointProvider(AuditInsertionPointProvider insertionPointProvider);

    /**
     * This method can be used to create a scan that can be configured before
     * being sent to the Burp Scanner tool.
     *
     * @return The {@link Scan} that can be configured.
     */
    Scan createScan();

    /**
     * This method can be used to create an audit insertion point based on
     * offsets.
     *
     * @param name The name of the audit insertion point.
     * @param baseRequest The base {@link HttpRequest}.
     * @param startIndexInclusive The start index inclusive.
     * @param endIndexExclusive The end index exclusive.
     * @return The {@link AuditInsertionPoint} based on offsets.
     */
    AuditInsertionPoint createOffsetBasedInsertionPoint(String name, HttpRequest baseRequest, int startIndexInclusive, int endIndexExclusive);

    /**
     * This method is used to generate a report for the specified Scanner
     * issues. The report format can be specified. For all other reporting
     * options, the default settings that appear in the reporting UI wizard are
     * used.
     *
     * @param issues The {@link AuditIssue}s issues to be reported.
     * @param format The {@link ReportFormat} to be used in the report.
     * @param path The {@link Path} to the file that will be saved.
     */
    void generateReport(List<AuditIssue> issues, ReportFormat format, Path path);

    /**
     * This method can be used to create a default implementation of an audit
     * issue for a URL.
     *
     * @param name                  The name of the issue type.
     * @param detail                The detailed information about the issue.
     * @param remediation           The detailed information about the remediation for
     *                              the issue.
     * @param baseUrl               The base URL for which the issue is generated.
     * @param severity              The {@link AuditIssueSeverity} level.
     * @param confidence            The {@link AuditIssueConfidence} level.
     * @param background            The background description for the type of issue.
     * @param remediationBackground The background description of the
     *                              remediation for this type of issue.
     * @param typicalSeverity       The typical {@link AuditIssueSeverity} level.
     * @param requestResponses      The {@link HttpRequestResponse} objects on the
     *                              basis of which the issue is generated.
     * @return The audit issue for the URL.
     */
    default AuditIssue createAuditIssue(
            String name,
            String detail,
            String remediation,
            String baseUrl,
            AuditIssueSeverity severity,
            AuditIssueConfidence confidence,
            String background,
            String remediationBackground,
            AuditIssueSeverity typicalSeverity,
            MarkedHttpRequestResponse... requestResponses)
    {
        return createAuditIssue(name, detail, remediation, baseUrl, severity, confidence, background, remediationBackground, typicalSeverity, Arrays.asList(requestResponses));
    }

    /**
     * This method can be used to create a default implementation of an audit
     * issue for a URL.
     *
     * @param name                  The name of the issue type.
     * @param detail                The detailed information about the issue.
     * @param remediation           The detailed information about the remediation for
     *                              the issue.
     * @param baseUrl               The base URL for which the issue is generated.
     * @param severity              The {@link AuditIssueSeverity} level.
     * @param confidence            The {@link AuditIssueConfidence} level.
     * @param background            The background description for the type of issue.
     * @param remediationBackground The background description of the
     *                              remediation for this type of issue.
     * @param typicalSeverity       The typical {@link AuditIssueSeverity} level.
     * @param requestResponses      The list of {@link HttpRequestResponse} objects
     *                              on the basis of which the issue is generated.
     * @return The audit issue for the URL.
     */
    AuditIssue createAuditIssue(
            String name,
            String detail,
            String remediation,
            String baseUrl,
            AuditIssueSeverity severity,
            AuditIssueConfidence confidence,
            String background,
            String remediationBackground,
            AuditIssueSeverity typicalSeverity,
            List<MarkedHttpRequestResponse> requestResponses);

    /**
     * This method can be used to create a default implementation of an audit
     * issue definition.
     *
     * @param name            The name of the issue type.
     * @param background      The background description for the type of issue.
     * @param remediation     The background description of the remediation for
     *                        this type of issue.
     * @param typicalSeverity The typical {@link AuditIssueSeverity} level.
     * @return The audit issue definition.
     */
    AuditIssueDefinition createAuditIssueDefinition(String name, String background, String remediation, AuditIssueSeverity typicalSeverity);
}
