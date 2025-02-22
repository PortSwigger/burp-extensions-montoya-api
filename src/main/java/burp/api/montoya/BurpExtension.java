/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya;

import java.util.Set;

import static java.util.Collections.emptySet;

/**
 * All extensions must implement this interface.
 * <p>
 * Implementations must be declared public, and must provide a default (public, no-argument) constructor.
 */
public interface BurpExtension
{
    /**
     * Invoked when the extension is loaded. Any registered handlers will only be enabled once this method has completed.
     *
     * @param api The API implementation to access the functionality of Burp Suite.
     */
    void initialize(MontoyaApi api);

    /**
     * Called when the extension is loaded to determine if it requires any enhanced capabilities.
     *
     * @see EnhancedCapability
     * @return set of required capabilities
     */
    default Set<EnhancedCapability> enhancedCapabilities()
    {
        return emptySet();
    }
}