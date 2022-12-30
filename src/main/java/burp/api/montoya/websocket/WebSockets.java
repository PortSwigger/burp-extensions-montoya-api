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
 * Provides access to WebSocket related functionality of Burp.
 */
public interface WebSockets
{
    /**
     * Register a handler which will be invoked whenever a WebSocket is created by any Burp tool.
     *
     * @param handler An object created by the extension that implements {@link WebSocketCreatedHandler} interface.
     *
     * @return The {@link Registration} for the handler.
     */
    Registration registerWebSocketCreatedHandler(WebSocketCreatedHandler handler);
}
