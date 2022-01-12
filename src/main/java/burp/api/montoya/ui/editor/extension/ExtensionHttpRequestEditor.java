/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor.extension;

import burp.api.montoya.http.message.requests.HttpRequest;

/**
 * Extensions that register an {@link ExtensionHttpRequestEditorProvider} must return an instance of this interface. Burp will then use that instance to create custom tabs within
 * its HTTP request editor.
 */
public interface ExtensionHttpRequestEditor extends ExtensionHttpMessageEditor
{
    /**
     * Sets the provided {@link HttpRequest} object within the editor component.
     *
     * @param request The request to set in the editor.
     */
    void setHttpRequest(HttpRequest request);

    /**
     * @return An instance of {@link HttpRequest} derived from the content of the HTTP request editor.
     */
    HttpRequest getHttpRequest();

    /**
     * A check to determine if the HTTP request editor is enabled for a specific {@link HttpRequest}
     *
     * @param request The {@link HttpRequest} to check.
     * @return True if the HTTP request editor is enabled for the provided request.
     */
    boolean isEnabledFor(HttpRequest request);
}
