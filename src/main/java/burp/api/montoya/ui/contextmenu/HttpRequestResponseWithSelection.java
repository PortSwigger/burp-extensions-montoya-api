/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.contextmenu;

import burp.api.montoya.core.Range;
import burp.api.montoya.http.message.HttpRequestResponse;

import java.util.Optional;

/**
 * This class contains information about a user selection of a request or response within a Burp Suite message editor.
 */
public interface HttpRequestResponseWithSelection
{
    /**
     * This will return {@link Optional#empty()} if the user has not made a selection.
     *
     * @return An {@link Optional} range of indices that indicates the position of the users current selection.
     */
    Optional<Range> selectionOffsets();

    /**
     * @return The index of the position for the carat within the current message editor.
     */
    int caretPosition();

    /**
     * @return An instance of {@link HttpRequestResponse} which contains the information about the currently displayed or selected HTTP request/response.
     */
    HttpRequestResponse requestResponse();
}
