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

    /**
     * This is a helper method to create a new Instance of {@code HttpParameter} with {@link HttpParameterType#URL} type.
     *
     * @param name The parameter name.
     * @param value The parameter value.
     * @return A new {@code HttpParameter} instance.
     */
    static HttpParameter urlParam(String name, String value) {
        return from(name, value, HttpParameterType.URL);
    }

    /**
     * This is a helper method to create a new Instance of {@code HttpParameter} with {@link HttpParameterType#BODY} type.
     *
     * @param name The parameter name.
     * @param value The parameter value.
     * @return A new {@code HttpParameter} instance.
     */
    static HttpParameter bodyParam(String name, String value) {
        return from(name, value, HttpParameterType.BODY);
    }

    /**
     * This is a helper method to create a new Instance of {@code HttpParameter} with {@link HttpParameterType#COOKIE} type.
     *
     * @param name The parameter name.
     * @param value The parameter value.
     * @return A new {@code HttpParameter} instance.
     */
    static HttpParameter cookie(String name, String value) {
        return from(name, value, HttpParameterType.COOKIE);
    }

    /**
     * This is a helper method to create a new Instance of {@code HttpParameter} with user-specified type.
     *
     * @param name The parameter name.
     * @param value The parameter value.
     * @param type The header type.
     * @return A new {@code HttpParameter} instance.
     */
    static HttpParameter from(String name, String value, HttpParameterType type) {
        return new HttpParameter()
        {
            @Override
            public HttpParameterType type()
            {
                return type;
            }

            @Override
            public String name()
            {
                return name;
            }

            @Override
            public String value()
            {
                return value;
            }
        };
    }
}
