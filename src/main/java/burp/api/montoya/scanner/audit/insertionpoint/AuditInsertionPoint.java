/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner.audit.insertionpoint;

import burp.api.montoya.scanner.ScanCheck;

/**
 * This interface is used to define an insertion point for use by active Scan
 * checks. Extensions can obtain instances of this interface by registering an
 * {@link ScanCheck}, or can create instances for use by Burp's own scan checks
 * by registering an {@link AuditInsertionPointProvider}.
 */
public interface AuditInsertionPoint extends ExtensionGeneratedAuditInsertionPoint
{
    /**
     * This method returns the type of this insertion point.
     *
     * @return The {@link AuditInsertionPointType} for this insertion point.
     */
    AuditInsertionPointType type();
}
