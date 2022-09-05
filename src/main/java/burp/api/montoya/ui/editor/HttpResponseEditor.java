/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor;

import burp.api.montoya.http.message.responses.HttpResponse;

/**
 * This interface provides extensions with an instance of Burp Suites HTTP response editor to use in their own user interface.
 */
public interface HttpResponseEditor extends Editor
{
    /**
     * @return an instance of {@link HttpResponse} derived from the contents of the editor.
     */
    HttpResponse getResponse();

    /**
     * This method is used to display the contents of an HTTP response in the editor.
     *
     * @param response The HTTP response to be set.
     */
    void setResponse(HttpResponse response);
}
