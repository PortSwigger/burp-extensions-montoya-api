/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.Cookie;
import burp.api.montoya.http.message.MimeType;
import burp.api.montoya.http.message.StatusCodeClass;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.HttpParameterType;
import burp.api.montoya.http.message.params.ParsedHttpParameter;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.requests.MalformedRequestException;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.List;
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
     * @return True if there are any notes for this HTTP request and response.
     */
    boolean hasNotes();

    /**
     * @return True if there is a highlight color for this HTTP request and response.
     */
    boolean hasHighlightColor();

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
     * @return True if there is a response.
     */
    boolean hasResponse();

    /**
     * @return True if the HTTP request is in-scope.
     */
    boolean isInScope();

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
     * @return the path and file in the request.
     * @throws MalformedRequestException if request is malformed.
     */
    String path();

    /**
     * Request path excluding the query parameters.
     * If the request is malformed, then a {@link MalformedRequestException} is thrown.
     *
     * @return the path excluding query parameters.
     * @throws MalformedRequestException if request is malformed.
     */
    String resourcePath();

    /**
     * @return The hostname or IP address for the service.
     */
    String host();

    /**
     * @return The port number for the service.
     */
    int port();

    /**
     * @return True if a secure protocol is used for the connection, false otherwise.
     */
    boolean secure();

    /**
     * @return The {@code String} representation of the service.
     */
    String httpServiceString();

    /**
     * HTTP Version text parsed from the request line for HTTP 1 messages.
     * HTTP 2 messages will return "HTTP/2".
     *
     * @return Version string.
     */
    String requestHttpVersion();

    /**
     * Body of the issued final request.
     *
     * @return The body of a message as a {@code String}.
     */
    String requestBody();

    /**
     * HTTP status code contained in the response.
     *
     * @return HTTP status code or -1 if there is no response.
     */
    short statusCode();

    /**
     * Test whether the status code is in the specified class.
     *
     * @param statusCodeClass The class of status code to test.
     *
     * @return True if the status code is in the class.
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
     * @return True if the request has parameters of type {@link HttpParameterType}
     */
    boolean hasParameters(HttpParameterType type);

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
     * @return True if the request or response was edited
     */
    boolean edited();

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
