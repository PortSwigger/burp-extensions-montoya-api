/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.intercept;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.requests.HttpRequest;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * An instance of this interface should be returned by {@link HttpHandler#handleHttpRequestToSend} if a custom {@link HttpHandler} has been registered with Burp.
 */
public interface RequestToSendAction
{
    /**
     * @return the action.
     */
    default HttpRequestAction action()
    {
        return HttpRequestAction.CONTINUE;
    }

    /**
     * @return The HTTP request.
     */
    HttpRequest request();

    /**
     * @return The annotations.
     */
    Annotations annotations();

    /**
     * This is a helper method used to create a new instance of {@code RequestResult}. Annotations will not be modified.
     *
     * @param request
     *         An HTTP request.
     *
     * @return A new {@code RequestHandlerResult} instance.
     */
    static RequestToSendAction continueWith(HttpRequest request)
    {
        return FACTORY.requestResult(request);
    }

    /**
     * This is a helper method used to create a new instance of {@code RequestResult}.
     *
     * @param request
     *         An HTTP request.
     * @param annotations
     *         modified annotations.
     *
     * @return A new {@code RequestHandlerResult} instance.
     */
    static RequestToSendAction continueWith(HttpRequest request, Annotations annotations)
    {
        return FACTORY.requestResult(request, annotations);
    }
}
