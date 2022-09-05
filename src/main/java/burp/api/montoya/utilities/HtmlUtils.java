/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.utilities;

/**
 * This interface gives you access to HTML encoding and decoding features.
 */
public interface HtmlUtils
{
    /**
     * Encode HTML text using {@link HtmlEncoding#STANDARD} encoding.
     * UTF-8 charset is assumed.
     *
     * @param html {@code String} to be encoded.
     * @return the encoded {@code String}.
     */
    default String encode(String html)
    {
        return encode(html, HtmlEncoding.STANDARD);
    }

    /**
     * Encode HTML text.
     * UTF-8 charset is assumed.
     *
     * @param html     {@code String} to be encoded.
     * @param encoding {@link HtmlEncoding} to be used.
     * @return the encoded {@code String}.
     */
    String encode(String html, HtmlEncoding encoding);

    /**
     * Decode encoded HTML text.
     * UTF-8 charset is assumed.
     *
     * @param encodedHtml {@code String} to be decoded.
     * @return the decoded {@code String}.
     */
    String decode(String encodedHtml);
}
