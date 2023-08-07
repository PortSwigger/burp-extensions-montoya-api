/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.Marker;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.handler.TimingData;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.HttpParameterType;
import burp.api.montoya.http.message.params.ParsedHttpParameter;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.requests.MalformedRequestException;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to define a coupling between {@link HttpRequest} and {@link HttpResponse}.
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
     * @return True if there is an HTTP response message.
     */
    boolean hasResponse();

    /**
     * @return The annotations.
     */
    Annotations annotations();

    /**
     * @return True if there are any notes for this HTTP request and response.
     */
    boolean hasNotes();

    /**
     * @return True if there is a highlight color for this HTTP request and response.
     */
    boolean hasHighlightColor();

    /**
     * @return True if the HTTP request is in-scope.
     */
    boolean isInScope();

    /**
     * Retrieve the timing data associated with this request if available.
     *
     * @return The timing data.
     */
    Optional<TimingData> timingData();

    /**
     * Retrieve the URL for the request.<br>
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return The URL in the request.
     * @throws MalformedRequestException if request is malformed.
     */
    String url();

    /**
     * HTTP service for the request.
     *
     * @return An {@link HttpService} object containing details of the HTTP service.
     */
    HttpService httpService();

    /**
     * @return The detected content type of the request.
     */
    ContentType contentType();

    /**
     * HTTP status code contained in the response.
     *
     * @return HTTP status code or -1 if there is no response.
     */
    short statusCode();

    /**
     * Test whether the HTTP status code is in the specified class.
     *
     * @param statusCodeClass The class of HTTP status code to test.
     *
     * @return True if the HTTP status code is in the class.
     */
    boolean isStatusCodeClass(StatusCodeClass statusCodeClass);

    /**
     * Obtain details of the HTTP cookies set in the response.
     *
     * @return A list of {@link Cookie} objects representing the cookies set in the response, if any.
     */
    List<Cookie> responseCookies();

    /**
     * @param name The name of the cookie to find in the response, if any.
     *
     * @return An instance of {@link Cookie} that matches the name provided. {@code null} if not found.
     */
    Cookie responseCookie(String name);

    /**
     * @param name The name of the cookie to retrieve the value from in the response, if any.
     *
     * @return The value of the cookie that matches the name provided. {@code null} if not found.
     */
    String responseCookieValue(String name);

    /**
     * @param name The name of the cookie to check if it exists in the response.
     *
     * @return {@code true} If a cookie exists within the response that matches the name provided. {@code false} if not.
     */
    boolean responseHasCookie(String name);

    /**
     * @param cookie An instance of {@link Cookie} to check if it exists in the response.
     *
     * @return {@code true} If a cookie exists within the response that matches the {@link Cookie} provided. {@code false} if not.
     */
    boolean responseHasCookie(Cookie cookie);

    /**
     * @return The parameters contained in the request.
     */
    List<ParsedHttpParameter> parameters();

    /**
     * @param type The type of parameter that will be returned in the filtered list.
     *
     * @return A filtered list of {@link ParsedHttpParameter} containing only the provided type.
     */
    List<ParsedHttpParameter> parameters(HttpParameterType type);

    /**
     * @return True if the request has parameters.
     */
    boolean hasParameters();

    /**
     * @param name The name of the parameter to find.
     * @param type The type of the parameter to find.
     *
     * @return An instance of {@link ParsedHttpParameter} that matches the type and name specified. {@code null} if not found.
     */
    ParsedHttpParameter parameter(String name, HttpParameterType type);

    /**
     * @param name The name of the parameter to get the value from.
     * @param type The type of the parameter to get the value from.
     *
     * @return The value of the parameter that matches the name and type specified. {@code null} if not found.
     */
    String parameterValue(String name, HttpParameterType type);

    /**
     * @param name The name of the parameter to find.
     * @param type The type of the parameter to find.
     *
     * @return {@code true} if a parameter exists that matches the name and type specified. {@code false} if not found.
     */
    boolean hasParameter(String name, HttpParameterType type);

    /**
     * @param parameter An instance of {@link HttpParameter} to match to an existing parameter.
     *
     * @return {@code true} if a parameter exists that matches the data within the provided {@link HttpParameter}. {@code false} if not found.
     */
    boolean hasParameter(HttpParameter parameter);

    /**
     * Obtain the MIME type of the response, as determined by Burp Suite.
     *
     * @return The MIME type.
     */
    MimeType mimeType();

    /**
     * Obtain the MIME type of the response, as stated in the HTTP headers.
     *
     * @return The stated MIME type.
     */
    MimeType statedMimeType();

    /**
     * Obtain the MIME type of the response, as inferred from the contents of the HTTP message body.
     *
     * @return The inferred MIME type.
     */
    MimeType inferredMimeType();

    /**
     * @return List of request markers
     */
    List<Marker> requestMarkers();

    /**
     * @return List of response markers
     */
    List<Marker> responseMarkers();

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

    /**
     * Create a copy of the {@code HttpRequestResponse} in temporary file.<br>
     * This method is used to save the {@code HttpRequestResponse} object to a temporary file,
     * so that it is no longer held in memory. Extensions can use this method to convert
     * {@code HttpRequest} objects into a form suitable for long-term usage.
     *
     * @return A new {@code ByteArray} instance stored in temporary file.
     */
    HttpRequestResponse copyToTempFile();

    /**
     * Create a copy of the {@code HttpRequestResponse} with the added annotations.
     *
     * @param annotations annotations to add.
     *
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withAnnotations(Annotations annotations);

    /**
     * Create a copy of the {@code HttpRequestResponse} with the added request markers.
     *
     * @param requestMarkers Request markers to add.
     *
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withRequestMarkers(List<Marker> requestMarkers);

    /**
     * Create a copy of the {@code HttpRequestResponse} with the added request markers.
     *
     * @param requestMarkers Request markers to add.
     *
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withRequestMarkers(Marker... requestMarkers);

    /**
     * Create a copy of the {@code HttpRequestResponse} with the added response markers.
     *
     * @param responseMarkers Response markers to add.
     *
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withResponseMarkers(List<Marker> responseMarkers);

    /**
     * Create a copy of the {@code HttpRequestResponse} with the added response markers.
     *
     * @param responseMarkers Response markers to add.
     *
     * @return A new {@code HttpRequestResponse} instance.
     */
    HttpRequestResponse withResponseMarkers(Marker... responseMarkers);

    /**
     * Create a new instance of {@link HttpRequestResponse}.<br>
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     *
     * @return A new {@link HttpRequestResponse} instance.
     */
    static HttpRequestResponse httpRequestResponse(HttpRequest request, HttpResponse response)
    {
        return FACTORY.httpRequestResponse(request, response);
    }

    /**
     * Create a new instance of {@link HttpRequestResponse}.<br>
     *
     * @param httpRequest  The HTTP request.
     * @param httpResponse The HTTP response.
     * @param annotations  annotations.
     *
     * @return A new {@link HttpRequestResponse} instance.
     */
    static HttpRequestResponse httpRequestResponse(HttpRequest httpRequest, HttpResponse httpResponse, Annotations annotations)
    {
        return FACTORY.httpRequestResponse(httpRequest, httpResponse, annotations);
    }
}
