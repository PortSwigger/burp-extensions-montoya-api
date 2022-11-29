/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

/**
 * This interface represents an instance of an HTTP request and response
 * intercepted by the Proxy.
 */
public interface ProxyRequestResponse extends HttpRequestResponse
{
    /**
     * This method used to update annotations to the {@code ProxyRequestResponse} instance.
     *
     * @param annotations new annotations for the request/response.
     */
    void setMessageAnnotations(Annotations annotations);

    /**
     * This method retrieves the HTTP request that was issued by Burp Proxy.
     *
     * @return The {@link HttpRequest} that was issued by Burp Proxy.
     */
    HttpRequest finalRequest();

    /**
     * This method retrieves the HTTP response that was received by Burp Proxy.
     *
     * @return The {@link HttpResponse} that was received by Burp Proxy.
     */
    HttpResponse originalResponse();
}
