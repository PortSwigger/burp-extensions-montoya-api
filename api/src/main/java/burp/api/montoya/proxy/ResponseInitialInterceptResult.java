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
import burp.api.montoya.http.message.responses.HttpResponse;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;
import static burp.api.montoya.proxy.InitialInterceptAction.DO_NOT_INTERCEPT;
import static burp.api.montoya.proxy.InitialInterceptAction.DROP;
import static burp.api.montoya.proxy.InitialInterceptAction.FOLLOW_USER_RULES;
import static burp.api.montoya.proxy.InitialInterceptAction.INTERCEPT;

/**
 * Extensions can implement this interface when returning a result from
 * {@link ProxyHttpResponseHandler#handleReceivedResponse(InterceptedHttpResponse, HttpRequest, Annotations)}.
 */
public interface ResponseInitialInterceptResult
{
    /**
     * This method can be used to create a result that causes Burp Proxy to
     * present the response to the user for manual review or modification.
     *
     * @param response The {@link HttpResponse} received after any
     *                 modifications by the extension.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to present the response to the user for manual review or
     * modification.
     */
    static ResponseInitialInterceptResult intercept(HttpResponse response)
    {
        return initialInterceptResult(response, Annotations.annotations(), INTERCEPT);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * present the response to the user for manual review or modification.
     *
     * @param response   The {@link HttpResponse} received after any
     *                   modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                   HTTP response.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to present the response to the user for manual review or
     * modification.
     */
    static ResponseInitialInterceptResult intercept(HttpResponse response, Annotations annotations)
    {
        return initialInterceptResult(response, annotations, INTERCEPT);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the response without presenting it to the user.
     *
     * @param response The {@link HttpResponse} received after any
     *                 modifications by the extension.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to forward the response without presenting it to the user.
     */
    static ResponseInitialInterceptResult doNotIntercept(HttpResponse response)
    {
        return initialInterceptResult(response, Annotations.annotations(), DO_NOT_INTERCEPT);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the response without presenting it to the user.
     *
     * @param response   The {@link HttpResponse} received after any
     *                   modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                   HTTP response.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to forward the response without presenting it to the user.
     */
    static ResponseInitialInterceptResult doNotIntercept(HttpResponse response, Annotations annotations)
    {
        return initialInterceptResult(response, annotations, DO_NOT_INTERCEPT);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * follow the current interception rules to determine the appropriate
     * action to take for the response.
     *
     * @param response The {@link HttpResponse} received after any
     *                 modifications by the extension.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to follow the current interception rules to determine the
     * appropriate action to take for the response.
     */
    static ResponseInitialInterceptResult followUserRules(HttpResponse response)
    {
        return initialInterceptResult(response, Annotations.annotations(), FOLLOW_USER_RULES);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * follow the current interception rules to determine the appropriate
     * action to take for the response.
     *
     * @param response   The {@link HttpResponse} received after any
     *                   modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                   HTTP response.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to follow the current interception rules to determine the
     * appropriate action to take for the response.
     */
    static ResponseInitialInterceptResult followUserRules(HttpResponse response, Annotations annotations)
    {
        return initialInterceptResult(response, annotations, FOLLOW_USER_RULES);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * drop the response.
     *
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to drop the response.
     */
    static ResponseInitialInterceptResult drop()
    {
        return initialInterceptResult(null, Annotations.annotations(), DROP);
    }

    /**
     * This method can be used to create a default implementation of an initial
     * intercept result for an HTTP response.
     *
     * @param response   The {@link HttpResponse} received after any
     *                   modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                   HTTP response.
     * @param action     The {@link InitialInterceptAction} for the HTTP response.
     * @return The {@link ResponseInitialInterceptResult} including the HTTP
     * response, annotations and initial intercept action.
     */
    static ResponseInitialInterceptResult initialInterceptResult(HttpResponse response, Annotations annotations, InitialInterceptAction action)
    {
        return FACTORY.initialInterceptResult(response, annotations, action);
    }

    /**
     * This method retrieves the current initial intercept action.
     *
     * @return The {@link InitialInterceptAction}.
     */
    InitialInterceptAction action();

    /**
     * This method retrieves the current HTTP response to forward after any
     * modifications by the extension.
     *
     * @return The {@link HttpResponse} to forward after any modifications by
     * the extension.
     */
    HttpResponse response();

    /**
     * This method retrieves the annotations for the current response after any
     * modifications by the extension.
     *
     * @return The {@link Annotations} for the intercepted HTTP response.
     */
    Annotations annotations();
}
