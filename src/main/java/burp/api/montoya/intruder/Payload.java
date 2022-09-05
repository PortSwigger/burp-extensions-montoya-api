/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.intruder;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * This interface represents an Intruder payload.
 */
public interface Payload
{
    /**
     * A null payload. This should be returned by a {@link PayloadGenerator} when it has finished
     * generating payloads.
     */
    Payload END = () -> null;

    /**
     * This is a helper method to create a new {@link Payload} instance from a String payload value.
     *
     * @param payload String payload value. UTF-8 charset is assumed.
     * @return A new {@link Payload} instance.
     */
    static Payload from(String payload)
    {
        return from(payload.getBytes(UTF_8));
    }

    /**
     * This method is a helper method to create a new {@link Payload} instance from a byte array payload value.
     *
     * @param payload Byte array payload value.
     * @return A new {@link Payload} instance.
     */
    static Payload from(byte[] payload)
    {
        return () -> payload;
    }

    /**
     * @return Payload value.
     */
    byte[] value();
}
