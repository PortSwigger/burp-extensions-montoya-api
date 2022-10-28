/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.websocket.Direction;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;
import static burp.api.montoya.proxy.InitialInterceptAction.DO_NOT_INTERCEPT;
import static burp.api.montoya.proxy.InitialInterceptAction.DROP;
import static burp.api.montoya.proxy.InitialInterceptAction.INTERCEPT;

/**
 * Extensions can implement this interface when returning a binary message from
 * {@link ProxyWebSocketHandler#handleBinaryMessageReceived(ByteArray, Direction)}.
 */
public interface ProxyWebSocketInitialInterceptBinaryMessage
{
    /**
     * @return The action associated with this message.
     */
    InitialInterceptAction action();

    /**
     * @return The payload of this message.
     */
    ByteArray payload();

    /**
     * This is a helper method to build a binary WebSocket message to be intercepted within the Proxy.
     * @param payload The binary message payload.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptBinaryMessage interceptBinaryMessage(ByteArray payload)
    {
        return FACTORY.proxyWebSocketBinaryMessage(payload, INTERCEPT);
    }

    /**
     * This is a helper method to build a binary WebSocket message to continue within the Proxy without interception.
     * @param payload The binary message payload.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptBinaryMessage doNotInterceptBinaryMessage(ByteArray payload)
    {
        return FACTORY.proxyWebSocketBinaryMessage(payload, DO_NOT_INTERCEPT);
    }

    /**
     * This is a helper method to build a binary WebSocket message to be dropped.
     * @return The message to be dropped.
     */
    static ProxyWebSocketInitialInterceptBinaryMessage dropBinaryMessage()
    {
        return FACTORY.proxyWebSocketBinaryMessage(null, DROP);
    }
}
