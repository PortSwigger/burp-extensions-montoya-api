/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.intruder;

import burp.api.montoya.core.Range;

/**
 * This interface is used to represent an Intruder insertion point for attack payloads.
 */
public interface IntruderInsertionPoint
{
    /**
     * @return The base value of the insertion point.
     */
    byte[] content();

    /**
     * @return A {@link Range} object that contains insertion point offset values.
     */
    Range offsets();
}
