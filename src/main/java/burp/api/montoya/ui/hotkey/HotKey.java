/*
 * Copyright (c) 2022-2025. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.hotkey;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Represents a hot key.
 */
public interface HotKey
{
    /**
     * @return the name of the hot key.
     */
    String name();

    /**
     * Hotkeys are defined in the same format as within Burp's Settings.
     *
     * @return the hot key
     */
    String hotkey();

    /**
     * Create a new instance
     *
     * @param name   The name of the hot key.
     * @param hotkey The hot key.
     *
     * @return A new {@code HotKey} instance.
     */
    static HotKey hotKey(String name, String hotkey)
    {
        return FACTORY.hotkey(name, hotkey);
    }
}
