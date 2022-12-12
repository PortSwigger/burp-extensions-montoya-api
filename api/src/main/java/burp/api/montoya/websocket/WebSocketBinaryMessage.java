package burp.api.montoya.websocket;

import burp.api.montoya.core.ByteArray;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface represents a binary WebSocket message.
 */
public interface WebSocketBinaryMessage
{
    /**
     * @return The action associated with this message.
     */
    WebSocketMessageAction action();

    /**
     * @return The payload of this message.
     */
    ByteArray payload();

    /**
     * This is a helper method to build a binary WebSocket message to be processed.
     * @param payload The binary message payload.
     * @return The message to be processed.
     */
    static WebSocketBinaryMessage continueWithBinaryMessage(ByteArray payload)
    {
        return FACTORY.continueWithBinaryMessage(payload);
    }

    /**
     * This is a helper method to build a binary WebSocket message to be dropped.
     * @return The message to be dropped.
     */
    static WebSocketBinaryMessage dropBinaryMessage()
    {
        return FACTORY.dropBinaryMessage();
    }
}
