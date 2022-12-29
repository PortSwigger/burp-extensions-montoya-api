/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy.websocket;

import burp.api.montoya.core.Registration;
import burp.api.montoya.websocket.Direction;

/**
 * This interface represents a ProxyWebSocket within Burp.
 */
public interface ProxyWebSocket
{
    /**
     * This method allows an extension to send a text message via the WebSocket to either the client or the server.
     *
     * @param message     The message to be sent.
     * @param direction   The direction of the message.
     */
    void sendTextMessage(String message, Direction direction);

    /**
     * This method will close the WebSocket.
     */
    void close();

    /**
     * This method is used to register a handler which will perform actions when messages are sent or received by the WebSocket.
     *
     * @param handler An object created by the extension that implements {@link ProxyWebSocketHandler} interface.
     * @return The {@link Registration} for the handler.
     */
    Registration registerHandler(ProxyWebSocketHandler handler);
}
