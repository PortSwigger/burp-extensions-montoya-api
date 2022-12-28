/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.responses.HttpResponse;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * An instance of this interface should be returned by {@link HttpHandler#handleHttpResponse} if a custom
 * {@link HttpHandler} has been registered with Burp.
 */
public interface ResponseResult
{
    /**
     * @return The HTTP response.
     */
    HttpResponse response();

    /**
     * @return The annotations.
     */
    Annotations annotations();


    /**
     * This is a helper method used to create a new instance of {@code ResponseResult}.
     * Response and Annotations will not be modified.
     *
     * @return A new {@code ResponseResult} instance.
     */
    static ResponseResult responseResult()
    {
        return FACTORY.responseResult();
    }

    /**
     * This is a helper method used to create a new instance of {@code ResponseResult}.
     * Annotations will not be modified.
     *
     * @param response An HTTP response.
     * @return A new {@code ResponseResult} instance.
     */
    static ResponseResult responseResult(HttpResponse response)
    {
        return FACTORY.responseResult(response);
    }

    /**
     * This is a helper method used to create a new instance of {@code ResponseResult}.
     *
     * @param response    An HTTP response.
     * @param annotations modified annotations.
     * @return A new {@code ResponseResult} instance.
     */
    static ResponseResult responseResult(HttpResponse response, Annotations annotations)
    {
        return FACTORY.responseResult(response, annotations);
    }
}
