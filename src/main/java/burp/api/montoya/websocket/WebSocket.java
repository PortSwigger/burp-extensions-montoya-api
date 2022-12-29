/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.websocket;

import burp.api.montoya.core.Registration;

/**
 * This interface represents a WebSocket within Burp.
 */
public interface WebSocket
{
    /**
     * This method allows an extension to send a text message via the WebSocket.
     *
     * @param message The message to be sent.
     */
    void sendTextMessage(String message);

    /**
     * This method will close the WebSocket.
     */
    void close();

    /**
     * This method is used to register a handler which will perform an action when a message is sent to or received from the application.
     *
     * @param handler An object created by the extension that implements {@link WebSocketHandler} interface.
     * @return The {@link Registration} for the handler.
     */
    Registration registerHandler(WebSocketHandler handler);
}
