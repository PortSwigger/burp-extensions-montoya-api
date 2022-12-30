/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy.websocket;

import burp.api.montoya.proxy.MessageSendAction;
import burp.api.montoya.websocket.TextMessage;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;


/**
 * Extensions can implement this interface when returning a text message from
 * {@link ProxyMessageHandler#handleTextMessageToSend(InterceptedTextMessage)}.
 */
public interface TextMessageToSendAction
{
    /**
     * @return The action associated with this message.
     */
    MessageSendAction action();

    /**
     * @return The payload of this message.
     */
    String payload();

    /**
     * This is a helper method to build a text WebSocket message to continue through Burp.
     *
     * @param payload The text message payload.
     *
     * @return The message.
     */
    static TextMessageToSendAction continueWith(String payload)
    {
        return FACTORY.continueWithFinalProxyTextMessage(payload);
    }

    /**
     * This is a helper method to build a text WebSocket message to continue through Burp.
     *
     * @param message The text message.
     *
     * @return The message.
     */
    static TextMessageToSendAction continueWith(TextMessage message)
    {
        return FACTORY.continueWithFinalProxyTextMessage(message.payload());
    }

    /**
     * This is a helper method to build a text WebSocket message to be dropped.
     *
     * @return The message to be dropped.
     */
    static TextMessageToSendAction drop()
    {
        return FACTORY.dropFinalProxyTextMessage();
    }
}
