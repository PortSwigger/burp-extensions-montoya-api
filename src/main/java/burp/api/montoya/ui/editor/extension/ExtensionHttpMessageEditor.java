/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor.extension;

import burp.api.montoya.ui.Selection;

import java.awt.Component;

/**
 * This interface provides shared information for the {@link ExtensionHttpRequestEditor} and {@link ExtensionHttpResponseEditor} interfaces
 */
public interface ExtensionHttpMessageEditor
{
    /**
     * @return The caption located in the message editor tab header.
     */
    String caption();

    /**
     * @return The component that is rendered within the message editor tab.
     */
    Component uiComponent();

    /**
     * The method should return {@code null} if no data has been selected.
     *
     * @return The data that is currently selected by the user.
     */
    Selection selectedData();

    /**
     * @return True if the user has modified the current message within the editor.
     */
    boolean isModified();
}
