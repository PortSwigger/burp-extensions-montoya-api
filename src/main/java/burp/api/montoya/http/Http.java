/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

import burp.api.montoya.core.Registration;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
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
