/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy.websocket;

import burp.api.montoya.websocket.Direction;
import burp.api.montoya.websocket.TextMessage;

public interface InterceptedTextMessage extends TextMessage
{
    /**
     * {@inheritDoc}
     */
    @Override
    String payload();

    /**
     * {@inheritDoc}
     */
    @Override
    Direction direction();
}
