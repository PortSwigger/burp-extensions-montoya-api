/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.params;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to hold details about an HTTP request parameter.
 */
public interface HttpParameter
{
    /**
     * Create a new Instance of {@code HttpParameter} with {@link HttpParameterType#URL} type.
     *
     * @param name  The parameter name.
     * @param value The parameter value.
     * @return A new {@code HttpParameter} instance.
     */
    static HttpParameter urlParameter(String name, String value)
    {
        return parameter(name, value, HttpParameterType.URL);
    }

    /**
     * Create a new Instance of {@code HttpParameter} with {@link HttpParameterType#BODY} type.
     *
     * @param name  The parameter name.
     * @param value The parameter value.
     * @return A new {@code HttpParameter} instance.
     */
    static HttpParameter bodyParameter(String name, String value)
    {
        return parameter(name, value, HttpParameterType.BODY);
    }

    /**
     * Create a new Instance of {@code HttpParameter} with {@link HttpParameterType#COOKIE} type.
     *
     * @param name  The parameter name.
     * @param value The parameter value.
     * @return A new {@code HttpParameter} instance.
     */
    static HttpParameter cookieParameter(String name, String value)
    {
        return parameter(name, value, HttpParameterType.COOKIE);
    }

    /**
     * Create a new Instance of {@code HttpParameter} with the specified type.
     *
     * @param name  The parameter name.
     * @param value The parameter value.
     * @param type  The header type.
     * @return A new {@code HttpParameter} instance.
     */
    static HttpParameter parameter(String name, String value, HttpParameterType type)
    {
        return FACTORY.parameter(name, value, type);
    }


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
