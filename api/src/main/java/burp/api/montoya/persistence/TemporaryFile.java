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
import burp.api.montoya.persistence.support.RequestResponseSupport;

/**
 * This interface represents an instance of a class that allows data to be
 * stored to and accessed from a temporary file.
 * It has support for HTTP requests, HTTP responses and byte arrays.
 */
public interface TemporaryFile extends RequestResponseSupport, ByteArraySupport, KeySupport, DeleteSupport
{
}
