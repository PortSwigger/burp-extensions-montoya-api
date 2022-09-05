/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.Range;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.List;

import static burp.api.montoya.core.Annotations.annotations;
import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to define a coupling between {@link HttpRequest} and {@link HttpResponse} interfaces.
 */
public interface HttpRequestResponse
{

    /**
     * This is a helper method to create a new instance of {@link HttpRequestResponse}.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A new {@link HttpRequestResponse} instance.
     */
    static HttpRequestResponse httpRequestResponse(HttpRequest request, HttpResponse response)
    {
        return httpRequestResponse(request, response, annotations());
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequestResponse}.
     *
     * @param httpRequest        The HTTP request.
     * @param httpResponse       The HTTP response.
     * @param annotations annotations.
     * @return A new {@link HttpRequestResponse} instance.
     */
    static HttpRequestResponse httpRequestResponse(HttpRequest httpRequest, HttpResponse httpResponse, Annotations annotations)
    {
        return FACTORY.httpRequestResponse(httpRequest, httpResponse, annotations);
    }

    /**
     * @return The HTTP request message.
     */
    HttpRequest httpRequest();

    /**
     * @return The HTTP response message.
     */
    HttpResponse httpResponse();

    /**
     * @return The annotations.
     */
    Annotations messageAnnotations();

    /**
     * This is a helper method used to add annotations to the {@code HttpRequestResponse} instance.
     *
     * @param annotations annotations to add.
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withMessageAnnotations(Annotations annotations);

    /**
     * This is a helper method used to add markers to the {@code HttpRequestResponse} instance.
     *
     * @param requestMarkers  Request markers to add.
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
