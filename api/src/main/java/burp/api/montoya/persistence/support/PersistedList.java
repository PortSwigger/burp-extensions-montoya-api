/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence.support;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.List;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface represents a list that has been persisted in the project.
 * The methods of this list operate on the underlying persisted data.
 */
public interface PersistedList<T> extends List<T>
{
    /**
     * This is a helper method to create a new instance of {@link PersistedList} that contains instances of {@link Boolean}.
     *
     * @return A new {@link PersistedList} instance.
     */
    static PersistedList<Boolean> persistedBooleanList()
    {
        return FACTORY.persistedBooleanList();
    }

    /**
     * This is a helper method to create a new instance of {@link PersistedList} that contains instances of {@link Short}.
     *
     * @return A new {@link PersistedList} instance.
     */
    static PersistedList<Short> persistedShortList()
    {
        return FACTORY.persistedShortList();
    }

    /**
     * This is a helper method to create a new instance of {@link PersistedList} that contains instances of {@link Integer}.
     *
     * @return A new {@link PersistedList} instance.
     */
    static PersistedList<Integer> persistedIntegerList()
    {
        return FACTORY.persistedIntegerList();
    }

    /**
     * This is a helper method to create a new instance of {@link PersistedList} that contains instances of {@link Long}.
     *
     * @return A new {@link PersistedList} instance.
     */
    static PersistedList<Long> persistedLongList()
    {
        return FACTORY.persistedLongList();
    }

    /**
     * This is a helper method to create a new instance of {@link PersistedList} that contains instances of {@link String}.
     *
     * @return A new {@link PersistedList} instance.
     */
    static PersistedList<String> persistedStringList()
    {
        return FACTORY.persistedStringList();
    }

    /**
     * This is a helper method to create a new instance of {@link PersistedList} that contains instances of {@link ByteArray}.
     *
     * @return A new {@link PersistedList} instance.
     */
    static PersistedList<ByteArray> persistedByteArrayList()
    {
        return FACTORY.persistedByteArrayList();
    }

    /**
     * This is a helper method to create a new instance of {@link PersistedList} that contains instances of {@link HttpRequest}.
     *
     * @return A new {@link PersistedList} instance.
     */
    static PersistedList<HttpRequest> persistedHttpRequestList()
    {
        return FACTORY.persistedHttpRequestList();
    }

    /**
     * This is a helper method to create a new instance of {@link PersistedList} that contains instances of {@link HttpResponse}.
     *
     * @return A new {@link PersistedList} instance.
     */
    static PersistedList<HttpResponse> persistedHttpResponseList()
    {
        return FACTORY.persistedHttpResponseList();
    }

    /**
     * This is a helper method to create a new instance of {@link PersistedList} that contains instances of {@link HttpRequestResponse}.
     *
     * @return A new {@link PersistedList} instance.
     */
    static PersistedList<HttpRequestResponse> persistedHttpRequestResponseList()
    {
        return FACTORY.persistedHttpRequestResponseList();
    }
}
