/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence;

/**
 * This interface represents an instance of a class that allows data to be
 * stored and accessed.
 */
public interface PersistenceContext
{
    /**
     * This method is used to load a string value for the extension.
     *
     * @param name The name of the string.
     * @return The value, or {@code null} if no value is set.
     */
    String getString(String name);

    /**
     * This method is used to save a string value for the extension.
     *
     * @param name  The name of the string.
     * @param value The string value. If this value is {@code null} then any
     *              value with the specified name will be removed.
     */
    void setString(String name, String value);

    /**
     * This method is used to load a number value for the extension.
     *
     * @param name The name of the number.
     * @return The value, or {@code null} if no value is set.
     */
    Number getNumber(String name);

    /**
     * This method is used to save a number value for the extension.
     *
     * @param name  The name of the number.
     * @param value The number value. If this value is {@code null} then any
     *              value with the specified name will be removed.
     */
    void setNumber(String name, Number value);

    /**
     * This method is used to load a boolean value for the extension.
     *
     * @param name The name of the boolean.
     * @return The value, or {@code null} if no value is set.
     */
    Boolean getBoolean(String name);

    /**
     * This method is used to save a boolean value for the extension.
     *
     * @param name  The name of the boolean.
     * @param value The boolean value. If this value is {@code null} then any
     *              value with the specified name will be removed.
     */
    void setBoolean(String name, Boolean value);

    /**
     * This method is used to remove a value for the extension.
     *
     * @param name The name of the value to remove.
     */
    void delete(String name);
}
