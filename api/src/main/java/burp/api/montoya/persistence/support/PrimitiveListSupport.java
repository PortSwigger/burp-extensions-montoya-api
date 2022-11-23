/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence.support;

import burp.api.montoya.persistence.PersistedList;

import java.util.Optional;
import java.util.Set;

/**
 * Add support for primitive lists
 */
public interface PrimitiveListSupport
{
    /**
     * Returns the {@link PersistedList} of {@link Boolean} associated with the specified key,
     * or {@link Optional#empty} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<PersistedList<Boolean>> getBooleanList(String key);

    /**
     * Associates the specified {@link PersistedList} of {@link Boolean} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     */
    void setBooleanList(String key, PersistedList<Boolean> value);

    /**
     * Removes the mapping from the specified key to the {@link PersistedList} of {@link Boolean}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteBooleanList(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link Boolean} Lists.
     *
     * @return Set of keys.
     */
    Set<String> booleanListKeys();

    /**
     * Returns the {@link PersistedList} of {@link Short} associated with the specified key,
     * or {@link Optional#empty} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<PersistedList<Short>> getShortList(String key);

    /**
     * Associates the specified {@link PersistedList} of {@link Short} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     */
    void setShortList(String key, PersistedList<Short> value);

    /**
     * Removes the mapping from the specified key to the {@link PersistedList} of {@link Short}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteShortList(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link Short} Lists.
     *
     * @return Set of keys.
     */
    Set<String> shortListKeys();

    /**
     * Returns the {@link PersistedList} of {@link Integer} associated with the specified key,
     * or {@link Optional#empty} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<PersistedList<Integer>> getIntegerList(String key);

    /**
     * Associates the specified {@link PersistedList} of {@link Integer} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     */
    void setIntegerList(String key, PersistedList<Integer> value);

    /**
     * Removes the mapping from the specified key to the {@link PersistedList} of {@link Integer}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteIntegerList(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link Integer} Lists.
     *
     * @return Set of keys.
     */
    Set<String> integerListKeys();

    /**
     * Returns the {@link PersistedList} of {@link Long} associated with the specified key,
     * or {@link Optional#empty} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<PersistedList<Long>> getLongList(String key);

    /**
     * Associates the specified {@link PersistedList} of {@link Long} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     */
    void setLongList(String key, PersistedList<Long> value);

    /**
     * Removes the mapping from the specified key to the {@link PersistedList} of {@link Long}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteLongList(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link Long} Lists.
     *
     * @return Set of keys.
     */
    Set<String> longListKeys();

    /**
     * Returns the {@link PersistedList} of {@link String} associated with the specified key,
     * or {@link Optional#empty} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@link Optional#empty} if this map contains no mapping for the key
     */
    Optional<PersistedList<String>> getStringList(String key);

    /**
     * Associates the specified {@link PersistedList} of {@link String} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     */
    void setStringList(String key, PersistedList<String> value);

    /**
     * Removes the mapping from the specified key to the {@link PersistedList} of {@link String}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteStringList(String key);

    /**
     * This method is used to retrieve all keys currently mapped for {@link String} Lists.
     *
     * @return Set of keys.
     */
    Set<String> stringListKeys();
}
