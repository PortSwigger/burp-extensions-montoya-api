package burp.api.montoya.websocket;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface represents a text WebSocket message.
 */
public interface TextMessageAction
{
    /**
     * @return The action associated with this message.
     */
    MessageAction action();

    /**
     * @return The payload of this message.
     */
    String payload();

    /**
     * This is a helper method to build a text WebSocket message to be processed.
     *
     * @param payload The text message payload.
     *
     * @return The {@link TextMessageAction} containing the message to be processed.
     */
    static TextMessageAction continueWith(String payload)
    {
        return FACTORY.continueWithTextMessage(payload);
    }

    /**
     * This is a helper method to build a text WebSocket message to be processed.
     *
     * @param textMessage the text message payload
     *
     * @return The {@link TextMessageAction} containing the message to be processed.
     */
    static TextMessageAction continueWith(TextMessage textMessage)
    {
        return FACTORY.continueWithTextMessage(textMessage.payload());
    }

    /**
     * This is a helper method to build a text WebSocket message to be dropped.
     *
     * @return The {@link TextMessageAction} dropping the message.
     */
    static TextMessageAction drop()
    {
        return FACTORY.dropTextMessage();
    }
}
