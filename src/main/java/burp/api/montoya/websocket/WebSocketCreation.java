package burp.api.montoya.websocket;

import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.Optional;

/**
 * Result of a WebSocket creation attempt
 */
public interface WebSocketCreation
{
    /**
     * The status of the WebSocket creation attempt.
     *
     * @return The {@link WebSocketCreationStatus} creation status
     */
    WebSocketCreationStatus status();

    /**
     * The created WebSocket.
     *
     * @return the created {@link WebSocket}
     */
    Optional<WebSocket> webSocket();

    /**
     * The HTTP response from the WebSocket creation attempt.
     *
     * @return the {@link HttpResponse}
     */
    Optional<HttpResponse> upgradeResponse();
}
