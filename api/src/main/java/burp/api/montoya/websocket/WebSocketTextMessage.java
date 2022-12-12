package burp.api.montoya.websocket;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface represents a text WebSocket message.
 */
public interface WebSocketTextMessage
{
    /**
     * @return The action associated with this message.
     */
    WebSocketMessageAction action();

    /**
     * @return The payload of this message.
     */
    String payload();

    /**
     * This is a helper method to build a text WebSocket message to be processed.
     * @param payload The text message payload.
     * @return The message to be processed.
     */
    static WebSocketTextMessage continueWithTextMessage(String payload)
    {
        return FACTORY.continueWithTextMessage(payload);
    }

    /**
     * This is a helper method to build a text WebSocket message to be dropped.
     * @return The message to be dropped.
     */
    static WebSocketTextMessage dropTextMessage()
    {
        return FACTORY.dropTextMessage();
    }
}
