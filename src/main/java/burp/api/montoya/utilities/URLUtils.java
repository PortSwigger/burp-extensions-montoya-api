/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.utilities;

import burp.api.montoya.core.ByteArray;

/**
 * This interface gives you access to URL encoding and decoding features.
 */
public interface URLUtils
{
    /**
     * This method is equivalent to calling {@link #encode(String, URLEncoding)} with {@link URLEncoding#JAVA_DEFAULT}.
     *
     * @param string {@code String} to be url encoded.
     *
     * @return the url encoded {@code String}.
     *
     * @see java.net.URLEncoder#encode(String, String)
     */
    String encode(String string);

    /**
     * @param string {@code String} to be url encoded.
     * @param encoding {@link URLEncoding} to be used.
     *
     * @return the url encoded {@code String}.
     */
    String encode(String string, URLEncoding encoding);

    /**
     * @param string the {@code String} to be url decoded
     *
     * @return the url decoded {@code String}
     *
     * @see java.net.URLDecoder#decode(String, String)
     */
    String decode(String string);

    /**
     * @param byteArray {@link ByteArray} to be url encoded.
     *
     * @return the url encoded {@link ByteArray}.
     *
     * @see java.net.URLEncoder#encode(String, String)
     */
    ByteArray encode(ByteArray byteArray);

    /**
     * @param byteArray the {@link ByteArray} to be url decoded
     *
     * @return the url decoded {@link ByteArray}
     *
     * @see java.net.URLDecoder#decode(String, String)
     */
    ByteArray decode(ByteArray byteArray);
}
