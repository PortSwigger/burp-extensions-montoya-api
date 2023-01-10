/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.core;

/**
 * Product version.<br>
 * e.g. Burp Suite Professional 2022.8.1-9320
 */
public interface Version
{
    /**
     * The product name (e.g. Burp Suite Professional).
     *
     * @return The product name.
     */
    String name();

    /**
     * The major version (e.g. 2022.8).
     *
     * @return The major version.
     */
    String major();

    /**
     * The minor version (e.g. 1).
     *
     * @return The minor version.
     */
    String minor();

    /**
     * The build number (e.g. 9320).
     *
     * @return The build number.
     */
    String build();

    /**
     * The edition of Burp Suite
     *
     * @return The edition of Burp Suite
     */
    BurpSuiteEdition edition();

    @Override
    String toString();
}
