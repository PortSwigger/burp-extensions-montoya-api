/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.requests;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.Marker;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.message.ContentType;
import burp.api.montoya.http.message.HttpHeader;
import burp.api.montoya.http.message.HttpMessage;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.ParsedHttpParameter;

import java.util.List;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Burp HTTP request able to retrieve and modify details of an HTTP request.
 */
public interface HttpRequest extends HttpMessage
{
    /**
     * HTTP service for the request.
     *
     * @return An {@link HttpService} object containing details of the HTTP service.
     */
    HttpService httpService();

    /**
     * URL for the request.
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return The URL in the request.
     *
     * @throws MalformedRequestException if request is malformed.
     */
    String url();

    /**
     * HTTP method for the request.
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return The HTTP method used in the request.
     *
     * @throws MalformedRequestException if request is malformed.
     */
    String method();

    /**
     * Path and File for the request.
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return the path and file in the request
     *
     * @throws MalformedRequestException if request is malformed.
     */
    String path();

    /**
     * HTTP Version text parsed from the request line for HTTP 1 messages.
     * HTTP 2 messages will return "HTTP/2"
     *
     * @return Version string
     */
    String httpVersion();

    /**
     * {@inheritDoc}
     */
    @Override
    List<HttpHeader> headers();

    /**
     * @return The detected content type of the request.
     */
    ContentType contentType();

    /**
     * @return The parameters contained in the request.
     */
    List<ParsedHttpParameter> parameters();

    /**
     * {@inheritDoc}
     */
    @Override
    ByteArray body();

    /**
     * {@inheritDoc}
     */
    @Override
    String bodyToString();

    /**
     * {@inheritDoc}
     */
    @Override
    int bodyOffset();

    /**
     * {@inheritDoc}
     */
    @Override
    List<Marker> markers();

    /**
     * {@inheritDoc}
     */
    @Override
    ByteArray toByteArray();

    /**
     * {@inheritDoc}
     */
    @Override
    String toString();

    /**
     * Create a copy of the {@code HttpRequest} with the new service.
     *
     * @param service An {@link HttpService} reference to add.
     *
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withService(HttpService service);

    /**
     * Create a copy of the {@code HttpRequest} with the new path.
     *
     * @param path The path to use.
     *
     * @return A new {@code HttpRequest} instance with updated path.
     */
    HttpRequest withPath(String path);

    /**
     * Create a copy of the {@code HttpRequest} with the new method.
     *
     * @param method the method to use
     *
     * @return a new {@code HttpRequest} instance with updated method.
     */
    HttpRequest withMethod(String method);

    /**
     * Create a copy of the {@code HttpRequest} with the added HTTP parameters.
     *
     * @param parameters HTTP parameters to add.
     *
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withAddedParameters(List<HttpParameter> parameters);

    /**
     * Create a copy of the {@code HttpRequest} with the added HTTP parameters.
     *
     * @param parameters HTTP parameters to add.
     *
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withAddedParameters(HttpParameter... parameters);

    /**
     * Create a copy of the {@code HttpRequest} with the removed HTTP parameters.
     *
     * @param parameters HTTP parameters to remove.
     *
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withRemovedParameters(List<HttpParameter> parameters);

    /**
     * Create a copy of the {@code HttpRequest} with the removed HTTP parameters.
     *
     * @param parameters HTTP parameters to remove.
     *
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withRemovedParameters(HttpParameter... parameters);

    /**
     * Create a copy of the {@code HttpRequest} with the updated HTTP parameters.<br>
     * If a parameter does not exist in the request, a new one will be added.
     *
     * @param parameters HTTP parameters to update.
     *
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withUpdatedParameters(List<HttpParameter> parameters);

    /**
     * Create a copy of the {@code HttpRequest} with the updated HTTP parameters.<br>
     * If a parameter does not exist in the request, a new one will be added.
     *
     * @param parameters HTTP parameters to update.
     *
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withUpdatedParameters(HttpParameter... parameters);

    /**
     * Create a copy of the {@code HttpRequest} with the transformation applied.
     *
     * @param transformation Transformation to apply.
     *
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withTransformationApplied(HttpTransformation transformation);

    /**
     * Create a copy of the {@code HttpRequest} with the updated body.<br>
     * Updates Content-Length header.
     *
     * @param body the new body for the request
     *
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withBody(String body);

    /**
     * Create a copy of the {@code HttpRequest} with the updated body.<br>
     * Updates Content-Length header.
     *
     * @param body the new body for the request
     *
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withBody(ByteArray body);

    /**
     * Create a copy of the {@code HttpRequest} with the added header.
     *
     * @param name  The name of the header.
     * @param value The value of the header.
     *
     * @return The updated HTTP request with the added header.
     */
    HttpRequest withAddedHeader(String name, String value);

    /**
     * Create a copy of the {@code HttpRequest} with the added header.
     *
     * @param header The {@link HttpHeader} to add to the HTTP request.
     *
     * @return The updated HTTP request with the added header.
     */
    HttpRequest withAddedHeader(HttpHeader header);

    /**
     * Create a copy of the {@code HttpRequest} with the updated header.
     *
     * @param name  The name of the header to update the value of.
     * @param value The new value of the specified HTTP header.
     *
     * @return The updated request containing the updated header.
     */
    HttpRequest withUpdatedHeader(String name, String value);

    /**
     * Create a copy of the {@code HttpRequest} with the updated header.
     *
     * @param header The {@link HttpHeader} to update containing the new value.
     *
     * @return The updated request containing the updated header.
     */
    HttpRequest withUpdatedHeader(HttpHeader header);

    /**
     * Removes an existing HTTP header from the current request.
     *
     * @param name The name of the HTTP header to remove from the request.
     *
     * @return The updated request containing the removed header.
     */
    HttpRequest withRemovedHeader(String name);

    /**
     * Removes an existing HTTP header from the current request.
     *
     * @param header The {@link HttpHeader} to remove from the request.
     *
     * @return The updated request containing the removed header.
     */
    HttpRequest withRemovedHeader(HttpHeader header);

    /**
     * Create a copy of the {@code HttpRequest} with the added markers.
     *
     * @param markers Request markers to add.
     *
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    HttpRequest withMarkers(List<Marker> markers);

    /**
     * Create a copy of the {@code HttpRequest} with the added markers.
     *
     * @param markers Request markers to add.
     *
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    HttpRequest withMarkers(Marker... markers);

    /**
     * Create a copy of the {@code HttpRequest} with added default headers.
     *
     * @return a new (@code HttpRequest) with added default headers
     */
    HttpRequest withDefaultHeaders();

    /**
     * Create a new empty instance of {@link HttpRequest}.<br>
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest()
    {
        return FACTORY.httpRequest();
    }

    /**
     * Create a new instance of {@link HttpRequest}.<br>
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param request The HTTP request
     *
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest(ByteArray request)
    {
        return FACTORY.httpRequest(request);
    }

    /**
     * Create a new instance of {@link HttpRequest}.<br>
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param request The HTTP request.
     *
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest(String request)
    {
        return FACTORY.httpRequest(request);
    }

    /**
     * Create a new instance of {@link HttpRequest}.<br>
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param service An HTTP service for the request.
     * @param request The HTTP request.
     *
     * @return A new {@link HttpRequest} instance. A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest(HttpService service, ByteArray request)
    {
        return FACTORY.httpRequest(service, request);
    }

    /**
     * Create a new instance of {@link HttpRequest}.<br>
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param service An HTTP service for the request.
     * @param request The HTTP request.
     *
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest(HttpService service, String request)
    {
        return FACTORY.httpRequest(service, request);
    }

    /**
     * Create a new instance of {@link HttpRequest} from the given {@link HttpRequest}.<br>
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param httpRequestToCopy source HTTP request.
     *
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequest(HttpRequest httpRequestToCopy)
    {
        return FACTORY.httpRequest(httpRequestToCopy);
    }

    /**
     * Create a new instance of {@link HttpRequest}.<br>
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param url A URL for the request.
     *
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest httpRequestFromUrl(String url)
    {
        return FACTORY.httpRequestFromUrl(url);
    }

    /**
     * Create a new instance of {@link HttpRequest} containing HTTP 2 headers and body.<br>
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param service An HTTP service for the request.
     * @param headers A list of HTTP 2 headers.
     * @param body    A body of the HTTP 2 request.
     *
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest http2Request(HttpService service, List<HttpHeader> headers, ByteArray body)
    {
        return FACTORY.http2Request(service, headers, body);
    }

    /**
     * Create a new instance of {@link HttpRequest} containing HTTP 2 headers and body.<br>
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param service An HTTP service for the request.
     * @param headers A list of HTTP 2 headers.
     * @param body    A body of the HTTP 2 request.
     *
     * @return A new {@link HttpRequest} instance.
     */
    static HttpRequest http2Request(HttpService service, List<HttpHeader> headers, String body)
    {
        return FACTORY.http2Request(service, headers, body);
    }
}
