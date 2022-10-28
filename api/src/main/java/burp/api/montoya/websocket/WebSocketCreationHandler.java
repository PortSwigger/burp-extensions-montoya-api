/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.websocket;

import burp.api.montoya.core.ToolSource;
import burp.api.montoya.http.message.requests.HttpRequest;

/**
 * Extensions can implement this interface and then call {@link WebSockets#registerWebSocketCreationHandler} to register a WebSocket handler.
 * The handler will be notified of new WebSockets created by any Burp tool.
 */
public interface WebSocketCreationHandler
{
    /**
     * This method is invoked by Burp when a WebSocket has been created.
     *
     * @param webSocket              The WebSocket that was created.
     * @param upgradeRequest         The HTTP upgrade request that initiated the WebSocket creation.
     * @param toolSource             Indicates which Burp tool that created the WebSocket.
     */
    void handleWebSocketCreated(WebSocket webSocket, HttpRequest upgradeRequest, ToolSource toolSource);
}
