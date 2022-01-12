/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.misc;

/**
 * This enum represents shutdown options that can be used when calling
 * {@link Misc#shutdownBurp(ShutdownOptions...)}.
 */
public enum ShutdownOptions
{
    PROMPT_USER
}
