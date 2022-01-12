/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

import burp.api.montoya.core.MessageAnnotations;
import burp.api.montoya.http.message.responses.HttpResponse;

/**
 * An instance of this interface should be returned by {@link HttpHandler#handleHttpResponse} if a custom
 * {@link HttpHandler} has been registered with Burp.
 */
public interface ResponseHandlerResult
{
    /**
     * @return The HTTP response.
     */
    HttpResponse response();

    /**
     * @return The message annotations.
     */
    MessageAnnotations annotations();

    /**
     * This is a helper method used to create a new instance of {@code ResponseHandlerResult}.
     *
     * @param response An HTTP response.
     * @param annotations Message annotations.
     * @return A new {@code ResponseHandlerResult} instance.
     */
    static ResponseHandlerResult from(HttpResponse response, MessageAnnotations annotations)
    {
        return new ResponseHandlerResult()
        {
            @Override
            public HttpResponse response()
            {
                return response;
            }

            @Override
            public MessageAnnotations annotations()
            {
                return annotations;
            }
        };
    }
}
