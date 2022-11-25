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
import burp.api.montoya.persistence.PersistedList;

import java.util.Set;

/**
 * Add support for requests and responses
 */
public interface RequestResponseSupport
{
    /**
     * Returns the {@link HttpRequest} associated with the specified key,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@code null} if this map contains no mapping for the key
     */
    HttpRequest getHttpRequest(String key);

    /**
     * Associates the specified {@link HttpRequest} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
     *              If this value is {@code null} then any value with the specified name will be removed.
     */
    void setHttpRequest(String key, HttpRequest value);

    /**
     * Removes the mapping from the specified key to the {@link HttpRequest}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteHttpRequest(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link HttpRequest} values.
     *
     * @return Set of keys.
     */
    Set<String> httpRequestKeys();

    /**
     * Returns the {@link PersistedList} of {@link HttpRequest} associated with the specified key,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@code null} if this map contains no mapping for the key
     */
    PersistedList<HttpRequest> getHttpRequestList(String key);

    /**
     * Associates the specified {@link PersistedList} of {@link HttpRequest} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     *              The methods of this list operate on the underlying persisted data.
     */
    void setHttpRequestList(String key, PersistedList<HttpRequest> value);

    /**
     * Removes the mapping from the specified key to the {@link PersistedList} of {@link HttpRequest}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteHttpRequestList(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link HttpRequest} Lists.
     *
     * @return Set of keys.
     */
    Set<String> httpRequestListKeys();

    /**
     * Returns the {@link HttpResponse} associated with the specified key,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
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
     */
    void setHttpResponse(String key, HttpResponse value);

    /**
     * Removes the mapping from the specified key to the {@link HttpResponse}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteHttpResponse(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link HttpResponse} values.
     *
     * @return Set of keys.
     */
    Set<String> httpResponseKeys();

    /**
     * Returns the {@link PersistedList} of {@link HttpResponse} associated with the specified key,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@code null} if this map contains no mapping for the key
     */
    PersistedList<HttpResponse> getHttpResponseList(String key);

    /**
     * Associates the specified {@link PersistedList} of {@link HttpResponse} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     *              The methods of this list operate on the underlying persisted data.
     */
    void setHttpResponseList(String key, PersistedList<HttpResponse> value);

    /**
     * Removes the mapping from the specified key to the {@link PersistedList} of {@link HttpResponse}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteHttpResponseList(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link HttpResponse} Lists.
     *
     * @return Set of keys.
     */
    Set<String> httpResponseListKeys();

    /**
     * Returns the {@link HttpRequestResponse} associated with the specified key,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
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
     */
    void setHttpRequestResponse(String key, HttpRequestResponse value);

    /**
     * Removes the mapping from the specified key to the {@link HttpRequestResponse}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteHttpRequestResponse(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link HttpRequestResponse} values.
     *
     * @return Set of keys.
     */
    Set<String> httpRequestResponseKeys();

    /**
     * Returns the {@link PersistedList} of {@link HttpRequestResponse} associated with the specified key,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@code null} if this map contains no mapping for the key
     */
    PersistedList<HttpRequestResponse> getHttpRequestResponseList(String key);

    /**
     * Associates the specified {@link PersistedList} of {@link HttpRequestResponse} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     *              The methods of this list operate on the underlying persisted data.
     */
    void setHttpRequestResponseList(String key, PersistedList<HttpRequestResponse> value);

    /**
     * Removes the mapping from the specified key to the {@link PersistedList} of {@link HttpRequestResponse}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteHttpRequestResponseList(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link HttpRequestResponse} Lists.
     *
     * @return Set of keys.
     */
    Set<String> httpRequestResponseListKeys();
}
