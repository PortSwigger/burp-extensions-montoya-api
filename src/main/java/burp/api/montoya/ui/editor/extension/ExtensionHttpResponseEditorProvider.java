/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor.extension;

import burp.api.montoya.http.message.HttpRequestResponse;

/**
 * Extensions can register an instance of this interface to provide custom HTTP response editors within Burp's user interface.
 */
public interface ExtensionHttpResponseEditorProvider
{
    /**
     * This method is invoked by Burp when a new HTTP response editor is created.
     *
     * @param httpRequestResponse The instance of {@link HttpRequestResponse} to be set in the editor.
     * @param editorMode          What mode the created editor should implement.
     * @return An instance of {@link ExtensionHttpResponseEditor}
     */
    ExtensionHttpResponseEditor provideHttpResponseEditor(HttpRequestResponse httpRequestResponse, EditorMode editorMode);
}
