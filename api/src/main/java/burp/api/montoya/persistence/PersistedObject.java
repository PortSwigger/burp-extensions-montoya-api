/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence;

import burp.api.montoya.persistence.support.ByteArraySupport;
import burp.api.montoya.persistence.support.PrimitiveListSupport;
import burp.api.montoya.persistence.support.PrimitivesSupport;
import burp.api.montoya.persistence.support.RequestResponseSupport;

import java.util.Optional;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface represents an instance of a class that allows data to be stored and accessed from the Burp project.
 * It has support for HTTP requests, HTTP responses, byte arrays, primitives, lists of all these, and object hierarchies.
 */
public interface PersistedObject extends PrimitivesSupport, PrimitiveListSupport, ByteArraySupport, RequestResponseSupport
{
    /**
     * This is a helper method to create a new instance of {@link PersistedObject}.
     *
     * @return A new {@link PersistedObject} instance.
     */
    static PersistedObject persistedObject()
    {
        return FACTORY.persistedObject();
    }

    /**
     * Returns the {@link PersistedObject} associated with the specified key.
     * or {@link Optional#empty} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     */
    PersistedObject getChildObject(String key);

    /**
     * Associates the specified {@link PersistedObject} with the specified key in this map.
     * If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     *
     * @param key         key with which the specified child object is to be associated
     * @param childObject the {@link PersistedObject} to be associated with the specified key.
     *                    If this value is {@code null} then any value with the specified key will be removed
     */
    void setChildObject(String key, PersistedObject childObject);

    /**
     * Removes the mapping from the specified key to the {@link PersistedObject}.
     *
     * @param key the key whose mapping is to be deleted
     */
    void deleteChildObject(String key);
}
