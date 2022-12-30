/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy.http;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.proxy.MessageSendAction;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Extensions can implement this interface when returning a result from
 * {@link ProxyRequestHandler#handleRequestToSend(InterceptedRequest)}.
 */
public interface ProxyRequestToSendAction
{
    /**
     * This method retrieves the current final intercept action.
     *
     * @return The {@link MessageSendAction}.
     */
    MessageSendAction action();

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
     * forward the request.<br>
     * Annotations are not modified.
     *
     * @param request The {@link HttpRequest} to forward after any
     *                modifications by the extension.
     * @return The {@link ProxyRequestToSendAction} that causes Burp Proxy
     * to forward the request.
     */
    static ProxyRequestToSendAction continueWith(HttpRequest request)
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
     * @return The {@link ProxyRequestToSendAction} that causes Burp Proxy
     * to forward the request.
     */
    static ProxyRequestToSendAction continueWith(HttpRequest request, Annotations annotations)
    {
        return FACTORY.requestFinalInterceptResultContinueWith(request, annotations);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * drop the request.
     *
     * @return The {@link ProxyRequestToSendAction} that causes Burp Proxy
     * to drop the request.
     */
    static ProxyRequestToSendAction drop()
    {
        return FACTORY.requestFinalInterceptResultDrop();
    }

    /**
     * This method can be used to create a default implementation of a final
     * intercept result for an HTTP request.
     *
     * @param request     The {@link HttpRequest} to forward after any modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted HTTP request. {@code null} value will leave the annotations unmodified.
     * @param action      The {@link MessageSendAction} for the HTTP request.
     * @return The {@link ProxyRequestToSendAction} including the HTTP
     * request, annotations and final intercept action.
     */
    static ProxyRequestToSendAction proxyRequestToSendAction(HttpRequest request, Annotations annotations, MessageSendAction action)
    {
        return FACTORY.proxyRequestToSendAction(request, annotations, action);
    }
}
