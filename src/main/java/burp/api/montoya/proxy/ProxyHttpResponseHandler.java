/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.MessageAnnotations;
import burp.api.montoya.http.message.requests.HttpRequest;

/**
 * Extensions can implement this interface and then call
 * {@link Proxy#registerResponseHandler(ProxyHttpResponseHandler)} to register a
 * Proxy response handler. The handler will be notified of responses being
 * processed by the Proxy tool. Extensions can perform custom analysis or
 * modification of these responses, and control in-UI message interception.
 */
public interface ProxyHttpResponseHandler
{
    /**
     * This method is invoked when an HTTP response is received in the Proxy.
     *
     * @param interceptedResponse An {@link InterceptedHttpResponse} object
     * that extensions can use to query and update details of the response, and
     * control whether the response should be intercepted and displayed to the
     * user for manual review or modification.
     * @param request The {@link HttpRequest} that was issued.
     * @param annotations The {@link MessageAnnotations} for the intercepted request.
     */
    ResponseInitialInterceptResult handleReceivedResponse(InterceptedHttpResponse interceptedResponse, HttpRequest request, MessageAnnotations annotations);

    /**
     * This method is invoked when an HTTP response has been processed by the
     * Proxy before it is returned to the client.
     *
     * @param interceptedResponse An {@link InterceptedHttpResponse} object
     * that extensions can use to query and update details of the response.
     * @param request The {@link HttpRequest} that was issued.
     * @param annotations The {@link MessageAnnotations} for the intercepted request.
     */
    ResponseFinalInterceptResult handleResponseToReturn(InterceptedHttpResponse interceptedResponse, HttpRequest request, MessageAnnotations annotations);
}
