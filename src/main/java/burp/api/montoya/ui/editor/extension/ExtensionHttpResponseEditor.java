/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor.extension;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.ui.Selection;

import java.awt.Component;

/**
 * Extensions that register an {@link HttpResponseEditorProvider} must return an instance of this interface. Burp will then use that instance to create custom tabs within
 * its HTTP response editor.
 */
public interface ExtensionHttpResponseEditor
{
    /**
     * @return An instance of {@link HttpResponse} derived from the content of the HTTP response editor.
     */
    HttpResponse getHttpResponse();

    /**
     * Sets the provided {@link HttpRequestResponse} object within the editor component.
     *
     * @param requestResponse The request and response to set in the editor.
     */
    void setHttpRequestResponse(HttpRequestResponse requestResponse);

    /**
     * A check to determine if the HTTP message editor is enabled for a specific {@link HttpRequestResponse}
     *
     * @param requestResponse The {@link HttpRequestResponse} to check.
     * @return True if the HTTP message editor is enabled for the provided request and response.
     */
    boolean isEnabledFor(HttpRequestResponse requestResponse);

    /**
     * @return The caption located in the message editor tab header.
     */
    String caption();

    /**
     * @return The component that is rendered within the message editor tab.
     */
    Component uiComponent();

    /**
     * The method should return {@code null} if no data has been selected.
     *
     * @return The data that is currently selected by the user.
     */
    Selection selectedData();

    /**
     * @return True if the user has modified the current message within the editor.
     */
    boolean isModified();
}
