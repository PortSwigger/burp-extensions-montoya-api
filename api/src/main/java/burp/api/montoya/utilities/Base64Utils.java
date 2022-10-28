/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.utilities;

import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * This interface contains various methods that give you access to base64 encoding and decoding features.
 */
public interface Base64Utils
{
    /**
     * @return A Base64 encoder.
     * @see java.util.Base64#getEncoder()
     */
    Encoder getEncoder();

    /**
     * @return A Base64 encoder.
     * @see java.util.Base64#getUrlEncoder()
     */
    Encoder getUrlEncoder();

    /**
     * @return A Base64 encoder.
     * @see java.util.Base64#getMimeEncoder()
     */
    Encoder getMimeEncoder();

    /**
     * @param lineLength    the length of each output line (rounded down to nearest multiple
     *                      of 4). If the rounded down line length is not a positive value,
     *                      the output will not be separated in lines
     * @param lineSeparator the line separator for each output line
     * @return A Base64 encoder.
     * @see java.util.Base64#getMimeEncoder(int, byte[])
     */
    Encoder getMimeEncoder(int lineLength, String lineSeparator);

    /**
     * @return A Base64 decoder.
     * @see java.util.Base64#getDecoder()
     */
    Decoder getDecoder();

    /**
     * @return A Base64 decoder.
     * @see java.util.Base64#getUrlDecoder()
     */
    Decoder getUrlDecoder();

    /**
     * @return A Base64 decoder.
     * @see java.util.Base64#getMimeDecoder()
     */
    Decoder getMimeDecoder();
}
