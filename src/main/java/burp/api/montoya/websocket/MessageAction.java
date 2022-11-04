package burp.api.montoya.websocket;

/**
 * This enum represents the action to be applied to a {@link TextMessageAction} or {@link BinaryMessageAction}.
 */
public enum MessageAction
{
    /**
     * Causes Burp to forward the message.
     */
    CONTINUE,

    /**
     * Causes Burp to drop the message.
     */
    DROP
}
