/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.MessageAnnotations;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import static burp.api.montoya.proxy.InitialInterceptAction.DO_NOT_INTERCEPT;
import static burp.api.montoya.proxy.InitialInterceptAction.DROP;
import static burp.api.montoya.proxy.InitialInterceptAction.FOLLOW_USER_RULES;
import static burp.api.montoya.proxy.InitialInterceptAction.INTERCEPT;

/**
 * Extensions can implement this interface when returning a result from
 * {@link ProxyHttpResponseHandler#handleReceivedResponse(InterceptedHttpResponse, HttpRequest, MessageAnnotations)}.
 */
public interface ResponseInitialInterceptResult
{
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
     * @return The {@link MessageAnnotations} for the intercepted HTTP response.
     */
    MessageAnnotations annotations();

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * present the response to the user for manual review or modification.
     *
     * @param response The {@link HttpResponse} received after any
     * modifications by the extension.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to present the response to the user for manual review or
     * modification.
     */
    static ResponseInitialInterceptResult intercept(HttpResponse response)
    {
        return from(response, MessageAnnotations.NONE, INTERCEPT);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * present the response to the user for manual review or modification.
     *
     * @param response The {@link HttpResponse} received after any
     * modifications by the extension.
     * @param annotations The {@link MessageAnnotations} for the intercepted
     * HTTP response.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to present the response to the user for manual review or
     * modification.
     */
    static ResponseInitialInterceptResult intercept(HttpResponse response, MessageAnnotations annotations)
    {
        return from(response, annotations, INTERCEPT);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the response without presenting it to the user.
     *
     * @param response The {@link HttpResponse} received after any
     * modifications by the extension.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to forward the response without presenting it to the user.
     */
    static ResponseInitialInterceptResult doNotIntercept(HttpResponse response)
    {
        return from(response, MessageAnnotations.NONE, DO_NOT_INTERCEPT);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the response without presenting it to the user.
     *
     * @param response The {@link HttpResponse} received after any
     * modifications by the extension.
     * @param annotations The {@link MessageAnnotations} for the intercepted
     * HTTP response.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to forward the response without presenting it to the user.
     */
    static ResponseInitialInterceptResult doNotIntercept(HttpResponse response, MessageAnnotations annotations)
    {
        return from(response, annotations, DO_NOT_INTERCEPT);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * follow the current interception rules to determine the appropriate
     * action to take for the response.
     *
     * @param response The {@link HttpResponse} received after any
     * modifications by the extension.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to follow the current interception rules to determine the
     * appropriate action to take for the response.
     */
    static ResponseInitialInterceptResult followUserRules(HttpResponse response)
    {
        return from(response, MessageAnnotations.NONE, FOLLOW_USER_RULES);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * follow the current interception rules to determine the appropriate
     * action to take for the response.
     *
     * @param response The {@link HttpResponse} received after any
     * modifications by the extension.
     * @param annotations The {@link MessageAnnotations} for the intercepted
     * HTTP response.
     * @return The {@link ResponseInitialInterceptResult} that causes Burp
     * Proxy to follow the current interception rules to determine the
     * appropriate action to take for the response.
     */
    static ResponseInitialInterceptResult followUserRules(HttpResponse response, MessageAnnotations annotations)
    {
        return from(response, annotations, FOLLOW_USER_RULES);
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
        return from(null, MessageAnnotations.NONE, DROP);
    }

    /**
     * This method can be used to create a default implementation of an initial
     * intercept result for an HTTP response.
     *
     * @param response The {@link HttpResponse} received after any
     * modifications by the extension.
     * @param annotations The {@link MessageAnnotations} for the intercepted
     * HTTP response.
     * @param action The {@link InitialInterceptAction} for the HTTP response.
     * @return The {@link ResponseInitialInterceptResult} including the HTTP
     * response, annotations and initial intercept action.
     */
    static ResponseInitialInterceptResult from(HttpResponse response, MessageAnnotations annotations, InitialInterceptAction action)
    {
        return new ResponseInitialInterceptResult()
        {
            @Override
            public InitialInterceptAction action()
            {
                return action;
            }

            @Override
            public HttpResponse response()
            {
                return response;
            }

            @Override
            public MessageAnnotations annotations()
            {
                return annotations;
            }
        };
    }
}
