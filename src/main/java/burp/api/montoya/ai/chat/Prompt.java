/*
 * Copyright (c) 2022-2024. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ai.chat;

/**
 * Provides access to AI chat prompt functionality.
 */
public interface Prompt
{
    /**
     * Evaluates a series of messages using the AI chat prompt.
     *
     * @param messages messages to evaluate
     *
     * @return A {@link PromptResponse} object with the response from the chat prompt.
     * @throws PromptException if there is a problem executing the prompt.
     */
    PromptResponse execute(String... messages) throws PromptException;

    /**
     * Evaluates a series of messages using the AI chat prompt using the provided prompt options.
     *
     * @param options prompt options
     * @param messages messages to evaluate
     *
     * @return A {@link PromptResponse} object with the response from the chat prompt.
     * @throws PromptException if there is a problem executing the prompt.
     */
    PromptResponse execute(PromptOptions options, String... messages) throws PromptException;

    /**
     * Evaluates a series of messages using the AI chat prompt.
     *
     * @param messages messages to evaluate
     *
     * @return A {@link PromptResponse} object with the response from the chat prompt.
     * @throws PromptException if there is a problem executing the prompt.
     */
    PromptResponse execute(Message... messages) throws PromptException;

    /**
     * Evaluates a series of messages using the AI chat prompt using the provided prompt options.
     *
     * @param options prompt options
     * @param messages messages to evaluate
     *
     * @return A {@link PromptResponse} object with the response from the chat prompt.
     * @throws PromptException if there is a problem executing the prompt.
     */
    PromptResponse execute(PromptOptions options, Message... messages) throws PromptException;
}
