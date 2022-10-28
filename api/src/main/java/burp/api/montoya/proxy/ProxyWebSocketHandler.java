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

/**
 * This interface allows an extension to be notified when messages are sent or received via the proxy WebSocket, or it has been closed.
 */
public interface ProxyWebSocketHandler
{
    /**
     * Invoked when a text message is received from either the client or server.
     * This gives the extension the ability to modify the message before it is
     * processed by Burp.
     *
     * @param textMessage Intercepted text-based WebSocket message.
     * @param direction   The direction of the message.
     * @return The message.
     */
    ProxyWebSocketInitialInterceptTextMessage handleTextMessageReceived(String textMessage, Direction direction);

    /**
     * Invoked when a text message is about to be sent to either the client or server.
     * This gives the extension the ability to modify the message before it is
     * issued.
     *
     * @param textMessage Intercepted text-based WebSocket message.
     * @param direction   The direction of the message.
     * @return The message.
     */
    ProxyWebSocketFinalInterceptTextMessage handleTextMessageToBeIssued(String textMessage, Direction direction);

    /**
     * Invoked when a binary message is received from either the client or server.
     * This gives the extension the ability to modify the message before it is
     * processed by Burp.
     *
     * @param binaryMessage Intercepted binary WebSocket message.
     * @param direction   The direction of the message.
     * @return The message.
     */
    ProxyWebSocketInitialInterceptBinaryMessage handleBinaryMessageReceived(ByteArray binaryMessage, Direction direction);

    /**
     * Invoked when a binary message is about to be sent to either the client or server.
     * This gives the extension the ability to modify the message before it is
     * issued.
     *
     * @param binaryMessage Intercepted binary WebSocket message.
     * @param direction   The direction of the message.
     * @return The message.
     */
    ProxyWebSocketFinalInterceptBinaryMessage handleBinaryMessageToBeIssued(ByteArray binaryMessage, Direction direction);

    /**
     * Invoked when the WebSocket is closed.
     */
    default void onClose()
    {
    }
}
