/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.sessions;

import burp.api.montoya.core.MessageAnnotations;
import burp.api.montoya.http.message.requests.HttpRequest;

/**
 * An instance of this interface should be returned by {@link SessionHandlingAction#handle} if a custom
 * {@link SessionHandlingAction} has been registered with Burp.
 */
public interface SessionHandlingResult
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
     * This is a helper method used to create a new instance of {@code SessionHandlingResult}.
     *
     * @param request An HTTP request.
     * @param annotations Message annotations.
     * @return A new {@link SessionHandlingResult} instance.
     */
    static SessionHandlingResult from(HttpRequest request, MessageAnnotations annotations)
    {
        return new SessionHandlingResult()
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
