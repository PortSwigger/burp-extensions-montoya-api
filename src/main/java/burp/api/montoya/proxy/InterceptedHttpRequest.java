/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.http.message.requests.HttpRequest;

/**
 * This interface represents an instance of an HTTP request intercepted by Burp
 * Proxy.
 */
public interface InterceptedHttpRequest extends InterceptedMessage, HttpRequest
{
}
