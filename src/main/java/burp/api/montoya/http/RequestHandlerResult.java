/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

import burp.api.montoya.core.MessageAnnotations;
import burp.api.montoya.http.message.requests.HttpRequest;

/**
 * An instance of this interface should be returned by {@link HttpHandler#handleHttpRequest} if a custom
 * {@link HttpHandler} has been registered with Burp.
 */
public interface RequestHandlerResult
{
    /**
     * @return The HTTP request.
     */
    HttpRequest request();

    /**
     * @return The message annotations.
     */
    MessageAnnotations annotations();

    /**
     * This is a helper method used to create a new instance of {@code RequestHandlerResult}.
     *
     * @param request An HTTP request.
     * @param annotations Message annotations.
     * @return A new {@code RequestHandlerResult} instance.
     */
    static RequestHandlerResult from(HttpRequest request, MessageAnnotations annotations)
    {
        return new RequestHandlerResult()
        {
            @Override
            public HttpRequest request()
            {
                return request;
            }

            @Override
            public MessageAnnotations annotations()
            {
                return annotations;
            }
        };
    }
}
