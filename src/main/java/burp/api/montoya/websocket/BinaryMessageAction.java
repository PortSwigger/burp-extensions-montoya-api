package burp.api.montoya.websocket;

import burp.api.montoya.core.ByteArray;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface represents a binary WebSocket message.
 */
public interface BinaryMessageAction
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
     *
     * @return The {@link BinaryMessageAction} containing the message to be processed.
     */
    static BinaryMessageAction continueWith(ByteArray payload)
    {
        return FACTORY.continueWithBinaryMessage(payload);
    }

    /**
     * This is a helper method to build a binary WebSocket message to be processed.
     *
     * @param binaryMessage The binary message payload.
     *
     * @return The {@link BinaryMessageAction} containing the message to be processed.
     */
    static BinaryMessageAction continueWith(BinaryMessage binaryMessage)
    {
        return FACTORY.continueWithBinaryMessage(binaryMessage.payload());
    }

    /**
     * This is a helper method to build a binary WebSocket message to be dropped.
     *
     * @return The {@link BinaryMessageAction} dropping the message.
     */
    static BinaryMessageAction drop()
    {
        return FACTORY.dropBinaryMessage();
    }

    /**
     * Build a binary websocket message action.
     *
     * @param payload the binary payload for the message
     * @param action  the action to take for the message.
     *
     * @return The {@link BinaryMessageAction} containing the message and the action.
     */
    static BinaryMessageAction
    binaryMessageAction(ByteArray payload, MessageAction action)
    {
        return FACTORY.binaryMessageAction(payload, action);
    }
}
