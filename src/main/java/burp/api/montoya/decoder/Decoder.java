/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.decoder;

/**
 * This interface provides access to the functionality of the Decoder tool.
 */
public interface Decoder
{
    /**
     * This method can be used to send data to the Decoder tool.
     *
     * @param data The data to be sent to Decoder.
     */
    void sendToDecoder(byte[] data);
}
