/*
 * Copyright (c) 2022-2025. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.hotkey;

import burp.api.montoya.core.ToolSource;
import burp.api.montoya.ui.contextmenu.ComponentEvent;
import burp.api.montoya.ui.contextmenu.InvocationSource;
import burp.api.montoya.ui.contextmenu.MessageEditorHttpRequestResponse;

import java.util.Optional;

/**
 * Provides useful contextual information from events triggered by hot keys {@link HotKeyHandler}.
 */
public interface HotKeyEvent extends ComponentEvent, ToolSource, InvocationSource
{
    /**
     * This method can be used to retrieve details of the currently selected HTTP request/response when the hot key was invoked.
     *
     * @return an {@link Optional} describing the currently selected request response with selection metadata.
     */
    Optional<MessageEditorHttpRequestResponse> messageEditorRequestResponse();
}
