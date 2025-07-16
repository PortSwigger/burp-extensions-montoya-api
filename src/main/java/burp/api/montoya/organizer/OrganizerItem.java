package burp.api.montoya.organizer;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

/**
 * Represents a {@link HttpRequest} and {@link HttpResponse} pair,
 * along with its status in Organizer.
 */
public interface OrganizerItem extends HttpRequestResponse
{
    /**
     * Returns the index number of the Organizer item.
     *
     * @return The item index number.
     */
    int id();

    /**
     * Returns the item status.
     *
     * @return The item status.
     */
    OrganizerItemStatus status();
}
