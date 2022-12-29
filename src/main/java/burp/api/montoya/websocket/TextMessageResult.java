package burp.api.montoya.websocket;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface represents a text WebSocket message.
 */
public interface TextMessageResult
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
     * @return The message to be processed.
     */
    static TextMessageResult continueWith(String payload)
    {
        return FACTORY.continueWithTextMessage(payload);
    }

    /**
     * This is a helper method to build a text WebSocket message to be processed.
     *
     * @param textMessage the text message payload
     * @return The message to be processed.
     */
    static TextMessageResult continueWith(TextMessage textMessage)
    {
        return FACTORY.continueWithTextMessage(textMessage.payload());
    }

    /**
     * This is a helper method to build a text WebSocket message to be dropped.
     *
     * @return The message to be dropped.
     */
    static TextMessageResult dropTextMessage()
    {
        return FACTORY.dropTextMessage();
    }
}
