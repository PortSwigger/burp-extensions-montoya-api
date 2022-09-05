/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner.audit.insertionpoint;

import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.scanner.ScanCheck;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to define an insertion point for use by active Scan
 * checks. Extensions can obtain instances of this interface by registering an
 * {@link ScanCheck}, or can create instances for use by Burp's own scan checks
 * by registering an {@link AuditInsertionPointProvider}.
 */
public interface AuditInsertionPoint extends ExtensionGeneratedAuditInsertionPoint
{

    /**
     * This method can be used to create an audit insertion point based on
     * offsets.
     *
     * @param name                The name of the audit insertion point.
     * @param baseRequest         The base {@link HttpRequest}.
     * @param startIndexInclusive The start index inclusive.
     * @param endIndexExclusive   The end index exclusive.
     * @return The {@link AuditInsertionPoint} based on offsets.
     */
    static AuditInsertionPoint auditInsertionPoint(String name, HttpRequest baseRequest, int startIndexInclusive, int endIndexExclusive)
    {
        return FACTORY.auditInsertionPoint(name, baseRequest, startIndexInclusive, endIndexExclusive);
    }

    /**
     * This method returns the type of this insertion point.
     *
     * @return The {@link AuditInsertionPointType} for this insertion point.
     */
    AuditInsertionPointType type();
}
