/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.Registration;

import java.util.List;

/**
 * This interface provides access to the functionality of the Proxy tool.
 */
public interface Proxy
{
    /**
     * This method enables the master interception for Burp Proxy.
     */
    void enableIntercept();

    /**
     * This method disables the master interception for Burp Proxy.
     */
    void disableIntercept();

    /**
     * This method returns details of all items in the Proxy history.
     *
     * @return The list of all the {@link ProxyRequestResponse} items in the
     * Proxy history.
     */
    default List<ProxyRequestResponse> history()
    {
        return history(requestResponse -> true);
    }

    /**
     * This method returns details of items in the Proxy history based on the
     * filter.
     *
     * @param filter An instance of {@link ProxyHistoryFilter} that can be used
     *               to filter the items in the Proxy history.
     * @return The list of {@link ProxyRequestResponse} items in the Proxy
     * history that matched the filter.
     */
    List<ProxyRequestResponse> history(ProxyHistoryFilter filter);

    /**
     * This method is used to register a handler which will be notified of
     * requests being processed by the Proxy tool. Extensions can perform
     * custom analysis or modification of these messages, and control in-UI
     * message interception.
     *
     * @param handler An object created by the extension that implements the
     *                {@link ProxyHttpRequestHandler} interface.
     * @return The {@link Registration} for the handler.
     */
    Registration registerRequestHandler(ProxyHttpRequestHandler handler);

    /**
     * This method is used to register a handler which will be notified of
     * responses being processed by the Proxy tool. Extensions can perform
     * custom analysis or modification of these messages, and control in-UI
     * message interception.
     *
     * @param handler An object created by the extension that implements the
     *                {@link ProxyHttpResponseHandler} interface.
     * @return The {@link Registration} for the handler.
     */
    Registration registerResponseHandler(ProxyHttpResponseHandler handler);
}
