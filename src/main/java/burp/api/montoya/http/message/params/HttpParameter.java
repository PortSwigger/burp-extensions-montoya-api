/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.params;

/**
 * This interface is used to hold details about an HTTP request parameter.
 */
public interface HttpParameter
{
    /**
     * @return The parameter type.
     */
    HttpParameterType type();

    /**
     * @return The parameter name.
     */
    String name();

    /**
     * @return The parameter value.
     */
    String value();
}
