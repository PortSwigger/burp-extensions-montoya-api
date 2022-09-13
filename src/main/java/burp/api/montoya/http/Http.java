/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

import burp.api.montoya.core.MessageAnnotations;
import burp.api.montoya.core.Registration;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.MarkedHttpRequestResponse;
import burp.api.montoya.http.message.headers.HttpHeader;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.HttpParameterType;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.http.message.responses.analysis.ResponseKeywordsAnalyzer;
import burp.api.montoya.http.message.responses.analysis.ResponseVariationsAnalyzer;
import burp.api.montoya.http.sessions.CookieJar;
import burp.api.montoya.http.sessions.SessionHandlingAction;

import java.util.List;

/**
 * This interface is used to access HTTP related functionality of Burp.
 */
public interface Http
{
    /**
     * This method is used to register a handler which will perform an action when a request is about to be issued
     * or a response was received by any Burp tool.
     *
     * @param handler An object created by the extension that implements {@link HttpHandler} interface.
     * @return The {@link Registration} for the handler.
     */
    Registration registerHttpHandler(HttpHandler handler);

    /**
     * This method is used to register a custom session handling action. Each registered action will be available
     * within the session handling rule UI for the user to select as a rule action. Users can choose to invoke an
     * action directly in its own right, or following execution of a macro.
     *
     * @param action An object created by the extension that implements {@link SessionHandlingAction} interface.
     * @return The {@link Registration} for the handler.
     */
    Registration registerSessionHandlingAction(SessionHandlingAction action);

    /**
     * Create a new instance of {@code HttpHeader} from name and value.
     *
     * @param name The name of the header.
     * @param value The value of the header.
     * @return A new {@code HttpHeader} instance.
     */
    HttpHeader createHeader(String name, String value);

    /**
     * Create a new instance of HttpHeader from a {@code String} header representation.
     *
     * @param header The {@code String} header representation.
     * @return A new {@code HttpHeader} instance.
     */
    HttpHeader createHeader(String header);

    /**
     * Create a new instance of {@code HttpService}.
     *
     * @param baseUrl The URL for the service.
     * @return A new {@code HttpService} instance.
     * @throws IllegalArgumentException If the provided URL is invalid.
     */
    HttpService createService(String baseUrl);

    /**
     * Create a new instance of {@code HttpService}.
     *
     * @param host The hostname or IP address for the service.
     * @param secure True is the secure connection is to be used.
     * @return A new {@code HttpService} instance.
     */
    HttpService createService(String host, boolean secure);

    /**
     * Create a new instance of {@code HttpService}.
     *
     * @param host The hostname or IP address for the service.
     * @param port The port number for the service.
     * @param secure True is the secure connection is to be used.
     * @return A new {@code HttpService} instance.
     */
    HttpService createService(String host, int port, boolean secure);

    /**
     * Create a new Instance of {@code HttpParameter} with {@link HttpParameterType#URL} type.
     *
     * @param name The parameter name.
     * @param value The parameter value.
     * @return A new {@code HttpParameter} instance.
     */
    default HttpParameter createUrlParameter(String name, String value) {
        return createParameter(name, value, HttpParameterType.URL);
    }

    /**
     * Create a new Instance of {@code HttpParameter} with {@link HttpParameterType#BODY} type.
     *
     * @param name The parameter name.
     * @param value The parameter value.
     * @return A new {@code HttpParameter} instance.
     */
    default HttpParameter createBodyParameter(String name, String value) {
        return createParameter(name, value, HttpParameterType.BODY);
    }

    /**
     * Create a new Instance of {@code HttpParameter} with {@link HttpParameterType#COOKIE} type.
     *
     * @param name The parameter name.
     * @param value The parameter value.
     * @return A new {@code HttpParameter} instance.
     */
    default HttpParameter createCookieParameter(String name, String value) {
        return createParameter(name, value, HttpParameterType.COOKIE);
    }

    /**
     * Create a new Instance of {@code HttpParameter} with the specified type.
     *
     * @param name The parameter name.
     * @param value The parameter value.
     * @param type The header type.
     * @return A new {@code HttpParameter} instance.
     */
    HttpParameter createParameter(String name, String value, HttpParameterType type);

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param request The HTTP request
     * @return A new {@link HttpRequest} instance.
     */
    default HttpRequest createRequest(byte[] request)
    {
        return createRequest(null, request);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param request The HTTP request.
     * @return A new {@link HttpRequest} instance.
     */
    default HttpRequest createRequest(String request)
    {
        return createRequest(null, request);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param service An HTTP service for the request.
     * @param request The HTTP request.
     * @return A new {@link HttpRequest} instance. A new {@link HttpRequest} instance.
     */
    HttpRequest createRequest(HttpService service, byte[] request);

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param service An HTTP service for the request.
     * @param request The HTTP request.
     * @return A new {@link HttpRequest} instance.
     */
    HttpRequest createRequest(HttpService service, String request);

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param service An HTTP service for the request.
     * @param headers A list of HTTP headers.
     * @param body    A body of the HTTP request.
     * @return A new {@link HttpRequest} instance.
     */
    HttpRequest createRequest(HttpService service, List<String> headers, byte[] body);

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param service An HTTP service for the request.
     * @param headers A list of HTTP headers.
     * @param body    A body of the HTTP request.
     * @return A new {@link HttpRequest} instance.
     */
    HttpRequest createRequest(HttpService service, List<String> headers, String body);

    /**
     * This is a helper method to create a new instance of {@link HttpRequest} that will only contain
     * the data provided in the arguments.
     *
     * @param service An HTTP service for the request.
     * @param headers A list of HTTP headers.
     * @param body    A body of the HTTP request.
     * @return A new {@link HttpRequest} instance.
     */
    HttpRequest createVerbatimRequest(HttpService service, List<HttpHeader> headers, byte[] body);

    /**
     * This is a helper method to create a new instance of {@link HttpRequest} that will only contain
     * the data provided in the arguments.
     *
     * @param service An HTTP service for the request.
     * @param headers A list of HTTP headers.
     * @param body    A body of the HTTP request.
     * @return A new {@link HttpRequest} instance.
     */
    HttpRequest createVerbatimRequest(HttpService service, List<HttpHeader> headers, String body);

    /**
     * This is a helper method to create a new instance of {@link HttpRequest}.
     *
     * @param url A URL for the request.
     * @return A new {@link HttpRequest} instance.
     */
    HttpRequest createRequestFromUrl(String url);

    /**
     * This is a helper method to create a new instance of {@link HttpResponse}.
     *
     * @param response The HTTP response.
     * @return A new {@link HttpResponse} instance.
     */
    HttpResponse createResponse(byte[] response);

    /**
     * This is a helper method to create a new instance of {@link HttpResponse}.
     *
     * @param response The HTTP response.
     * @return A new {@link HttpResponse} instance.
     */
    HttpResponse createResponse(String response);

    /**
     * This is a helper method to create a new instance of {@link HttpResponse}.
     *
     * @param headers A list of HTTP headers.
     * @param body    An HTTP response body.
     * @return A new {@link HttpResponse} instance.
     */
    HttpResponse createResponse(List<String> headers, byte[] body);

    /**
     * This is a helper method to create a new instance of {@link HttpResponse}.
     *
     * @param headers A list of HTTP headers.
     * @param body    An HTTP response body.
     * @return A new {@link HttpResponse} instance.
     */
    HttpResponse createResponse(List<String> headers, String body);

    /**
     * This is a helper method to create a new instance of {@link HttpRequestResponse}.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A new {@link HttpRequestResponse} instance.
     */
    default HttpRequestResponse createRequestResponse(HttpRequest request, HttpResponse response)
    {
        return createRequestResponse(request, response, MessageAnnotations.NONE);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpRequestResponse}.
     *
     * @param httpRequest        The HTTP request.
     * @param httpResponse       The HTTP response.
     * @param messageAnnotations Message annotations.
     * @return A new {@link HttpRequestResponse} instance.
     */
    HttpRequestResponse createRequestResponse(HttpRequest httpRequest, HttpResponse httpResponse, MessageAnnotations messageAnnotations);

    /**
     * This is a helper method to create a new instance of {@link MarkedHttpRequestResponse}.
     *
     * @param httpRequest        The HTTP request.
     * @param httpResponse       The HTTP response.
     * @return A new {@link MarkedHttpRequestResponse} instance.
     */
    default MarkedHttpRequestResponse createMarkedRequestResponse(HttpRequest httpRequest, HttpResponse httpResponse)
    {
        return createMarkedRequestResponse(httpRequest, httpResponse, MessageAnnotations.NONE);
    }

    /**
     * This is a helper method to create a new instance of {@link MarkedHttpRequestResponse}.
     *
     * @param httpRequest        The HTTP request.
     * @param httpResponse       The HTTP response.
     * @param messageAnnotations Message annotations.
     * @return A new {@link MarkedHttpRequestResponse} instance.
     */
    MarkedHttpRequestResponse createMarkedRequestResponse(HttpRequest httpRequest, HttpResponse httpResponse, MessageAnnotations messageAnnotations);

    /**
     * This method can be used to issue HTTP requests and retrieve their responses.
     *
     * @param request The full HTTP request.
     * @return An object that implements the {@link HttpRequestResponse} interface, and which the extension can query to obtain the details of the response.
     */
    default HttpRequestResponse issueRequest(HttpRequest request)
    {
        return issueRequest(request, HttpMode.AUTO, null);
    }

    /**
     * This method can be used to issue HTTP requests and retrieve their responses.
     *
     * @param request  The full HTTP request.
     * @param httpMode An {@link HttpMode} enum value which indicates how a request should be sent.
     * @return An object that implements the {@link HttpRequestResponse} interface, and which the extension can query to obtain the details of the response.
     */
    default HttpRequestResponse issueRequest(HttpRequest request, HttpMode httpMode)
    {
        return issueRequest(request, httpMode, null);
    }

    /**
     * This method can be used to issue HTTP requests and retrieve their responses.
     *
     * @param request      The full HTTP request.
     * @param httpMode     An {@link HttpMode} enum value which indicates how a request should be sent.
     * @param connectionId The identifier for the connection you want to use.
     * @return An object that implements the {@link HttpRequestResponse} interface, and which the extension can query to obtain the details of the response.
     */
    HttpRequestResponse issueRequest(HttpRequest request, HttpMode httpMode, String connectionId);

    /**
     * This method is used to create a new response keyword analyzer.
     *
     * @param keywords A list of keywords the analyzer will look for.
     * @return A new {@link ResponseKeywordsAnalyzer} instance.
     */
    ResponseKeywordsAnalyzer createResponseKeywordsAnalyzer(List<String> keywords);

    /**
     * This method is used to create a new response variations analyzer.
     *
     * @return A new {@link ResponseKeywordsAnalyzer} instance.
     */
    ResponseVariationsAnalyzer createResponseVariationsAnalyzer();

    /**
     * This method is used to access the Cookie Jar.
     *
     * @return The {@link CookieJar} instance.
     */
    CookieJar cookieJar();
}
