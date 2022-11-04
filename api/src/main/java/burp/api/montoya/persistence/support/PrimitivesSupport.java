/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence.support;

import java.util.Optional;
import java.util.Set;

public interface PrimitivesSupport
{
    /**
     * Returns the {@link Boolean} associated with the specified key,
     * or {@link Optional#empty} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<Boolean> getBoolean(String key);

    /**
     * Associates the specified {@link Boolean} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
     * If this value is {@code null} then any value with the specified key will be removed.
     */
    void setBoolean(String key, Boolean value);

    /**
     * Removes the mapping from the specified key to the {@link Boolean}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteBoolean(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link Boolean} values.
     *
     * @return Set of keys.
     */
    Set<String> booleanKeys();

    /**
     * Returns the {@link Byte} associated with the specified key,
     * or {@link Optional#empty} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<Byte> getByte(String key);

    /**
     * Associates the specified {@link Byte} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
     * If this value is {@code null} then any value with the specified key will be removed.
     */
    void setByte(String key, Byte value);

    /**
     * Removes the mapping from the specified key to the {@link Byte}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteByte(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link Byte} values.
     *
     * @return Set of keys.
     */
    Set<String> byteKeys();

    /**
     * Returns the {@link Short} associated with the specified key,
     * or {@link Optional#empty} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<Short> getShort(String key);

    /**
     * Associates the specified {@link Short} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
     * If this value is {@code null} then any value with the specified key will be removed.
     */
    void setShort(String key, Short value);

    /**
     * Removes the mapping from the specified key to the {@link Short}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteShort(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link Short} values.
     *
     * @return Set of keys.
     */
    Set<String> shortKeys();

    /**
     * Returns the {@link Integer} associated with the specified key,
     * or {@link Optional#empty} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<Integer> getInteger(String key);

    /**
     * Associates the specified {@link Integer} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
     * If this value is {@code null} then any value with the specified key will be removed.
     */
    void setInteger(String key, Integer value);

    /**
     * Removes the mapping from the specified key to the {@link Integer}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteInteger(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link Integer} values.
     *
     * @return Set of keys.
     */
    Set<String> integerKeys();

    /**
     * Returns the {@link Long} associated with the specified key,
     * or {@link Optional#empty}} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<Long> getLong(String key);

    /**
     * Associates the specified {@link Long} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
     * If this value is {@code null} then any value with the specified key will be removed.
     */
    void setLong(String key, Long value);

    /**
     * Removes the mapping from the specified key to the {@link Long}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteLong(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link Long} values.
     *
     * @return Set of keys.
     */
    Set<String> longKeys();

    /**
     * Returns the {@link String} associated with the specified key,
     * or {@link Optional#empty} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<String> getString(String key);

    /**
     * Associates the specified {@link String} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
     * If this value is {@code null} then any value with the specified key will be removed.
     */
    void setString(String key, String value);

    /**
     * Removes the mapping from the specified key to the {@link String}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteString(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link String} values.
     *
     * @return Set of keys.
     */
    Set<String> stringKeys();
}
