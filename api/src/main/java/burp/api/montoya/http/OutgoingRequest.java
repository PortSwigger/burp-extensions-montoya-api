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

public interface OutgoingRequest
{
    /**
     * @return The HTTP request that is about to be sent.
     */
    HttpRequest request();

    /**
     * @return annotations for request/response
     */
    Annotations annotations();

    /**
     * @return Indicates which Burp tool issued the request.
     */
    ToolSource toolSource();
}
