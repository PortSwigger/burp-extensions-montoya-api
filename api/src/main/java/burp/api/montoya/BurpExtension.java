/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya;

/**
 * All extensions must implement this interface.
 * <p>
 * Implementations must be declared public, and must provide a default (public, no-argument) constructor.
 */
public interface BurpExtension
{
    /**
     * This method is invoked when the extension is loaded.
     *
     * @param api The api implementation to access the functionality of burp suite.
     */
    void initialize(MontoyaApi api);
}