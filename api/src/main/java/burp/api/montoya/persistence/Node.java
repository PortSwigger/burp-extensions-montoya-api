/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence;

import burp.api.montoya.persistence.support.ByteArraySupport;
import burp.api.montoya.persistence.support.DeleteSupport;
import burp.api.montoya.persistence.support.KeySupport;
import burp.api.montoya.persistence.support.PrimitiveListSupport;
import burp.api.montoya.persistence.support.PrimitivesSupport;
import burp.api.montoya.persistence.support.RequestResponseSupport;

/**
 * This interface represents an instance of a class that allows data to be stored and accessed from the Burp project.
 * It has support for HTTP requests, HTTP responses, byte arrays, primitives, primitive collections and Node hierarchies.
 */
public interface Node extends PrimitivesSupport, PrimitiveListSupport, ByteArraySupport, RequestResponseSupport, KeySupport, DeleteSupport
{
    /**
     * Returns the a {@link Node} to which the specified key is mapped.
     * or a new Node if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * new {@link Node} if this map contains no mapping for the key
     */
    Node childNode(String key);
}
