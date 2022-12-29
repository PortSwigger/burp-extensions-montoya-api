/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy.websocket;

import burp.api.montoya.proxy.InitialInterceptAction;
import burp.api.montoya.websocket.TextMessage;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;


/**
 * Extensions can implement this interface when returning a text message from
 * {@link ProxyWebSocketHandler#handleTextMessageReceived(InterceptedTextMessage)}.
 */
public interface ProxyWebSocketInitialInterceptTextMessage
{
    /**
     * @return The action associated with this message.
     */
    InitialInterceptAction action();

    /**
     * @return The payload of this message.
     */
    String payload();

    /**
     * This is a helper method to build a text WebSocket message to
     * follow the current interception rules to determine the appropriate
     * action to take for the message.
     *
     * @param payload The text message payload.
     * @return The {@link ProxyWebSocketInitialInterceptTextMessage} that allows user rules to be
     * followed.
     */
    static ProxyWebSocketInitialInterceptTextMessage followUserRulesTextMessage(String payload)
    {
        return FACTORY.followUserRulesInitialProxyTextMessage(payload);
    }

    /**
     * This is a helper method to build a text WebSocket message to
     * follow the current interception rules to determine the appropriate
     * action to take for the message.
     *
     * @param message The text message.
     * @return The {@link ProxyWebSocketInitialInterceptTextMessage} that allows user rules to be
     * followed.
     */
    static ProxyWebSocketInitialInterceptTextMessage followUserRulesTextMessage(TextMessage message)
    {
        return FACTORY.followUserRulesInitialProxyTextMessage(message.payload());
    }

    /**
     * This is a helper method to build a text WebSocket message to be intercepted within the Proxy.
     *
     * @param payload The text message payload.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptTextMessage interceptTextMessage(String payload)
    {
        return FACTORY.interceptInitialProxyTextMessage(payload);
    }

    /**
     * This is a helper method to build a text WebSocket message to be intercepted within the Proxy.
     *
     * @param message The text message.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptTextMessage interceptTextMessage(TextMessage message)
    {
        return FACTORY.interceptInitialProxyTextMessage(message.payload());
    }

    /**
     * This is a helper method to build a text WebSocket message to continue within the Proxy without interception.
     *
     * @param payload The text message payload.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptTextMessage doNotIntercept(String payload)
    {
        return FACTORY.doNotInterceptInitialProxyTextMessage(payload);
    }

    /**
     * This is a helper method to build a text WebSocket message to continue within the Proxy without interception.
     *
     * @param message The text message payload.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptTextMessage doNotIntercept(TextMessage message)
    {
        return FACTORY.doNotInterceptInitialProxyTextMessage(message.payload());
    }


    /**
     * This is a helper method to build a text WebSocket message to be dropped.
     *
     * @return The message to be dropped.
     */
    static ProxyWebSocketInitialInterceptTextMessage dropInitialTextMessage()
    {
        return FACTORY.dropInitialProxyTextMessage();
    }
}
