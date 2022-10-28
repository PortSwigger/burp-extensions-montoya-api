/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.params;

import burp.api.montoya.core.Range;

/**
 * This interface is used to hold additional details about an HTTP request parameter that has been parsed by Burp.
 */
public interface ParsedHttpParameter extends HttpParameter
{
    /**
     * This method is used to retrieve the offsets of the parameter name within the HTTP request.
     *
     * @return The parameter name offsets.
     */
    Range nameOffsets();

    /**
     * This method is used to retrieve the offsets of the parameter value within the HTTP request.
     *
     * @return The parameter value offsets.
     */
    Range valueOffsets();
}
