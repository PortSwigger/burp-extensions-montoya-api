package burp.api.montoya.websocket;

import burp.api.montoya.core.ByteArray;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface represents a binary WebSocket message.
 */
public interface BinaryMessageResult
{
    /**
     * @return The action associated with this message.
     */
    MessageAction action();

    /**
     * @return The payload of this message.
     */
    ByteArray payload();

    /**
     * This is a helper method to build a binary WebSocket message to be processed.
     *
     * @param payload The binary message payload.
     * @return The message to be processed.
     */
    static BinaryMessageResult continueWith(ByteArray payload)
    {
        return FACTORY.continueWithBinaryMessage(payload);
    }

    /**
     * This is a helper method to build a binary WebSocket message to be processed.
     *
     * @param binaryMessage The binary message payload.
     * @return The message to be processed.
     */
    static BinaryMessageResult continueWith(BinaryMessage binaryMessage)
    {
        return FACTORY.continueWithBinaryMessage(binaryMessage.payload());
    }

    /**
     * This is a helper method to build a binary WebSocket message to be dropped.
     *
     * @return The message to be dropped.
     */
    static BinaryMessageResult dropBinaryMessage()
    {
        return FACTORY.dropBinaryMessage();
    }
}
