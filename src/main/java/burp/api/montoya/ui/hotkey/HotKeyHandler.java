/*
 * Copyright (c) 2022-2025. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.hotkey;

/**
 * This interface allows extensions to handle hot key events.
 */
public interface HotKeyHandler
{
    /**
     * Invoked by Burp Suite when the user invokes a hot key in the user interface.
     *
     * @param event the hot key event {@link HotKeyEvent}
     */
    void handle(HotKeyEvent event);
}
