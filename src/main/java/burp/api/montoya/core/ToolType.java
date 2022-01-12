/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.core;

/**
 * This enum represents tools in Burp Suite.
 */
public enum ToolType
{
    SUITE("Suite"),
    TARGET("Target"),
    PROXY("Proxy"),
    SCANNER("Scanner"),
    INTRUDER("Intruder"),
    REPEATER("Repeater"),
    LOGGER("Logger"),
    SEQUENCER("Sequencer"),
    DECODER("Decoder"),
    COMPARER("Comparer"),
    EXTENDER("Extender"),
    RECORDED_LOGIN_REPLAYER("Recorded login replayer");

    private final String toolName;

    ToolType(String toolName)
    {
        this.toolName = toolName;
    }

    public String toolName()
    {
        return toolName;
    }

    @Override
    public String toString()
    {
        return toolName;
    }
}
