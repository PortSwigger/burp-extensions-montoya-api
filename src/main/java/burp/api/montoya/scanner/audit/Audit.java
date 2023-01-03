/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner.audit;

import burp.api.montoya.core.Range;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.scanner.ScanTask;
import burp.api.montoya.scanner.audit.issues.AuditIssue;

import java.util.List;

/**
 * Audit in the Burp Scanner tool.
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

    /**
     * This method can be used to add an HTTP request to this audit.
     *
     * @param request The {@link HttpRequest} to add to this audit.
     */
    void addRequest(HttpRequest request);

    /**
     * This method can be used to add an HTTP request to this audit.
     *
     * @param request               The {@link HttpRequest} to add to this audit.
     * @param insertionPointOffsets The list of {@link Range}s representing the
     *                              insertion point offsets.
     */
    void addRequest(HttpRequest request, List<Range> insertionPointOffsets);

    /**
     * This method can be used to add an HTTP request and response to this
     * audit.
     *
     * @param requestResponse The {@link HttpRequestResponse} to add to this
     *                        audit.
     */
    void addRequestResponse(HttpRequestResponse requestResponse);

    /**
     * {@inheritDoc}
     */
    @Override
    int requestCount();

    /**
     * {@inheritDoc}
     */
    @Override
    int errorCount();

    /**
     * {@inheritDoc}
     */
    @Override
    void delete();

    /**
     * {@inheritDoc}
     */
    @Override
    String statusMessage();
}
