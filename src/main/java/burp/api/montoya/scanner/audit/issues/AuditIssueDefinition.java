/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner.audit.issues;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to retrieve background information about audit
 * issues. Note that text generated by extensions is subject to an HTML
 * whitelist that allows only formatting tags and simple hyperlinks.
 */
public interface AuditIssueDefinition
{

    /**
     * Name of this issue type.
     *
     * @return The name of this issue type (e.g. "SQL injection").
     */
    String name();

    /**
     * This method returns a background description for this issue type.
     *
     * @return A background description for this type of issue, or {@code null}
     * if none applies. A limited set of HTML tags may be used.
     */
    String background();

    /**
     * This method returns a background description of the remediation for this
     * type of issue.
     *
     * @return A background description of the remediation for this type of
     * issue, or {@code null} if none applies. A limited set of HTML tags may
     * be used.
     */
    String remediation();

    /**
     * Typical issue severity level.
     *
     * @return The typical {@link AuditIssueSeverity} level.
     */
    AuditIssueSeverity typicalSeverity();

    /**
     * This method returns an index of the issue type. See the Burp Scanner
     * documentation for a listing of all the issue types.
     *
     * @return An index of the issue type.
     */
    int typeIndex();

    /**
     * This method can be used to create a default implementation of an audit
     * issue definition.
     *
     * @param name            The name of the issue type.
     * @param background      The background description for the type of issue.
     * @param remediation     The background description of the remediation for
     *                        this type of issue.
     * @param typicalSeverity The typical {@link AuditIssueSeverity} level.
     *
     * @return The audit issue definition.
     */
    static AuditIssueDefinition auditIssueDefinition(String name, String background, String remediation, AuditIssueSeverity typicalSeverity)
    {
        return FACTORY.auditIssueDefinition(name, background, remediation, typicalSeverity);
    }
}
