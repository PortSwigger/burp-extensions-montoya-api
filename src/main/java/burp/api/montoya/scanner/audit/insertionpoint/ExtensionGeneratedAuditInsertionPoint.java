/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner.audit.insertionpoint;

import burp.api.montoya.core.Range;

import java.util.List;

/**
 * This interface is used to define an insertion point for use by Scan checks.
 * Extensions can create instances for use by Burp's own scan checks by
 * registering an {@link AuditInsertionPointProvider}.
 */
public interface ExtensionGeneratedAuditInsertionPoint
{
    /**
     * This method returns the name of this insertion point.
     *
     * @return The name of this insertion point (for example, a description of
     * a particular request parameter).
     */
    String name();

    /**
     * This method returns the base value for this insertion point.
     *
     * @return the base value that appears in this insertion point in the base
     * request being audited, or {@code null} if there is no value in the base
     * request that corresponds to this insertion point.
     */
    String baseValue();

    /**
     * This method is used to build a request with the specified payload placed
     * into the insertion point. There is no requirement for extension-provided
     * insertion points to adjust the Content-Length header in requests if the
     * body length has changed, although Burp-provided insertion points will
     * always do this and will return a request with a valid Content-Length
     * header.
     * <b>Note:</b>
     * Scan checks should submit raw non-encoded payloads to insertion points,
     * and the insertion point has responsibility for performing any data
     * encoding that is necessary given the nature and location of the insertion
     * point.
     *
     * @param payload The payload that should be placed into the insertion
     *                point.
     * @return The resulting request.
     */
    byte[] buildHttpMessageWithPayload(byte[] payload);

    /**
     * This method is used to determine the offsets of the payload value within
     * the request, when it is placed into the insertion point. Scan checks may
     * invoke this method when reporting issues, so as to highlight the
     * relevant part of the request within the UI.
     *
     * @param payload The payload that should be placed into the insertion
     *                point.
     * @return A list of {@link Range} objects containing the start and end
     * offsets of the payload within the request, or an empty list if this is
     * not applicable (for example, where the insertion point places a payload
     * into a serialized data structure, the raw payload may not literally
     * appear anywhere within the resulting request).
     */
    List<Range> issueHighlights(byte[] payload);
}
