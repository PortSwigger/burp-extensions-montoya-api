/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.MimeType;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.requests.MalformedRequestException;
import burp.api.montoya.http.message.responses.HttpResponse;

/**
 * HTTP request and response intercepted by the Proxy.
 */
public interface ProxyHttpRequestResponse
{
    /**
     * This method retrieves the annotations for the request/response pair.
     *
     * @return The {@link Annotations} for the request/response pair.
     */
    Annotations annotations();

    /**
     * This method retrieves the HTTP request that was sent by Burp Proxy.
     *
     * @return The {@link HttpRequest} that was sent by Burp Proxy.
     */
    HttpRequest finalRequest();

    /**
     * This method retrieves the HTTP response that was received by Burp Proxy.
     *
     * @return The {@link HttpResponse} that was received by Burp Proxy.
     */
    HttpResponse originalResponse();

    /**
     * URL for the issued final request.
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return The URL in the request.
     * @throws MalformedRequestException if request is malformed.
     */
    String url();

    /**
     * HTTP method for the issued final request.
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return The HTTP method used in the request.
     * @throws MalformedRequestException if request is malformed.
     */
    String method();

    /**
     * Path and File for the issued final request.
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return the path and file in the request
     * @throws MalformedRequestException if request is malformed.
     */
    String path();

    /**
     * @return The hostname or IP address for the service.
     */
    String host();

    /**
     * @return The MIME type of the final response.
     */
    MimeType mimeType();

    /**
     * @return The port number for the service.
     */
    int port();

    /**
     * @return True is a secure protocol is used for the connection, false otherwise.
     */
    boolean secure();

    /**
     * @return The {@code String} representation of the service.
     */
    String httpServiceString();

    /**
     * HTTP Version text parsed from the request line for HTTP 1 messages.
     * HTTP 2 messages will return "HTTP/2"
     *
     * @return Version string
     */
    String requestHttpVersion();

    /**
     * Body of the issued final request
     *
     * @return The body of a message as a {@code String}.
     */
    String requestBody();

}
