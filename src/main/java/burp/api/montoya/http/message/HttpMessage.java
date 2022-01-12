/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message;

import burp.api.montoya.http.message.headers.HttpHeader;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.List;

/**
 * This interface is used to retrieve common information shared by {@link HttpRequest} and {@link HttpResponse} interfaces.
 */
public interface HttpMessage
{
    /**
     * This method is used to obtain the HTTP headers contained in the message.
     *
     * @return A list of HTTP headers.
     */
    List<HttpHeader> headers();

    /**
     * This method is used to obtain the offset within the message where the message body begins.
     *
     * @return The message body offset.
     */
    int bodyOffset();

    /**
     * This method is used to get the message as a byte array.
     *
     * @return The message as a byte array.
     */
    byte[] asBytes();

    /**
     * This method is used to get the message as a {@code String}.
     *
     * @return The message as a {@code String}.
     */
    @Override
    String toString();

    /**
     * This method is used to get the body of a message as a byte array.
     *
     * @return The body of a message as a byte array.
     */
    byte[] body();

    /**
     * This method is used to get the body of a message as a {@code String}.
     *
     * @return The body of a message as a {@code String}.
     */
    String bodyAsString();
}
