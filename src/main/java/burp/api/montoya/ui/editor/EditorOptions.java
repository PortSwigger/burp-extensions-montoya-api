/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor;

/**
 * These options allow you to configure additional behavior to {@link Editor} implementations.
 */
public enum EditorOptions
{
    /**
     * Editor should be read only.
     */
    READ_ONLY,
    /**
     * Editor should wrap lines - only applicable for Raw Editors.
     */
    WRAP_LINES,
    /**
     * Editor should show non printable characters - only applicable for Raw Editors.
     */
    SHOW_NON_PRINTABLE_CHARACTERS
}
