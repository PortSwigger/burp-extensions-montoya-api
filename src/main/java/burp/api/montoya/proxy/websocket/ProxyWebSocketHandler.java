/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy.websocket;

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
     * @param interceptedTextMessage Intercepted text WebSocket message.
     * @return The {@link ProxyWebSocketInitialInterceptTextMessage} containing the required action and text message to be passed through.
     */
    ProxyWebSocketInitialInterceptTextMessage handleTextMessageReceived(InterceptedTextMessage interceptedTextMessage);

    /**
     * Invoked when a text message is about to be sent to either the client or server.
     * This gives the extension the ability to modify the message before it is
     * issued.
     *
     * @param interceptedTextMessage Intercepted text WebSocket message.
     * @return The {@link ProxyWebSocketInitialInterceptTextMessage} containing the required action and text message to be passed through.
     */
    ProxyWebSocketFinalInterceptTextMessage handleTextMessageToBeIssued(InterceptedTextMessage interceptedTextMessage);

    /**
     * Invoked when a binary message is received from either the client or server.
     * This gives the extension the ability to modify the message before it is
     * processed by Burp.
     *
     * @param interceptedBinaryMessage Intercepted binary WebSocket message.
     * @return The {@link ProxyWebSocketInitialInterceptBinaryMessage} containing the required action and binary message to be passed through.
     */
    ProxyWebSocketInitialInterceptBinaryMessage handleBinaryMessageReceived(InterceptedBinaryMessage interceptedBinaryMessage);

    /**
     * Invoked when a binary message is about to be sent to either the client or server.
     * This gives the extension the ability to modify the message before it is
     * issued.
     *
     * @param interceptedBinaryMessage Intercepted binary WebSocket message.
     * @return The {@link ProxyWebSocketInitialInterceptBinaryMessage} containing the required action and binary message to be passed through.
     */
    ProxyWebSocketFinalInterceptBinaryMessage handleBinaryMessageToBeIssued(InterceptedBinaryMessage interceptedBinaryMessage);

    /**
     * Invoked when the WebSocket is closed.
     */
    default void onClose()
    {
    }
}
