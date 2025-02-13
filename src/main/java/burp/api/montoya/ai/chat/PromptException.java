/*
 * Copyright (c) 2022-2024. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ai.chat;

/**
 * This class represents an exception which is thrown when using AI chat prompt functionality.
 */
public class PromptException extends RuntimeException
{
    public PromptException(String message)
    {
        super(message);
    }
}
