/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence;

import burp.api.montoya.persistence.support.DeleteSupport;
import burp.api.montoya.persistence.support.KeySupport;
import burp.api.montoya.persistence.support.PrimitivesSupport;

/**
 * This interface represents an instance of a class that allows data to be
 * stored and accessed from the java preference store. It has support for primitives.
 */
public interface Preferences extends PrimitivesSupport, KeySupport, DeleteSupport
{
}
