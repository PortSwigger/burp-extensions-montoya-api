/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

/**
 * Extensions can implement this interface and then call {@link Http#registerHttpHandler} to register an HTTP handler. The handler
 * will be notified of requests and responses made and received by any Burp tool. Extensions can perform custom analysis or modification
 * of these messages by registering an HTTP handler.
 */
public interface HttpHandler
{
    /**
     * This method is invoked by Burp when an HTTP request is about to be issued.
     *
     * @param outgoingRequest information about the HTTP request that is going to be sent.
     * @return An instance of {@link RequestResult}.
     */
    RequestResult handleHttpRequest(OutgoingHttpRequest outgoingRequest);

    /**
     * This method is invoked by Burp when an HTTP response has been received.
     *
     * @param incomingResponse information about HTTP response that was received.
     * @return An instance of {@link ResponseResult}.
     */
    ResponseResult handleHttpResponse(IncomingHttpResponse incomingResponse);
}
