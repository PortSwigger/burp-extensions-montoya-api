/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.core;

/**
 * This interface represents a product version.
 */
public interface Version
{
    /**
     * This method returns the product name (e.g. Burp Suite Professional).
     *
     * @return The product name.
     */
    String name();

    /**
     * This method returns the major version (e.g. 2022.8).
     *
     * @return The major version.
     */
    String major();

    /**
     * This method returns the minor version (e.g. 1).
     *
     * @return The minor version.
     */
    String minor();

    /**
     * This method returns the build number (e.g. 9320).
     *
     * @return The build number.
     */
    String build();

    /**
     * This method returns the edition of Burp Suite
     *
     * @return The edition of Burp Suite
     */
    BurpSuiteEdition edition();

    @Override
    String toString();
}
