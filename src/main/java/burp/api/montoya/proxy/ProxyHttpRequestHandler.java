/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.Annotations;

/**
 * Extensions can implement this interface and then call
 * {@link Proxy#registerRequestHandler(ProxyHttpRequestHandler)} to register a
 * Proxy request handler. The handler will be notified of requests being
 * processed by the Proxy tool. Extensions can perform custom analysis or
 * modification of these messages, and control in-UI message interception.
 */
public interface ProxyHttpRequestHandler
{
    /**
     * This method is invoked when an HTTP request is received in the Proxy.
     *
     * @param interceptedRequest An {@link InterceptedHttpRequest} object
     *                           that extensions can use to query and update details of the request, and
     *                           control whether the request should be intercepted and displayed to the
     *                           user for manual review or modification.
     * @param annotations        The {@link Annotations} for the intercepted request.
     * @return The {@link RequestInitialInterceptResult} containing the required action, HTTP request and annotations to be passed through.
     */
    RequestInitialInterceptResult handleReceivedRequest(InterceptedHttpRequest interceptedRequest, Annotations annotations);

    /**
     * This method is invoked when an HTTP request has been processed by the
     * Proxy before it is issued.
     *
     * @param interceptedRequest An {@link InterceptedHttpRequest} object
     *                           that extensions can use to query and update details of the request.
     * @param annotations        The {@link Annotations} for the intercepted request.
     * @return The {@link RequestFinalInterceptResult} containing the required action, HTTP request and annotations to be passed through.
     */
    RequestFinalInterceptResult handleRequestToIssue(InterceptedHttpRequest interceptedRequest, Annotations annotations);
}
