/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.responses.analysis;

/**
 * This interface is used to hold details about HTTP response attributes.
 */
public interface Attribute
{
    /**
     * @return The attribute type.
     */
    AttributeType type();

    /**
     * @return The attribute value.
     */
    int value();
}
