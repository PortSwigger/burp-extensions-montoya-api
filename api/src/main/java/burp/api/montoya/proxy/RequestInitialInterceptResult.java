/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.requests.HttpRequest;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Extensions can implement this interface when returning a result from
 * {@link ProxyHttpRequestHandler#handleReceivedRequest(InterceptedHttpRequest)}.
 */
public interface RequestInitialInterceptResult
{
    /**
     * This method retrieves the current initial intercept action.
     *
     * @return The {@link InitialInterceptAction}.
     */
    InitialInterceptAction action();

    /**
     * This method retrieves the current HTTP request to forward after any
     * modifications by the extension.
     *
     * @return The {@link HttpRequest} to forward after any modifications by
     * the extension.
     */
    HttpRequest request();

    /**
     * This method retrieves the annotations for the current request after any
     * modifications by the extension.
     *
     * @return The {@link Annotations} for the intercepted HTTP request.
     */
    Annotations annotations();

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * present the request to the user for manual review or modification.
     *
     * @param request The {@link HttpRequest} received after any modifications
     *                by the extension.
     * @return The {@link RequestInitialInterceptResult} that causes Burp Proxy
     * to present the request to the user for manual review or modification.
     */
    static RequestInitialInterceptResult intercept(HttpRequest request)
    {
        return FACTORY.requestInitialInterceptResultIntercept(request);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * present the request to the user for manual review or modification.
     *
     * @param request    The {@link HttpRequest} received after any modifications
     *                   by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                   HTTP request.
     * @return The {@link RequestInitialInterceptResult} that causes Burp Proxy
     * to present the request to the user for manual review or modification.
     */
    static RequestInitialInterceptResult intercept(HttpRequest request, Annotations annotations)
    {
        return FACTORY.requestInitialInterceptResultIntercept(request, annotations);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the request without presenting it to the user.
     *
     * @param request The {@link HttpRequest} received after any modifications
     *                by the extension.
     * @return The {@link RequestInitialInterceptResult} that causes Burp Proxy
     * to forward the request without presenting it to the user.
     */
    static RequestInitialInterceptResult doNotIntercept(HttpRequest request)
    {
        return FACTORY.requestInitialInterceptResultDoNotIntercept(request);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the request without presenting it to the user.
     *
     * @param request    The {@link HttpRequest} received after any modifications
     *                   by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                   HTTP request.
     * @return The {@link RequestInitialInterceptResult} that causes Burp Proxy
     * to forward the request without presenting it to the user.
     */
    static RequestInitialInterceptResult doNotIntercept(HttpRequest request, Annotations annotations)
    {
        return FACTORY.requestInitialInterceptResultDoNotIntercept(request, annotations);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * follow the current interception rules to determine the appropriate
     * action to take for the request.
     *
     * @param request The {@link HttpRequest} received after any modifications
     *                by the extension.
     * @return The {@link RequestInitialInterceptResult} that allows user rules to be
     * followed.
     */
    static RequestInitialInterceptResult followUserRules(HttpRequest request)
    {
        return FACTORY.requestInitialInterceptResultFollowUserRules(request);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * follow the current interception rules to determine the appropriate
     * action to take for the request.
     *
     * @param request    The {@link HttpRequest} received after any modifications
     *                   by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                   HTTP request.
     * @return The {@link RequestInitialInterceptResult} that causes Burp Proxy
     * to follow the current interception rules to determine the appropriate
     * action to take for the request.
     */
    static RequestInitialInterceptResult followUserRules(HttpRequest request, Annotations annotations)
    {
        return FACTORY.requestInitialInterceptResultFollowUserRules(request, annotations);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * drop the request.
     *
     * @return The {@link RequestInitialInterceptResult} that causes Burp Proxy
     * to drop the request.
     */
    static RequestInitialInterceptResult drop()
    {
        return FACTORY.requestInitialInterceptResultDrop();
    }

    /**
     * This method can be used to create a default implementation of an initial
     * intercept result for an HTTP request.
     *
     * @param request    The {@link HttpRequest} received after any modifications
     *                   by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                   HTTP request.
     * @param action     The {@link InitialInterceptAction} for the HTTP request.
     * @return The {@link RequestInitialInterceptResult} including the HTTP
     * request, annotations and initial intercept action.
     */
    static RequestInitialInterceptResult initialInterceptResult(HttpRequest request, Annotations annotations, InitialInterceptAction action)
    {
        return FACTORY.initialInterceptResult(request, annotations, action);
    }
}
