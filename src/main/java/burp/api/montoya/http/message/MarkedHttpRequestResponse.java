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
 * This interface is used to extend {@link HttpRequestResponse} interface to hold message markers data.
 */
public interface MarkedHttpRequestResponse extends HttpRequestResponse
{
    /**
     * This is a helper method to create a new instance of {@link MarkedHttpRequestResponse}.
     *
     * @param httpRequest  The HTTP request.
     * @param httpResponse The HTTP response.
     * @return A new {@link MarkedHttpRequestResponse} instance.
     */
    static MarkedHttpRequestResponse markedRequestResponse(HttpRequest httpRequest, HttpResponse httpResponse)
    {
        return markedRequestResponse(httpRequest, httpResponse, annotations());
    }

    /**
     * This is a helper method to create a new instance of {@link MarkedHttpRequestResponse}.
     *
     * @param httpRequest        The HTTP request.
     * @param httpResponse       The HTTP response.
     * @param annotations annotations.
     * @return A new {@link MarkedHttpRequestResponse} instance.
     */
    static MarkedHttpRequestResponse markedRequestResponse(HttpRequest httpRequest, HttpResponse httpResponse, Annotations annotations)
    {
        return FACTORY.markedRequestResponse(httpRequest, httpResponse, annotations);
    }

    /**
     * @return List of request markers
     */
    List<Range> requestMarkers();

    /**
     * @return List of response markers
     */
    List<Range> responseMarkers();


    /**
     * This is a helper method used to add annotations to the {@code MarkedHttpRequestResponse} instance.
     *
     * @param annotations annotations to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    @Override
    MarkedHttpRequestResponse withMessageAnnotations(Annotations annotations);
}
