/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy.http;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.proxy.MessageSendAction;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Extensions can implement this interface when returning a result from
 * {@link ProxyResponseHandler#handleResponseToSend(InterceptedResponse)}.
 */
public interface ProxyResponseToSendAction
{
    /**
     * This method retrieves the current final intercept action.
     *
     * @return The {@link MessageSendAction}.
     */
    MessageSendAction action();

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

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the response.<br>
     * Annotations are not modified.
     *
     * @param response The {@link HttpResponse} to forward after any
     *                 modifications by the extension.
     *
     * @return The {@link ProxyResponseToSendAction} that causes Burp Proxy
     * to forward the response.
     */
    static ProxyResponseToSendAction continueWith(HttpResponse response)
    {
        return FACTORY.responseFinalInterceptResultContinueWith(response);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * forward the response.
     *
     * @param response    The {@link HttpResponse} to forward after any modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted HTTP response.
     *
     * @return The {@link ProxyResponseToSendAction} that causes Burp Proxy
     * to forward the response.
     */
    static ProxyResponseToSendAction continueWith(HttpResponse response, Annotations annotations)
    {
        return FACTORY.responseFinalInterceptResultContinueWith(response, annotations);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * drop the response.
     *
     * @return The {@link ProxyResponseToSendAction} that causes Burp Proxy
     * to drop the response.
     */
    static ProxyResponseToSendAction drop()
    {
        return FACTORY.responseFinalInterceptResultDrop();
    }

    /**
     * This method can be used to create a default implementation of a final
     * intercept result for an HTTP response.
     *
     * @param response    The {@link HttpResponse} to forward after any modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted HTTP response. {@code null} value will leave the annotations unmodified.
     * @param action      The {@link MessageSendAction} for the HTTP response.
     *
     * @return The {@link ProxyResponseToSendAction} including the HTTP
     * response, annotations and final intercept action.
     */
    static ProxyResponseToSendAction proxyResponseToReturnAction(HttpResponse response, Annotations annotations, MessageSendAction action)
    {
        return FACTORY.proxyResponseToReturnAction(response, annotations, action);
    }
}
