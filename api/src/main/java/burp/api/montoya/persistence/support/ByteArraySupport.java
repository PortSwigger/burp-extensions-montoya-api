/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence.support;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.persistence.PersistedList;

import java.util.Set;

/**
 * Add support for byte arrays
 */
public interface ByteArraySupport
{
    /**
     * Returns the {@link ByteArray} associated with the specified key,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@code null} if this map contains no mapping for the key
     */
    ByteArray getByteArray(String key);

    /**
     * Associates the specified {@code ByteArray} with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced
     * by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    void setByteArray(String key, ByteArray value);

    /**
     * Removes the mapping from the specified key to the {@link ByteArray}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteByteArray(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link ByteArray} values.
     *
     * @return Set of keys.
     */
    Set<String> byteArrayKeys();

    /**
     * Returns the {@link PersistedList} of {@link ByteArray} associated with the specified key,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@code null} if this map contains no mapping for the key
     */
    PersistedList<ByteArray> getByteArrayList(String key);

    /**
     * Associates the specified {@link PersistedList} of {@code ByteArray} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
     *              The methods of this list operate on the underlying persisted data.
     */
    void setByteArrayList(String key, PersistedList<ByteArray> value);

    /**
     * Removes the mapping from the specified key to the {@link PersistedList} of {@link ByteArray}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteByteArrayList(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link ByteArray} Lists.
     *
     * @return Set of keys.
     */
    Set<String> byteArrayListKeys();
}
