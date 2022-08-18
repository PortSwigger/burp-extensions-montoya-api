/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor.extension;

import burp.api.montoya.http.message.responses.HttpResponse;

/**
 * Extensions that register an {@link ExtensionHttpResponseEditorProvider} must return an instance of this interface. Burp will then use that instance to create custom tabs within
 * its HTTP response editor.
 */
public interface ExtensionHttpResponseEditor extends ExtensionHttpMessageEditor
{
    /**
     * @return An instance of {@link HttpResponse} derived from the content of the HTTP response editor.
     */
    HttpResponse getHttpResponse();
}
