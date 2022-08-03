/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message;

import burp.api.montoya.core.MessageAnnotations;
import burp.api.montoya.core.Range;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.List;

/**
 * This interface is used to define a coupling between {@link HttpRequest} and {@link HttpResponse} interfaces.
 */
public interface HttpRequestResponse
{
    /**
     * @return The HTTP request message.
     */
    HttpRequest httpRequest();

    /**
     * @return The HTTP response message.
     */
    HttpResponse httpResponse();

    /**
     * @return The message annotations.
     */
    MessageAnnotations messageAnnotations();

    /**
     * This is a helper method used to add message annotations to the {@code HttpRequestResponse} instance.
     *
     * @param messageAnnotations Message annotations to add.
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withMessageAnnotations(MessageAnnotations messageAnnotations);

    /**
     * This is a helper method used to add markers to the {@code HttpRequestResponse} instance.
     *
     * @param requestMarkers Request markers to add.
     * @param responseMarkers Response markers to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    MarkedHttpRequestResponse withMarkers(List<Range> requestMarkers, List<Range> responseMarkers);

    /**
     * This is a helper method used to add request markers to the {@code HttpRequestResponse} instance.
     *
     * @param requestMarkers Request markers to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    MarkedHttpRequestResponse withRequestMarkers(List<Range> requestMarkers);

    /**
     * This is a helper method used to add request markers to the {@code HttpRequestResponse} instance.
     *
     * @param requestMarkers Request markers to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    MarkedHttpRequestResponse withRequestMarkers(Range... requestMarkers);

    /**
     * This is a helper method used to add response markers to the {@code HttpRequestResponse} instance.
     *
     * @param responseMarkers Response markers to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    MarkedHttpRequestResponse withResponseMarkers(List<Range> responseMarkers);

    /**
     * This is a helper method used to add response markers to the {@code HttpRequestResponse} instance.
     *
     * @param responseMarkers Response markers to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    MarkedHttpRequestResponse withResponseMarkers(Range... responseMarkers);

    /**
     * This is a helper method used to build a {@code MarkedHttpRequestResponse} instance with no markers.
     *
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    MarkedHttpRequestResponse withNoMarkers();
}
