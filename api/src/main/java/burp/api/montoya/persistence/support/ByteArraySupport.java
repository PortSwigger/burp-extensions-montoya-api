/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence.support;

import burp.api.montoya.core.ByteArray;

import java.util.List;

public interface ByteArraySupport
{
    /**
     * Returns the {@link ByteArray} to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    ByteArray getByteArray(String key);

    /**
     * Associates the specified {@code ByteArray} with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced
     * by the specified value.
     *
     * @param key  key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
     * @return a read-only {@code ByteArray} representing the stored data
     */
    ByteArray setByteArray(String key, ByteArray value);

    /**
     * Returns the List of {@link ByteArray} to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    List<ByteArray> getByteArrayList(String key);

    /**
     * Associates the specified List of {@code ByteArray} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key  key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
     * @return A read-only {@code List} of read-only {@code ByteArray}
     * representing the stored data
     */
    List<ByteArray> setByteArrayList(String key, List<ByteArray> value);
}
