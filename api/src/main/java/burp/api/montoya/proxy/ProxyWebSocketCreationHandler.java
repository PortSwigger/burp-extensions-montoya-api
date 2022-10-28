/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.http.message.requests.HttpRequest;

/**
 * Extensions can implement this interface and then call {@link Proxy#registerWebSocketCreationHandler} to register a WebSocket handler.
 * The handler will be notified of new WebSockets created by the Proxy tool.
 */
public interface ProxyWebSocketCreationHandler
{
    /**
     * This method is invoked by Burp when a WebSocket has been created by the Proxy tool.
     *
     * @param webSocket              The ProxyWebSocket that was created.
     * @param upgradeRequest         The HTTP upgrade request that initiated the WebSocket creation.
     */
    void handleWebSocketCreated(ProxyWebSocket webSocket, HttpRequest upgradeRequest);
}
