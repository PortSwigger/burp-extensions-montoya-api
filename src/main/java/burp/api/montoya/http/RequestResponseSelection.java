package burp.api.montoya.http;

import burp.api.montoya.ui.Selection;

/**
 * Provides access to user-selected content and its start and end points in HTTP requests and responses.
 */
public interface RequestResponseSelection
{
    /**
     * Provides access to user-selected content and its start and end points from the HTTP request.
     * @return The selection for the request.
     */
    Selection requestSelection();

    /**
     * Provides access to user-selected content and its start and end points from the HTTP response.
     * @return The selection for the response.
     */
    Selection responseSelection();

    /**
     * Indicates whether user-selected content from the HTTP request is available.
     * @return True if there is user-selected content from the HTTP request.
     */
    boolean hasRequestSelection();

    /**
     * Indicates whether user-selected content from the HTTP response is available.
     * @return True if there is user-selected content from the HTTP response.
     */
    boolean hasResponseSelection();
}
