/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.intruder;

import burp.api.montoya.core.Range;
import burp.api.montoya.http.message.requests.HttpRequest;

import java.util.List;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to represent an Intruder request template, which contains the
 * HTTP request and insertion point offsets.
 */
public interface HttpRequestTemplate
{
    /**
     * This method is a helper method used to generate a new {@link HttpRequestTemplate} instance
     * from an {@link HttpRequest} object and a list of insertion point offsets.
     *
     * @param request               An instance of {@link HttpRequest}.
     * @param insertionPointOffsets List of insertion point offsets.
     * @return A new instance of {@link HttpRequestTemplate}.
     */
    static HttpRequestTemplate httpRequestTemplate(HttpRequest request, List<Range> insertionPointOffsets)
    {
        return httpRequestTemplate(request.asBytes(), insertionPointOffsets);
    }

    /**
     * This method is a helper method used to generate new {@link HttpRequestTemplate} instance
     * from an HTTP request in a byte array form and a list of insertion point offsets.
     *
     * @param content               An HTTP request in a byte array form.
     * @param insertionPointOffsets List of insertion point offsets.
     * @return A new instance of {@link HttpRequestTemplate}.
     */
    static HttpRequestTemplate httpRequestTemplate(byte[] content, List<Range> insertionPointOffsets)
    {
       return FACTORY.httpRequestTemplate(content, insertionPointOffsets);
    }

    /**
     * @return Content of the request template.
     */
    byte[] content();

    /**
     * This method is used to obtain insertion point offsets for an Intruder attack.
     *
     * @return A list of {@link Range} objects representing insertion point offsets.
     */
    List<Range> insertionPointOffsets();
}
