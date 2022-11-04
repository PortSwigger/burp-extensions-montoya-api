/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence;

import burp.api.montoya.persistence.support.ByteArraySupport;

/**
 * This interface represents an instance of a class that allows data to be
 * stored to and accessed from a temporary file.
 * It has support for byte arrays.
 */
public interface TemporaryFile extends ByteArraySupport
{
}
