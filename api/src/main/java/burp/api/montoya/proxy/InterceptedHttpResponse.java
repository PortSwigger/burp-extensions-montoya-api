/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.http.message.responses.HttpResponse;

/**
 * This interface represents an instance of an HTTP response intercepted by
 * Burp Proxy.
 */
public interface InterceptedHttpResponse extends InterceptedMessage, HttpResponse
{
}
