/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.websocket.Direction;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;
import static burp.api.montoya.proxy.FinalInterceptAction.CONTINUE;
import static burp.api.montoya.proxy.FinalInterceptAction.DROP;


/**
 * Extensions can implement this interface when returning a text message from
 * {@link ProxyWebSocketHandler#handleTextMessageToBeIssued(String, Direction)}.
 */
public interface ProxyWebSocketFinalInterceptTextMessage
{
    /**
     * @return The action associated with this message.
     */
    FinalInterceptAction action();

    /**
     * @return The payload of this message.
     */
    String payload();

    /**
     * This is a helper method to build a text WebSocket message to continue through Burp.
     * @param payload The text message payload.
     * @return The message.
     */
    static ProxyWebSocketFinalInterceptTextMessage continueWithTextMessage(String payload)
    {
        return FACTORY.proxyWebSocketTextMessage(payload, CONTINUE);
    }

    /**
     * This is a helper method to build a text WebSocket message to be dropped.
     * @return The message to be dropped.
     */
    static ProxyWebSocketFinalInterceptTextMessage dropTextMessage()
    {
        return FACTORY.proxyWebSocketTextMessage(null, DROP);
    }
}
