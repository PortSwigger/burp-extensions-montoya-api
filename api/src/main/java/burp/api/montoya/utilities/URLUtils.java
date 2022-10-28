/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.utilities;

/**
 * This interface gives you access to URL encoding and decoding features.
 */
public interface URLUtils
{
    /**
     * UTF-8 charset is assumed.
     *
     * @param string {@code String} to be translated.
     * @return the translated {@code String}.
     * @see java.net.URLEncoder#encode(String, String)
     */
    String encode(String string);

    /**
     * UTF-8 charset is assumed.
     *
     * @param string the {@code String} to decode
     * @return the newly decoded {@code String}
     * @see java.net.URLDecoder#decode(String, String)
     */
    String decode(String string);
}
