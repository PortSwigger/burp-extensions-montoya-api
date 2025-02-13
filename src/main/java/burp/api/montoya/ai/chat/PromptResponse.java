/*
 * Copyright (c) 2022-2024. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ai.chat;

/**
 * Represents the response from an AI chat prompt.
 */
public interface PromptResponse
{
    /**
     * Retrieves the content of the response from the prompt.
     *
     * @return prompt content
     */
    String content();
}
