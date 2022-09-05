/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.ToolSource;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

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
     * @param request     The HTTP request that is about to be sent.
     * @param annotations annotations.
     * @param toolSource  Indicates which Burp tool issued the request.
     * @return An instance of {@link RequestResult}.
     */
    RequestResult handleHttpRequest(HttpRequest request, Annotations annotations, ToolSource toolSource);

    /**
     * This method is invoked by Burp when an HTTP response has been received.
     *
     * @param request     The HTTP request that was issued.
     * @param response    The HTTP response that was received.
     * @param annotations annotations.
     * @param toolSource  Indicates which Burp tool issued the request.
     * @return An instance of {@link ResponseResult}.
     */
    ResponseResult handleHttpResponse(HttpRequest request, HttpResponse response, Annotations annotations, ToolSource toolSource);
}
