/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence.support;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.List;

public interface RequestResponseSupport
{
    /**
     * Returns the {@link HttpRequest} to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    HttpRequest getHttpRequest(String key);

    /**
     * Associates the specified {@link HttpRequest} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key. If this value is {@code null} then any
     *              value with the specified name will be removed.
     * @return {@link HttpRequest} representing the stored data
     */
    HttpRequest setHttpRequest(String key, HttpRequest value);

    /**
     * Returns the List of {@link HttpRequest} to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * new empty list if this map contains no mapping for the key
     */
    List<HttpRequest> getHttpRequestList(String key);

    /**
     * Associates the specified List of {@link HttpRequest} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     * @return A read-only {@link List} of {@link HttpRequest}
     * representing the stored data
     */
    List<HttpRequest> setHttpRequestList(String key, List<HttpRequest> value);

    /**
     * Returns the {@link HttpResponse} to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    HttpResponse getHttpResponse(String key);

    /**
     * Associates the specified {@link HttpResponse} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key. If this value is {@code null} then any
     *              value with the specified name will be removed.
     * @return {@link HttpResponse} representing the stored data
     */
    HttpResponse setHttpResponse(String key, HttpResponse value);

    /**
     * Returns the List of {@link HttpResponse} to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * new empty list if this map contains no mapping for the key
     */
    List<HttpResponse> getHttpResponseList(String key);

    /**
     * Associates the specified List of {@link HttpResponse} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     * @return A read-only {@link List} of {@link HttpResponse}
     * representing the stored data
     */
    List<HttpResponse> setHttpResponseList(String key, List<HttpResponse> value);

    /**
     * Returns the {@link HttpRequestResponse} to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    HttpRequestResponse getHttpRequestResponse(String key);

    /**
     * Associates the specified {@link HttpRequestResponse} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key. If this value is {@code null} then any
     *              value with the specified name will be removed.
     * @return {@link HttpRequestResponse} representing the stored data
     */
    HttpRequestResponse setHttpRequestResponse(String key, HttpRequestResponse value);

    /**
     * Returns the List of {@link HttpRequestResponse} to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * new empty list if this map contains no mapping for the key
     */
    List<HttpRequestResponse> getHttpRequestResponseList(String key);

    /**
     * Associates the specified List of {@link HttpRequestResponse} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     * @return A read-only {@link List} of {@link HttpRequestResponse}
     * representing the stored data
     */
    List<HttpRequestResponse> setHttpRequestResponseList(String key, List<HttpRequestResponse> value);
}
