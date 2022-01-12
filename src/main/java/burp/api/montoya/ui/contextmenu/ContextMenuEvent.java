/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.contextmenu;

import burp.api.montoya.core.ToolSource;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.scanner.audit.issues.AuditIssue;

import java.util.List;
import java.util.Optional;

/**
 *  This interface provides useful information when generating context menu items from a {@link ContextMenuItemsProvider}.
 */
public interface ContextMenuEvent extends ComponentEvent, ToolSource, InvocationSource
{
    /**
     * This method can be used to retrieve details of the currently selected HTTP request/response that were selected by the user when the context menu was invoked.
     * This will return {@link Optional#empty()} if the user has not made a selection.
     *
     * @return an {@link Optional} describing the currently selected request response with selection position metadata.
     */
    Optional<HttpRequestResponseWithSelection> requestResponseWithSelection();

    /**
     * This method can be used to retrieve details of the currently selected HTTP request/response that were selected by the user when the context menu was invoked.
     * This will return an empty list if the user has not made a selection.
     *
     * @return A list of request responses that have been selected by the user.
     */
    List<HttpRequestResponse> requestResponses();


    /**
     * This method can be used to retrieve details of the Scanner issues that were selected by the user when the context menu was invoked.
     * This will return an empty list if no issues are applicable to the invocation.
     *
     * @return a List of {@link AuditIssue} objects representing the items that were shown or selected by the user when the context menu was invoked.
     */
    List<AuditIssue> issues();
}
