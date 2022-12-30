/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner.audit.issues;

import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.scanner.ScanCheck;
import burp.api.montoya.scanner.audit.AuditIssueHandler;
import burp.api.montoya.sitemap.SiteMap;

import java.util.Arrays;
import java.util.List;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to retrieve details of audit issues. Extensions can
 * obtain details of issues by registering an {@link AuditIssueHandler}.
 * Extensions can also add custom audit issues by registering an
 * {@link ScanCheck} or calling {@link SiteMap#add(AuditIssue)},
 * and providing their own implementations of this interface. Note that issue
 * descriptions and other text generated by extensions are subject to an HTML
 * whitelist that allows only formatting tags and simple hyperlinks.
 */
public interface AuditIssue
{
    /**
     * This method returns the name of this issue type.
     *
     * @return The name of this issue type (e.g. "SQL injection").
     */
    String name();

    /**
     * This method returns detailed information about this specific instance of
     * the issue.
     *
     * @return Detailed information about this specific instance of the issue,
     * or {@code null} if none applies. A limited set of HTML tags may be used.
     */
    String detail();

    /**
     * This method returns detailed information about the remediation for this
     * specific instance of the issue.
     *
     * @return Detailed information about the remediation for this specific
     * instance of the issue, or {@code null} if none applies. A limited set of
     * HTML tags may be used.
     */
    String remediation();

    /**
     * This method returns the HTTP service for which the issue was generated.
     *
     * @return The HTTP service for which the issue was generated.
     */
    HttpService httpService();

    /**
     * This method returns the base URL for which this issue was generated.
     *
     * @return The base URL for which this issue was generated.
     */
    String baseUrl();

    /**
     * This method returns the issue severity level.
     *
     * @return The {@link AuditIssueSeverity} level.
     */
    AuditIssueSeverity severity();

    /**
     * This method returns the issue confidence level.
     *
     * @return The {@link AuditIssueConfidence} level.
     */
    AuditIssueConfidence confidence();

    /**
     * This method returns the HTTP request/response messages that caused the issue to be generated.
     *
     * @return The list of {@link HttpRequestResponse} objects on the basis of
     * which the issue was generated.
     */
    List<HttpRequestResponse> requestResponses();

    /**
     * This method returns the definition for this issue.
     *
     * @return The {@link AuditIssueDefinition} for this issue.
     */
    AuditIssueDefinition definition();

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
     *
     * @return The audit issue for the URL.
     */
    static AuditIssue auditIssue(
            String name,
            String detail,
            String remediation,
            String baseUrl,
            AuditIssueSeverity severity,
            AuditIssueConfidence confidence,
            String background,
            String remediationBackground,
            AuditIssueSeverity typicalSeverity,
            HttpRequestResponse... requestResponses)
    {
        return auditIssue(name, detail, remediation, baseUrl, severity, confidence, background, remediationBackground, typicalSeverity, Arrays.asList(requestResponses));
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
     *
     * @return The audit issue for the URL.
     */
    static AuditIssue auditIssue(
            String name,
            String detail,
            String remediation,
            String baseUrl,
            AuditIssueSeverity severity,
            AuditIssueConfidence confidence,
            String background,
            String remediationBackground,
            AuditIssueSeverity typicalSeverity,
            List<HttpRequestResponse> requestResponses)
    {
        return FACTORY.auditIssue(name, detail, remediation, baseUrl, severity, confidence, background, remediationBackground, typicalSeverity, requestResponses);
    }
}
