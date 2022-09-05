/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.sessions;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.Http;
import burp.api.montoya.http.RequestResult;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;

import java.util.List;

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
     * @param currentRequest        The base request that is currently being processed. The action can query this object to obtain details about the base request.
     * @param annotations    The message annotation on the request.
     * @param macroRequestResponses If the action is invoked following execution of a macro, this parameter contains the result of executing the macro.
     *                              Otherwise, it is an empty list. Actions can use the details of the macro items to perform custom analysis of the macro
     *                              to derive values of non-standard session handling tokens, etc.
     * @return A new {@link burp.api.montoya.http.RequestResult} instance.
     */
    RequestResult handle(HttpRequest currentRequest, Annotations annotations, List<HttpRequestResponse> macroRequestResponses);
}
