/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence.support;

import java.util.Set;

/**
 * Add support for String data type
 */
public interface StringSupport
{
    /**
     * Returns the {@link String} associated with the specified key,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or
     * {@code null} if this map contains no mapping for the key
     */
    String getString(String key);

    /**
     * Associates the specified {@link String} with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key.
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
