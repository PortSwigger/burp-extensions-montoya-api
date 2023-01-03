/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.requests;

/**
 * This class represents an exception which is thrown when trying to retrieve attributes from a malformed request.
 */
public class MalformedRequestException extends RuntimeException
{
    public MalformedRequestException(String message)
    {
        super(message);
    }
}