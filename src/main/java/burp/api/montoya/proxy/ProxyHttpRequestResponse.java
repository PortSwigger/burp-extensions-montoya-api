/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.regex.Pattern;

/**
 * HTTP request and response intercepted by the Proxy.
 */
public interface ProxyHttpRequestResponse
{
    /**
     * This method retrieves the annotations for the request/response pair.
     *
     * @return The {@link Annotations} for the request/response pair.
     */
    Annotations annotations();

    /**
     * This method retrieves the HTTP request that was sent by Burp Proxy.
     *
     * @return The {@link HttpRequest} that was sent by Burp Proxy.
     */
    HttpRequest finalRequest();

    /**
     * This method retrieves the HTTP response that was received by Burp Proxy.
     *
     * @return The {@link HttpResponse} that was received by Burp Proxy.
     */
    HttpResponse originalResponse();

    /**
     * @return True if there is a response.
     */
    boolean hasResponse();

    /**
     * @return True if the request or response was edited
     */
    boolean edited();

    /**
     * Searches the data in the HTTP request and response for the specified search term.
     *
     * @param searchTerm    The value to be searched for.
     * @param caseSensitive Flags whether the search is case-sensitive.
     *
     * @return True if the search term is found.
     */
    boolean contains(String searchTerm, boolean caseSensitive);

    /**
     * Searches the data in the HTTP request and response for the specified regular expression.
     *
     * @param pattern The regular expression to be searched for.
     *
     * @return True if the pattern is matched.
     */
    boolean contains(Pattern pattern);
}
