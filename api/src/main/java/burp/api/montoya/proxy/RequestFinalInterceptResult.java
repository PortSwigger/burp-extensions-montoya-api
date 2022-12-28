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
 * {@link ProxyHttpRequestHandler#handleRequestToIssue(InterceptedHttpRequest)}.
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
     * @return The {@link Annotations} for the intercepted HTTP request.
     */
    Annotations annotations();

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the request.
     *
     * @param request The {@link HttpRequest} to forward after any
     *                modifications by the extension.
     * @return The {@link RequestFinalInterceptResult} that causes Burp Proxy
     * to forward the request.
     */
    static RequestFinalInterceptResult continueWith(HttpRequest request)
    {
        return FACTORY.requestFinalInterceptResultContinueWith(request);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the request.
     *
     * @param request     The {@link HttpRequest} to forward after any
     *                    modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                    HTTP request.
     * @return The {@link RequestFinalInterceptResult} that causes Burp Proxy
     * to forward the request.
     */
    static RequestFinalInterceptResult continueWith(HttpRequest request, Annotations annotations)
    {
        return FACTORY.requestFinalInterceptResultContinueWith(request, annotations);
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
        return FACTORY.requestFinalInterceptResultDrop();
    }

    /**
     * This method can be used to create a default implementation of a final
     * intercept result for an HTTP request.
     *
     * @param request     The {@link HttpRequest} to forward after any
     *                    modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                    HTTP request. {@code null} will leave the annotations unmodified.
     * @param action      The {@link FinalInterceptAction} for the HTTP request.
     * @return The {@link RequestFinalInterceptResult} including the HTTP
     * request, annotations and final intercept action.
     */
    static RequestFinalInterceptResult finalInterceptResult(HttpRequest request, Annotations annotations, FinalInterceptAction action)
    {
        return FACTORY.finalInterceptResult(request, annotations, action);
    }
}
