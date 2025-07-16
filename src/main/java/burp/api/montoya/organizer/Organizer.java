/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.organizer;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;

import java.util.List;

/**
 * Provides access to the functionality of the Organizer tool.
 */
public interface Organizer
{
    /**
     * This method can be used to send an HTTP request to the Burp Organizer
     * tool.
     *
     * @param request The full HTTP request.
     */
    void sendToOrganizer(HttpRequest request);

    /**
     * This method can be used to send an HTTP request and response to the Burp
     * Organizer tool.
     *
     * @param requestResponse The full HTTP request and response.
     */
    void sendToOrganizer(HttpRequestResponse requestResponse);

    /**
     * Returns all items in Organizer.
     *
     * @return A list of all {@link OrganizerItem} items.
     */
    List<OrganizerItem> items();

    /**
     * Returns Organizer items that match the specified filter.
     *
     * @param filter The {@link OrganizerItemFilter} used to
     *               determine which items to include.
     *
     * @return A list of {@link OrganizerItem} items
     * that match the filter.
     */
    List<OrganizerItem> items(OrganizerItemFilter filter);
}
