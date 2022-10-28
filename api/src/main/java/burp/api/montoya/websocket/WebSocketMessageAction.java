package burp.api.montoya.websocket;

/**
 * This enum represents the action to be applied to a {@link WebSocketTextMessage} or {@link WebSocketBinaryMessage}.
 */
public enum WebSocketMessageAction
{
    CONTINUE,
    DROP
}
