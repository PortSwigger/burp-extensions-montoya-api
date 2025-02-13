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
 * Interface used to specify options for AI chat prompts.
 */
public interface PromptOptions
{
    /**
     * Specifies the prompt temperature to be used.
     *
     * @param temperature the temperature
     *
     * @return prompt options
     */
    PromptOptions withTemperature(double temperature);

    static PromptOptions promptOptions()
    {
        return FACTORY.promptOptions();
    }
}
