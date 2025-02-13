/*
 * Copyright (c) 2022-2024. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ai.chat;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Interface used to represent different types of message used by AI chat prompts.
 */
public interface Message
{
    /**
     * Creates a system message.
     *
     * @param content message content
     *
     * @return the message
     */
    static Message systemMessage(String content)
    {
        return FACTORY.systemMessage(content);
    }

    /**
     * Creates a user message.
     *
     * @param content message content
     *
     * @return the message
     */
    static Message userMessage(String content)
    {
        return FACTORY.userMessage(content);
    }

    /**
     * Creates an assistant message.
     *
     * @param content message content
     *
     * @return the message
     */
    static Message assistantMessage(String content)
    {
        return FACTORY.assistantMessage(content);
    }
}
