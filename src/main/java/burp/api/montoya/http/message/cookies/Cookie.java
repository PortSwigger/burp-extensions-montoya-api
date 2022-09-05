/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.cookies;

import burp.api.montoya.http.message.responses.HttpResponse;

import java.time.ZonedDateTime;

/**
 * This interface is used to hold details about an HTTP cookie.
 */
public interface Cookie
{
    /**
     * This method is used to retrieve the name of the cookie.
     *
     * @return The name of the cookie
     */
    String name();

    /**
     * This method is used to retrieve the value of the cookie.
     *
     * @return The value of the cookie.
     */
    String value();

    /**
     * This method is used to retrieve the domain for which the cookie is in scope.
     *
     * @return The domain for which the cookie is in scope. Note: For cookies that have been obtained from generated responses
     * (by calling {@link HttpResponse#httpResponse} and then {@link HttpResponse#cookies}), the domain will be {@code null} if the response
     * did not explicitly set a domain attribute for the cookie.
     */
    String domain();

    /**
     * This method is used to retrieve the path for which the cookie is in scope.
     *
     * @return The path for which the cookie is in scope or {@code null} if none is set.
     */
    String path();

    /**
     * This method is used to retrieve the expiration time for the cookie.
     *
     * @return The expiration time for the cookie, or {@code null} if none is set (i.e., for non-persistent session cookies).
     */
    ZonedDateTime expiration();
}
