/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message;

import burp.api.montoya.core.Range;

import java.util.List;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to associate request and responses with their markers.
 */
public interface MarkedHttpRequestResponse
{
    /**
     * This is a helper method to create a new instance of {@link MarkedHttpRequestResponse} that does not contain any markers.
     *
     * @param httpRequestResponse The HTTP request response.
     * @return A new {@link MarkedHttpRequestResponse} instance.
     */
    static MarkedHttpRequestResponse unmarkedRequestResponse(HttpRequestResponse httpRequestResponse)
    {
        return FACTORY.markedRequestResponse(httpRequestResponse);
    }

    /**
     * This is a helper method to create a new instance of {@link MarkedHttpRequestResponse} with markers.
     *
     * @param httpRequestResponse The HTTP request response.
     * @param requestMarkers A list of markers indicating interesting information within the HTTP request.
     * @param responseMarkers A list of markers indicating interesting information within the HTTP response.
     * @return A new {@link MarkedHttpRequestResponse} instance.
     */
    static MarkedHttpRequestResponse markedRequestResponse(HttpRequestResponse httpRequestResponse, List<Range> requestMarkers, List<Range> responseMarkers)
    {
        return FACTORY.markedRequestResponse(httpRequestResponse, requestMarkers, responseMarkers);
    }

    /**
     * @return The HTTP request/response which is associated with the markers.
     */
    HttpRequestResponse httpRequestResponse();

    /**
     * @return List of request markers
     */
    List<Range> requestMarkers();

    /**
     * @return List of response markers
     */
    List<Range> responseMarkers();
}
