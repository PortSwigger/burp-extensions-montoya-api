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
import burp.api.montoya.proxy.SendAction;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Extensions can implement this interface when returning a result from
 * {@link RequestHandler#handleRequestToSend(InterceptedRequest)}.
 */
public interface RequestToSendAction
{
    /**
     * This method retrieves the current final intercept action.
     *
     * @return The {@link SendAction}.
     */
    SendAction action();

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
     * @return The {@link RequestToSendAction} that causes Burp Proxy
     * to forward the request.
     */
    static RequestToSendAction continueWith(HttpRequest request)
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
     * @return The {@link RequestToSendAction} that causes Burp Proxy
     * to forward the request.
     */
    static RequestToSendAction continueWith(HttpRequest request, Annotations annotations)
    {
        return FACTORY.requestFinalInterceptResultContinueWith(request, annotations);
    }

    /**
     * This method can be used to create a result that causes Burp Proxy to
     * drop the request.
     *
     * @return The {@link RequestToSendAction} that causes Burp Proxy
     * to drop the request.
     */
    static RequestToSendAction drop()
    {
        return FACTORY.requestFinalInterceptResultDrop();
    }

    /**
     * This method can be used to create a default implementation of a final
     * intercept result for an HTTP request.
     *
     * @param request     The {@link HttpRequest} to forward after any modifications by the extension.
     * @param annotations The {@link Annotations} for the intercepted HTTP request. {@code null} value will leave the annotations unmodified.
     * @param action      The {@link SendAction} for the HTTP request.
     * @return The {@link RequestToSendAction} including the HTTP
     * request, annotations and final intercept action.
     */
    static RequestToSendAction requestToSendAction(HttpRequest request, Annotations annotations, SendAction action)
    {
        return FACTORY.requestToSendAction(request, annotations, action);
    }
}
