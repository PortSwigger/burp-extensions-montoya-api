/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.Marker;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Burp message retrieve common information shared by {@link HttpRequest} and {@link HttpResponse}.
 */
public interface HttpMessage
{
    /**
     * HTTP headers contained in the message.
     *
     * @return A list of HTTP headers.
     */
    List<HttpHeader> headers();

    /**
     * HTTP Version text parsed from the request or response line for HTTP 1 messages.
     * HTTP 2 messages will return "HTTP/2"
     *
     * @return Version string
     */
    String httpVersion();

    /**
     * Offset within the message where the message body begins.
     *
     * @return The message body offset.
     */
    int bodyOffset();

    /**
     * Body of a message as a byte array.
     *
     * @return The body of a message as a byte array.
     */
    ByteArray body();

    /**
     * Body of a message as a {@code String}.
     *
     * @return The body of a message as a {@code String}.
     */
    String bodyToString();

    /**
     * Markers for the message.
     *
     * @return A list of markers.
     */
    List<Marker> markers();

    /**
     * Searches the data in the HTTP message for the specified search term.
     *
     * @param searchTerm    The value to be searched for.
     * @param caseSensitive Flags whether the search is case-sensitive.
     *
     * @return True if the search term is found.
     */
    boolean contains(String searchTerm, boolean caseSensitive);

    /**
     * Searches the data in the HTTP message for the specified regular expression.
     *
     * @param pattern The regular expression to be searched for.
     *
     * @return True if the pattern is matched.
     */
    boolean contains(Pattern pattern);

    /**
     * Message as a byte array.
     *
     * @return The message as a byte array.
     */
    ByteArray toByteArray();

    /**
     * Message as a {@code String}.
     *
     * @return The message as a {@code String}.
     */
    @Override
    String toString();
}
