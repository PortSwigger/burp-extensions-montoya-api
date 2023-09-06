/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.requests.MalformedRequestException;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.regex.Pattern;

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
    @Deprecated(forRemoval = true)
    String url();

    /**
     * HTTP method for the issued final request.
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return The HTTP method used in the request.
     * @throws MalformedRequestException if request is malformed.
     * @deprecated use {@link #finalRequest()} method instead.
     */
    @Deprecated(forRemoval = true)
    String method();

    /**
     * Path and File for the issued final request.
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return the path and file in the request
     * @throws MalformedRequestException if request is malformed.
     * @deprecated use {@link #finalRequest()} path instead.
     */
    @Deprecated(forRemoval = true)
    String path();

    /**
     * @return The hostname or IP address for the service.
     * @deprecated use {@link #finalRequest()} httpService instead.
     */
    @Deprecated(forRemoval = true)
    String host();

    /**
     * @return The port number for the service.
     * @deprecated use {@link #finalRequest()} httpService instead.
     */
    @Deprecated(forRemoval = true)
    int port();

    /**
     * @return True is a secure protocol is used for the connection, false otherwise.
     * @deprecated use {@link #finalRequest()} httpService instead.
     */
    @Deprecated(forRemoval = true)
    boolean secure();

    /**
     * @return The {@code String} representation of the service.
     * @deprecated use {@link #finalRequest()} httpService instead.
     */
    @Deprecated(forRemoval = true)
    String httpServiceString();

    /**
     * HTTP Version text parsed from the request line for HTTP 1 messages.
     * HTTP 2 messages will return "HTTP/2"
     *
     * @return Version string
     * @deprecated use {@link #finalRequest()} httpVersion instead.
     */
    @Deprecated(forRemoval = true)
    String requestHttpVersion();

    /**
     * Body of the issued final request
     *
     * @return The body of a message as a {@code String}.
     * @deprecated use {@link #finalRequest()} body instead.
     */
    @Deprecated(forRemoval = true)
    String requestBody();

    /**
     * @return True if the request or response was edited
     */
    boolean edited();

    /**
     * Searches the data in the HTTP request and response for the specified search term.
     *
     * @param searchTerm    The value to be searched for.
     * @param caseSensitive Flags whether the search is case-sensitive.
     *
     * @return True if the search term is found.
     */
    boolean contains(String searchTerm, boolean caseSensitive);

    /**
     * Searches the data in the HTTP request and response for the specified regular expression.
     *
     * @param pattern The regular expression to be searched for.
     *
     * @return True if the pattern is matched.
     */
    boolean contains(Pattern pattern);
}
