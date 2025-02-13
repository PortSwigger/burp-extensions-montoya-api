/*
 * Copyright (c) 2022-2024. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ai;

import burp.api.montoya.ai.chat.Prompt;

/**
 * Provides access to AI related functionality.
 */
public interface Ai
{
    /**
     * Allows an extension to determine if it has access to AI functionality.
     *
     * @return true if extension has access to AI functionality.
     */
    boolean isEnabled();

    /**
     * Access functionality related to AI chat prompts.
     *
     * @return An implementation of the Prompt interface which exposes chat prompt functionality.
     */
    Prompt prompt();
}
