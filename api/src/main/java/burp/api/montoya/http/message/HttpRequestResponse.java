/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.ContentType;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.requests.MalformedRequestException;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.List;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to define a coupling between {@link HttpRequest} and {@link HttpResponse} interfaces.
 */
public interface HttpRequestResponse
{
    /**
     * @return The HTTP request message.
     */
    HttpRequest request();

    /**
     * @return The HTTP response message.
     */
    HttpResponse response();

    /**
     * @return The annotations.
     */
    Annotations annotations();

    /**
     * This method is used to retrieve the URL for the request.
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return The URL in the request.
     * @throws MalformedRequestException if request is malformed.
     */
    String url();

    /**
     * This method is used to retrieve the HTTP service for the request.
     *
     * @return An {@link HttpService} object containing details of the HTTP service.
     */
    HttpService httpService();

    /**
     * @return The detected content type of the request.
     */
    ContentType contentType();

    /**
     * This method is used to obtain the HTTP status code contained in the response.
     *
     * @return HTTP status code or -1 if there is no response.
     */
    short statusCode();

    /**
     * @return List of request markers
     */
    List<Marker> requestMarkers();

    /**
     * @return List of response markers
     */
    List<Marker> responseMarkers();

    /**
     * This is a helper method used to add annotations to the {@code HttpRequestResponse} instance.
     *
     * @param annotations annotations to add.
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withAnnotations(Annotations annotations);

    /**
     * This is a helper method used to add request markers to the {@code HttpRequestResponse} instance.
     *
     * @param requestMarkers Request markers to add.
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withRequestMarkers(List<Marker> requestMarkers);

    /**
     * This is a helper method used to add request markers to the {@code HttpRequestResponse} instance.
     *
     * @param requestMarkers Request markers to add.
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withRequestMarkers(Marker... requestMarkers);

    /**
     * This is a helper method used to add response markers to the {@code HttpRequestResponse} instance.
     *
     * @param responseMarkers Response markers to add.
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withResponseMarkers(List<Marker> responseMarkers);

    /**
     * This is a helper method used to add response markers to the {@code HttpRequestResponse} instance.
     *
     * @param responseMarkers Response markers to add.
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withResponseMarkers(Marker... responseMarkers);

    /**
     * This is a helper method to create a new instance of {@link HttpRequestResponse}.
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A new {@link HttpRequestResponse} instance.
     */
    static HttpRequestResponse httpRequestResponse(HttpRequest request, HttpResponse response)
    {
        return httpRequestResponse(request, response, Annotations.annotations());
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequestResponse}.
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param httpRequest        The HTTP request.
     * @param httpResponse       The HTTP response.
     * @param annotations annotations.
     * @return A new {@link HttpRequestResponse} instance.
     */
    static HttpRequestResponse httpRequestResponse(HttpRequest httpRequest, HttpResponse httpResponse, Annotations annotations)
    {
        return FACTORY.httpRequestResponse(httpRequest, httpResponse, annotations);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequestResponse} from the given {@link HttpRequestResponse}.
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param httpRequestResponseToCopy source HTTP request response.
     * @return A new {@link HttpRequestResponse} instance.
     */
    static HttpRequestResponse httpRequestResponse(HttpRequestResponse httpRequestResponseToCopy)
    {
        return FACTORY.httpRequestResponse(httpRequestResponseToCopy);
    }
}
