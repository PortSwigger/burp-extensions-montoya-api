/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.ui.contextmenu.WebSocketMessage;
import burp.api.montoya.websocket.Direction;

/**
 * WebSocket message intercepted by the Proxy.
 */
public interface ProxyWebSocketMessage extends WebSocketMessage
{
    /**
     * {@inheritDoc}
     */
    @Override
    Annotations annotations();

    /**
     * {@inheritDoc}
     */
    @Override
    Direction direction();

    /**
     * {@inheritDoc}
     */
    @Override
    ByteArray payload();

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest upgradeRequest();
}