/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence.support;

import java.util.List;

public interface PrimitiveListSupport
{
    /**
     * Returns the List of {@link Boolean} to which the specified key is mapped.
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * new empty list if this map contains no mapping for the key
     */
    List<Boolean> getBooleanList(String key);

    /**
     * Associates the specified List of {@link Boolean} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     * @return A read-only {@link List} of {@link Boolean}
     * representing the stored data
     */
    List<Boolean> setBooleanList(String key, List<Boolean> value);

    /**
     * Returns the List of {@link Short} to which the specified key is mapped.
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * new empty list if this map contains no mapping for the key
     */
    List<Short> getShortList(String key);

    /**
     * Associates the specified List of {@link Short} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     * @return A read-only {@link List} of {@link Short}
     * representing the stored data
     */
    List<Short> setShortList(String key, List<Short> value);

    /**
     * Returns the List of {@link Integer} to which the specified key is mapped.
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * new empty list if this map contains no mapping for the key
     */
    List<Integer> getIntegerList(String key);

    /**
     * Associates the specified List of {@link Integer} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     * @return A read-only {@link List} of {@link Integer}
     * representing the stored data
     */
    List<Integer> setIntegerList(String key, List<Integer> value);

    /**
     * Returns the List of {@link Long} to which the specified key is mapped.
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * new empty list if this map contains no mapping for the key
     */
    List<Long> getLongList(String key);

    /**
     * Associates the specified List of {@link Long} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     * @return A read-only {@link List} of {@link Long}
     * representing the stored data
     */
    List<Long> setLongList(Long key, List<Long> value);

    /**
     * Returns the List of {@link String} to which the specified key is mapped
     * or {@code null} if this map contains no mapping for the key
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    List<String> getStringList(String key);

    /**
     * Associates the specified List of {@link String} with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key.
     * @return A read-only {@link List} of {@link String}
     * representing the stored data
     */
    List<String> setStringList(String key, List<String> value);
}
