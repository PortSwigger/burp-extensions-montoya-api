/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.intercept;

/**
 * This enum represents the action to be taken when intercepting HTTP requests.
 */
public enum HttpRequestAction
{
    /**
     * Causes Burp Proxy to send the request.
     */
    CONTINUE
}
