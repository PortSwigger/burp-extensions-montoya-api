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
     * @return An instance of {@link HttpRequest} derived from the content of the HTTP request editor.
     */
    HttpRequest getHttpRequest();
}
