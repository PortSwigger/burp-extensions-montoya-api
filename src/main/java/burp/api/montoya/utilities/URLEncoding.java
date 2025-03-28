/*
 * Copyright (c) 2022-2025. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.utilities;

public enum URLEncoding
{
    /**
     * Encode using {@link java.net.URLEncoder}.
     */
    JAVA_DEFAULT,

    /**
     * Encode key characters only.
     */
    KEY_CHARACTERS,

    /**
     * Encode all characters.
     */
    ALL_CHARACTERS,

    /**
     * Encode all characters to Unicode.
     */
    ALL_CHARACTERS_UNICODE
}
