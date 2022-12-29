/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.websocket.BinaryMessage;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Extensions can implement this interface when returning a binary message from
 * {@link ProxyWebSocketHandler#handleBinaryMessageToBeIssued(InterceptedBinaryMessage)}.
 */
public interface ProxyWebSocketFinalInterceptBinaryMessage
{
    /**
     * @return The action associated with this message.
     */
    FinalInterceptAction action();

    /**
     * @return The payload of this message.
     */
    ByteArray payload();

    /**
     * This is a helper method to build a binary WebSocket message to continue through Burp.
     *
     * @param payload The binary message payload.
     * @return The message.
     */
    static ProxyWebSocketFinalInterceptBinaryMessage continueWith(ByteArray payload)
    {
        return FACTORY.continueWithFinalProxyBinaryMessage(payload);
    }

    /**
     * This is a helper method to build a binary WebSocket message to continue through Burp.
     *
     * @param message The binary message.
     * @return The message.
     */
    static ProxyWebSocketFinalInterceptBinaryMessage continueWith(BinaryMessage message)
    {
        return FACTORY.continueWithFinalProxyBinaryMessage(message.payload());
    }

    /**
     * This is a helper method to build a binary WebSocket message to be dropped.
     *
     * @return The message to be dropped.
     */
    static ProxyWebSocketFinalInterceptBinaryMessage dropFinalBinaryMessage()
    {
        return FACTORY.dropFinalProxyBinaryMessage();
    }
}
