/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.requests;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.http.ContentType;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.HttpTransformation;
import burp.api.montoya.http.message.HttpMessage;
import burp.api.montoya.http.message.Marker;
import burp.api.montoya.http.message.headers.HttpHeader;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.ParsedHttpParameter;

import java.util.List;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to retrieve key details about an HTTP request.
 */
public interface HttpRequest extends HttpMessage
{
    /**
     * This method is used to retrieve the HTTP service for the request.
     *
     * @return An {@link HttpService} object containing details of the HTTP service.
     */
    HttpService httpService();

    /**
     * @return The HTTP method used in the request.
     */
    String method();

    /**
     * @return the path and file in the request
     */
    String path();

    /**
     * @return The URL in the request.
     */
    String url();

    /**
     * @return The content type of the message body.
     */
    ContentType contentType();

    /**
     * @return The parameters contained in the request.
     */
    List<ParsedHttpParameter> parameters();

    /**
     * This is a helper method that builds a modified request with the new service.
     *
     * @param service An {@link HttpService} reference to add.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withService(HttpService service);

    /**
     * This is a helper method that builds a modified request with the new path.
     *
     * @param path The path to use.
     * @return A new {@code HttpRequest} instance with updated path.
     */
    HttpRequest withPath(String path);

    /**
     * This is a helper method that builds a modified request with the new method.
     *
     * @param method the method to use
     * @return a new {@code HttpRequest} instance with updated method.
     */
    HttpRequest withMethod(String method);

    /**
     * This is a helper method that builds a modified request with the added HTTP parameters.
     *
     * @param parameters HTTP parameters to add.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withAddedParameters(List<HttpParameter> parameters);

    /**
     * This is a helper method that builds a modified request with the added HTTP parameters.
     *
     * @param parameters HTTP parameters to add.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withAddedParameters(HttpParameter... parameters);

    /**
     * This is a helper method that builds a modified request with the removed HTTP parameters.
     *
     * @param parameters HTTP parameters to remove.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withRemovedParameters(List<HttpParameter> parameters);

    /**
     * This is a helper method that builds a modified request with the removed HTTP parameters.
     *
     * @param parameters HTTP parameters to remove.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withRemovedParameters(HttpParameter... parameters);

    /**
     * This is a helper method that builds a modified request with the updated HTTP parameters.
     * If a parameter does not exist in the request, a new one will be added.
     *
     * @param parameters HTTP parameters to update.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withUpdatedParameters(List<HttpParameter> parameters);

    /**
     * This is a helper method that builds a modified request with the updated HTTP parameters.
     * If a parameter does not exist in the request, a new one will be added.
     *
     * @param parameters HTTP parameters to update.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withUpdatedParameters(HttpParameter... parameters);

    /**
     * This is a helper method that builds a modified request with the transformation applied.
     *
     * @param transformation Transformation to apply.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withTransformationApplied(HttpTransformation transformation);

    /**
     * This is a helper method that builds a modified request with the updated body.
     * Updates Content-Length header.
     *
     * @param body the new body for the request
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withBody(String body);

    /**
     * This is a helper method that builds a modified request with the updated body.
     * Updates Content-Length header.
     *
     * @param body the new body for the request
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withBody(ByteArray body);

    /**
     * Adds an HTTP header to the current request.
     *
     * @param name  The name of the header.
     * @param value The value of the header.
     * @return The updated HTTP request with the added header.
     */
    HttpRequest addHeader(String name, String value);

    /**
     * Adds an HTTP header to the current request.
     *
     * @param header The {@link HttpHeader} to add to the HTTP request.
     * @return The updated HTTP request with the added header.
     */
    HttpRequest addHeader(HttpHeader header);

    /**
     * Updates an existing HTTP header in the request with a new value.
     *
     * @param name  The name of the header to update the value of.
     * @param value The new value of the specified HTTP header.
     * @return The updated request containing the updated header.
     */
    HttpRequest updateHeader(String name, String value);

    /**
     * Updates an existing HTTP header in the request with a new value.
     *
     * @param header The {@link HttpHeader} to update containing the new value.
     * @return The updated request containing the updated header.
     */
    HttpRequest updateHeader(HttpHeader header);

    /**
     * Removes an existing HTTP header from the current request.
     *
     * @param name The name of the HTTP header to remove from the request.
     * @return The updated request containing the removed header.
     */
    HttpRequest removeHeader(String name);

    /**
     * Removes an existing HTTP header from the current request.
     *
     * @param header The {@link HttpHeader} to remove from the request.
     * @return The updated request containing the removed header.
     */
    HttpRequest removeHeader(HttpHeader header);

    /**
     * This method is used to obtain the HTTP headers contained in the message.
     *
     * @return A list of HTTP headers.
     */
    List<Marker> markers();

    /**
     * This is a helper method used to add request markers to the {@code HttpRequest} instance.
     *
     * @param markers Request markers to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    HttpRequest withMarkers(List<Marker> markers);

    /**
     * This is a helper method used create a new instance with markers to the {@code HttpRequest} instance.
     *
     * @param markers Request markers to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    HttpRequest withMarkers(Marker... markers);

    /**
     * This is a helper method to create a new empty {@link HttpRequest}.
     *
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest()
    {
        return httpRequest(null, "");
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param request The HTTP request
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest(ByteArray request)
    {
        return httpRequest(null, request);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param request The HTTP request.
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest(String request)
    {
        return httpRequest(null, request);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param service An HTTP service for the request.
     * @param request The HTTP request.
     * @return A new {@link HttpRequest} instance. A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest(HttpService service, ByteArray request)
    {
        return FACTORY.httpRequest(service, request);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param service An HTTP service for the request.
     * @param request The HTTP request.
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest(HttpService service, String request)
    {
        return FACTORY.httpRequest(service, request);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param url A URL for the request.
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequestFromUrl(String url)
    {
        return FACTORY.httpRequestFromUrl(url);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequest} containing HTTP 2 headers and body.
     *
     * @param service An HTTP service for the request.
     * @param headers A list of HTTP 2 headers.
     * @param body    A body of the HTTP 2 request.
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest http2Request(HttpService service, List<HttpHeader> headers, ByteArray body)
    {
        return FACTORY.http2Request(service, headers, body);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequest} containing HTTP 2 headers and body.
     *
     * @param service An HTTP service for the request.
     * @param headers A list of HTTP 2 headers.
     * @param body    A body of the HTTP 2 request.
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest http2Request(HttpService service, List<HttpHeader> headers, String body)
    {
        return FACTORY.http2Request(service, headers, body);
    }
}
