/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy.websocket;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.websocket.BinaryMessage;
import burp.api.montoya.websocket.Direction;

public interface InterceptedBinaryMessage extends BinaryMessage
{
    /**
     * {@inheritDoc}
     */
    @Override
    ByteArray payload();

    /**
     * {@inheritDoc}
     */
    @Override
    Direction direction();
}
