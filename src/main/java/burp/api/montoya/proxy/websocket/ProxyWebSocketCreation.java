/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy.websocket;

import burp.api.montoya.http.message.requests.HttpRequest;

/**
 * Information about the proxy web socket that is being created.
 */
public interface ProxyWebSocketCreation
{
    /**
     * @return The ProxyWebSocket that is being created.
     */
    ProxyWebSocket proxyWebSocket();

    /**
     * @return The HTTP upgrade request that initiated the WebSocket creation.
     */
    HttpRequest upgradeRequest();
}