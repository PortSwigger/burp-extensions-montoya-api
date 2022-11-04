package burp.api.montoya.websocket;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Text WebSocket message.
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
     * Build a text WebSocket message to be processed.
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
     * Build a text WebSocket message to be processed.
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
     * Build a text WebSocket message to be dropped.
     *
     * @return The {@link TextMessageAction} dropping the message.
     */
    static TextMessageAction drop()
    {
        return FACTORY.dropTextMessage();
    }

    /**
     * Build a websocket text message action.
     *
     * @param payload the binary payload for the message
     * @param action  the action to take for the message.
     *
     * @return The {@link TextMessageAction} containing the message and the action.
     */
    static TextMessageAction textMessageAction(String payload, MessageAction action)
    {
        return FACTORY.textMessageAction(payload, action);
    }
}
