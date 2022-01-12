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

import java.util.List;

/**
 * This interface is used to extend {@link HttpRequestResponse} interface to hold message markers data.
 */
public interface MarkedHttpRequestResponse extends HttpRequestResponse
{
    /**
     * @return List of request markers
     */
    List<Range> requestMarkers();

    /**
     * @return List of response markers
     */
    List<Range> responseMarkers();


    /**
     * This is a helper method used to add message annotations to the {@code MarkedHttpRequestResponse} instance.
     *
     * @param messageAnnotations Message annotations to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    @Override
    MarkedHttpRequestResponse withMessageAnnotations(MessageAnnotations messageAnnotations);
}
