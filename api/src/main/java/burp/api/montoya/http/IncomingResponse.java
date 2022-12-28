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

public interface IncomingResponse
{
    /**
     * @return The HTTP response that was received.
     */
    HttpResponse response();

    /**
     * @return initiatingRequest The HTTP request that was issued.
     */
    HttpRequest initiatingRequest();

    /**
     * @return Annotations for request/response.
     */
    Annotations annotations();

    /**
     * @return ToolSource which indicates which Burp tool issued the request.
     */
    ToolSource toolSource();
}
