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
import static burp.api.montoya.proxy.InitialInterceptAction.DO_NOT_INTERCEPT;
import static burp.api.montoya.proxy.InitialInterceptAction.DROP;
import static burp.api.montoya.proxy.InitialInterceptAction.INTERCEPT;


/**
 * Extensions can implement this interface when returning a text message from
 * {@link ProxyWebSocketHandler#handleTextMessageReceived(String, Direction)}.
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
     * This is a helper method to build a text WebSocket message to be intercepted within the Proxy.
     * @param payload The text message payload.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptTextMessage interceptTextMessage(String payload)
    {
        return FACTORY.proxyWebSocketTextMessage(payload, INTERCEPT);
    }

    /**
     * This is a helper method to build a text WebSocket message to continue within the Proxy without interception.
     * @param payload The text message payload.
     * @return The message.
     */
    static ProxyWebSocketInitialInterceptTextMessage doNotInterceptTextMessage(String payload)
    {
        return FACTORY.proxyWebSocketTextMessage(payload, DO_NOT_INTERCEPT);
    }

    /**
     * This is a helper method to build a text WebSocket message to be dropped.
     * @return The message to be dropped.
     */
    static ProxyWebSocketInitialInterceptTextMessage dropTextMessage()
    {
        return FACTORY.proxyWebSocketTextMessage(null, DROP);
    }
}
