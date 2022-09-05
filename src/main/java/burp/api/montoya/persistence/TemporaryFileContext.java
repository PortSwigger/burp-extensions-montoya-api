/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

/**
 * This interface represents an instance of a class that allows potentially
 * large objects to be moved to temporary files, so that they are no longer
 * held in memory.
 */
public interface TemporaryFileContext
{
    /**
     * This method is used to save {@link RawData} for the extension.
     *
     * @param key  The key of the {@link RawData}.
     * @param data The data.
     */
    void setRawData(String key, byte[] data);

    /**
     * This method is used to access {@link RawData} for the extension.
     *
     * @param key The key of the {@link RawData}.
     * @return The {@link RawData}, or {@code null} if no value is set.
     */
    RawData getRawData(String key);

    /**
     * This method is used to save a {@link HttpRequest} for the extension.
     *
     * @param key  The key of the {@link HttpRequest}.
     * @param data The {@link HttpRequest}.
     */
    void setHttpRequest(String key, HttpRequest data);

    /**
     * This method is used to access a {@link HttpRequest} for the extension.
     *
     * @param key The key of the {@link HttpRequest}.
     * @return The {@link HttpRequest}, or {@code null} if no value is set.
     */
    HttpRequest getHttpRequest(String key);

    /**
     * This method is used to save a {@link HttpResponse} for the extension.
     *
     * @param key  The key of the {@link HttpResponse}.
     * @param data The {@link HttpResponse}.
     */
    void setHttpResponse(String key, HttpResponse data);

    /**
     * This method is used to access a {@link HttpResponse} for the extension.
     *
     * @param key The key of the {@link HttpResponse}.
     * @return The {@link HttpResponse}, or {@code null} if no value is set.
     */
    HttpResponse getHttpResponse(String key);

    /**
     * This method is used to save a {@link HttpRequestResponse} for the
     * extension.
     *
     * @param key  The key of the {@link HttpRequestResponse}.
     * @param data The {@link HttpRequestResponse}.
     */
    void setHttpRequestResponse(String key, HttpRequestResponse data);

    /**
     * This method is used to access a {@link HttpRequestResponse} for the
     * extension.
     *
     * @param key The key of the {@link HttpRequestResponse}.
     * @return The {@link HttpRequestResponse}, or {@code null} if no value is set.
     */
    HttpRequestResponse getHttpRequestResponse(String key);
}
