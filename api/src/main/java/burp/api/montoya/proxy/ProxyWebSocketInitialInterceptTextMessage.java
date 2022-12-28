/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

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
     * This is a helper method to build a text WebSocket message to continue within the Proxy without interception.
     *
     * @param payload The text message payload.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptTextMessage doNotInterceptTextMessage(String payload)
    {
        return FACTORY.doNotInterceptInitialProxyTextMessage(payload);
    }

    /**
     * This is a helper method to build a text WebSocket message to be dropped.
     *
     * @return The message to be dropped.
     */
    static ProxyWebSocketInitialInterceptTextMessage dropTextMessage()
    {
        return FACTORY.dropInitialProxyTextMessage();
    }
}
