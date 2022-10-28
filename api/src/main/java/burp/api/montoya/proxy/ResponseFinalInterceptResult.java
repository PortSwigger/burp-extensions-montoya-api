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
import static burp.api.montoya.proxy.FinalInterceptAction.CONTINUE;
import static burp.api.montoya.proxy.FinalInterceptAction.DROP;

/**
 * Extensions can implement this interface when returning a result from
 * {@link ProxyHttpResponseHandler#handleResponseToReturn(InterceptedHttpResponse, HttpRequest, Annotations)}.
 */
public interface ResponseFinalInterceptResult
{
    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the response.
     *
     * @param response The {@link HttpResponse} to forward after any
     *                 modifications by the extension.
     * @return The {@link ResponseFinalInterceptResult} that causes Burp Proxy
     * to forward the response.
     */
    static ResponseFinalInterceptResult continueWith(HttpResponse response)
    {
        return finalInterceptResult(response, Annotations.annotations(), CONTINUE);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the response.
     *
     * @param response   The {@link HttpResponse} to forward after any
     *                   modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                   HTTP response.
     * @return The {@link ResponseFinalInterceptResult} that causes Burp Proxy
     * to forward the response.
     */
    static ResponseFinalInterceptResult continueWith(HttpResponse response, Annotations annotations)
    {
        return finalInterceptResult(response, annotations, CONTINUE);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * drop the response.
     *
     * @return The {@link ResponseFinalInterceptResult} that causes Burp Proxy
     * to drop the response.
     */
    static ResponseFinalInterceptResult drop()
    {
        return finalInterceptResult(null, Annotations.annotations(), DROP);
    }

    /**
     * This method can be used to create a default implementation of a final
     * intercept result for an HTTP response.
     *
     * @param response   The {@link HttpResponse} to forward after any
     *                   modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted
     *                   HTTP response.
     * @param action     The {@link FinalInterceptAction} for the HTTP response.
     * @return The {@link ResponseFinalInterceptResult} including the HTTP
     * response, annotations and final intercept action.
     */
    static ResponseFinalInterceptResult finalInterceptResult(HttpResponse response, Annotations annotations, FinalInterceptAction action)
    {
        return FACTORY.finalInterceptResult(response, annotations, action);
    }

    /**
     * This method retrieves the current final intercept action.
     *
     * @return The {@link FinalInterceptAction}.
     */
    FinalInterceptAction action();

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
