/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.websocket;

import burp.api.montoya.core.ByteArray;

/**
 * This interface allows an extension to be notified when messages are received or the WebSocket has been closed.
 */
public interface WebSocketHandler
{
    /**
     * Invoked when a text message is sent or received from the application.
     * This gives the extension the ability to modify the message before it is
     * sent to the application or processed by Burp.
     *
     * @param textMessage Intercepted text based WebSocket message.
     * @param direction   The direction of the message.
     * @return The message.
     */
    WebSocketTextMessage handleTextMessage(String textMessage, Direction direction);

    /**
     * Invoked when a binary message is sent or received from the application.
     * This gives the extension the ability to modify the message before it is
     * sent to the application or processed by Burp.
     *
     * @param binaryMessage Intercepted binary based WebSocket message.
     * @param direction     The direction of the message.
     * @return The message.
     */
    WebSocketBinaryMessage handleBinaryMessage(ByteArray binaryMessage, Direction direction);

    /**
     * Invoked when the WebSocket is closed.
     */
    default void onClose()
    {
    }
}
