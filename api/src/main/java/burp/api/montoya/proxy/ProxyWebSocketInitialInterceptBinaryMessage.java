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
 * {@link ProxyWebSocketHandler#handleBinaryMessageReceived(InterceptedBinaryMessage)}.
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
     * This is a helper method to build a binary WebSocket message to
     * follow the current interception rules to determine the appropriate
     * action to take for the message.
     *
     * @param payload The binary message payload.
     * @return The {@link ProxyWebSocketInitialInterceptBinaryMessage} that allows user rules to be
     * followed.
     */
    static ProxyWebSocketInitialInterceptBinaryMessage followUserRulesBinaryMessage(ByteArray payload)
    {
        return FACTORY.followUserRulesInitialProxyBinaryMessage(payload);
    }

    /**
     * This is a helper method to build a binary WebSocket message to
     * follow the current interception rules to determine the appropriate
     * action to take for the message.
     *
     * @param message The binary message.
     * @return The {@link ProxyWebSocketInitialInterceptBinaryMessage} that allows user rules to be
     * followed.
     */
    static ProxyWebSocketInitialInterceptBinaryMessage followUserRulesBinaryMessage(BinaryMessage message)
    {
        return FACTORY.followUserRulesInitialProxyBinaryMessage(message.payload());
    }

    /**
     * This is a helper method to build a binary WebSocket message to be intercepted within the Proxy.
     *
     * @param payload The binary message payload.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptBinaryMessage interceptBinaryMessage(ByteArray payload)
    {
        return FACTORY.interceptInitialProxyBinaryMessage(payload);
    }

    /**
     * This is a helper method to build a binary WebSocket message to be intercepted within the Proxy.
     *
     * @param message The binary message.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptBinaryMessage interceptBinaryMessage(BinaryMessage message)
    {
        return FACTORY.interceptInitialProxyBinaryMessage(message.payload());
    }

    /**
     * This is a helper method to build a binary WebSocket message to continue within the Proxy without interception.
     *
     * @param payload The binary message payload.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptBinaryMessage doNotIntercept(ByteArray payload)
    {
        return FACTORY.doNotInterceptInitialProxyBinaryMessage(payload);
    }

    /**
     * This is a helper method to build a binary WebSocket message to continue within the Proxy without interception.
     *
     * @param message The binary message.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptBinaryMessage doNotIntercept(BinaryMessage message)
    {
        return FACTORY.doNotInterceptInitialProxyBinaryMessage(message.payload());
    }

    /**
     * This is a helper method to build a binary WebSocket message to be dropped.
     *
     * @return The message to be dropped.
     */
    static ProxyWebSocketInitialInterceptBinaryMessage dropInitialBinaryMessage()
    {
        return FACTORY.dropInitialProxyBinaryMessage();
    }
}
