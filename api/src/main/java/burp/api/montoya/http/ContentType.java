/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

/**
 * This enum defines content types recognised by Burp.
 */
public enum ContentType
{
    NONE,
    UNKNOWN,
    AMF,
    JSON,
    MULTIPART,
    URL_ENCODED,
    XML
}
