/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.sessions;

import burp.api.montoya.http.Http;
import burp.api.montoya.http.RequestResult;

/**
 * Extensions can implement this interface and then call {@link Http#registerSessionHandlingAction} to register a custom session handling action.
 * Each registered action will be available within the session handling rule UI for the user to select as a rule action. Users can choose to
 * invoke an action directly in its own right, or following execution of a macro.
 */
public interface SessionHandlingAction
{
    /**
     * @return Action name
     */
    String name();

    /**
     * This method is invoked when the session handling action should be executed.
     *
     * @param actionHttpRequest {@link SessionHandlingHttpRequest} The base request that is currently being processed.
     *                          The action can query this object to obtain details about the base request.
     * @return A new {@link RequestResult} instance.
     */
    RequestResult handle(SessionHandlingHttpRequest actionHttpRequest);
}
