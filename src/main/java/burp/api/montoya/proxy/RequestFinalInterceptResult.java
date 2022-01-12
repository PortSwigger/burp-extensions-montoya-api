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

import static burp.api.montoya.proxy.FinalInterceptAction.CONTINUE;
import static burp.api.montoya.proxy.FinalInterceptAction.DROP;

/**
 * Extensions can implement this interface when returning a result from
 * {@link ProxyHttpRequestHandler#handleRequestToIssue(InterceptedHttpRequest, MessageAnnotations)}.
 */
public interface RequestFinalInterceptResult
{
    /**
     * This method retrieves the current final intercept action.
     *
     * @return The {@link FinalInterceptAction}.
     */
    FinalInterceptAction action();

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
     * @return The {@link MessageAnnotations} for the intercepted HTTP request.
     */
    MessageAnnotations annotations();

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the request.
     *
     * @param request The {@link HttpRequest} to forward after any
     * modifications by the extension.
     * @return The {@link RequestFinalInterceptResult} that causes Burp Proxy
     * to forward the request.
     */
    static RequestFinalInterceptResult continueWith(HttpRequest request)
    {
        return from(request, MessageAnnotations.NONE, CONTINUE);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the request.
     *
     * @param request The {@link HttpRequest} to forward after any
     * modifications by the extension.
     * @param annotations The {@link MessageAnnotations} for the intercepted
     * HTTP request.
     * @return The {@link RequestFinalInterceptResult} that causes Burp Proxy
     * to forward the request.
     */
    static RequestFinalInterceptResult continueWith(HttpRequest request, MessageAnnotations annotations)
    {
        return from(request, annotations, CONTINUE);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * drop the request.
     *
     * @return The {@link RequestFinalInterceptResult} that causes Burp Proxy
     * to drop the request.
     */
    static RequestFinalInterceptResult drop()
    {
        return from(null, MessageAnnotations.NONE, DROP);
    }

    /**
     * This method can be used to create a default implementation of a final
     * intercept result for an HTTP request.
     *
     * @param request The {@link HttpRequest} to forward after any
     * modifications by the extension.
     * @param annotations The {@link MessageAnnotations} for the intercepted
     * HTTP request.
     * @param action The {@link FinalInterceptAction} for the HTTP request.
     * @return The {@link RequestFinalInterceptResult} including the HTTP
     * request, annotations and final intercept action.
     */
    static RequestFinalInterceptResult from(HttpRequest request, MessageAnnotations annotations, FinalInterceptAction action)
    {
        return new RequestFinalInterceptResult()
        {
            @Override
            public FinalInterceptAction action()
            {
                return action;
            }

            @Override
            public HttpRequest request()
            {
                return request;
            }

            @Override
            public MessageAnnotations annotations()
            {
                return annotations;
            }
        };
    }
}
