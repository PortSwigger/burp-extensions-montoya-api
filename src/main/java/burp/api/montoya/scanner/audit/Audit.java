/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner.audit;

import burp.api.montoya.scanner.ScanTask;
import burp.api.montoya.scanner.audit.issues.AuditIssue;

import java.util.List;

/**
 * This interface represents an instance of an audit in the Burp Scanner tool.
 */
public interface Audit extends ScanTask
{
    /**
     * This method retrieves the number of insertion points.
     *
     * @return The number of insertion points.
     */
    int insertionPointCount();

    /**
     * This method retrieves the audit issues found by this audit.
     *
     * @return The list of {@link AuditIssue}s found by this audit.
     */
    List<AuditIssue> issues();
}
