/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.responses;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.http.MimeType;
import burp.api.montoya.http.message.Marker;
import burp.api.montoya.http.message.cookies.Cookie;
import burp.api.montoya.http.message.headers.HttpHeader;
import burp.api.montoya.http.message.responses.analysis.Attribute;
import burp.api.montoya.http.message.responses.analysis.AttributeType;
import burp.api.montoya.http.message.responses.analysis.KeywordCount;

import java.util.List;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to retrieve key details about an HTTP response.
 */
public interface HttpResponse
{
    /**
     * This method is used to obtain the HTTP status code contained in the response.
     *
     * @return HTTP status code.
     */
    short statusCode();

    /**
     * This method is used to obtain the HTTP reason phrase contained in the response for HTTP 1 messages.
     * HTTP 2 messages will return a mapped phrase based on the status code.
     * @return HTTP Reason phrase.
     */
    String reasonPhrase();

    /**
     * This method is used to return the HTTP Version text parsed from the response line for HTTP 1 messages.
     * HTTP 2 messages will return "HTTP/2"
     *
     * @return Version string
     */
    String httpVersion();

    /**
     * This method is used to obtain the HTTP headers contained in the message.
     *
     * @return A list of HTTP headers.
     */
    List<HttpHeader> headers();

    /**
     * This method is used to get the body of a message as a byte array.
     *
     * @return The body of a message as a byte array.
     */
    ByteArray body();

    /**
     * This method is used to get the body of a message as a {@code String}.
     *
     * @return The body of a message as a {@code String}.
     */
    String bodyToString();
    /**
     * This method is used to obtain the offset within the message where the message body begins.
     *
     * @return The message body offset.
     */
    int bodyOffset();

    /**
     * This method is used to obtain the markers for the response.
     *
     * @return A list of HTTP headers.
     */
    List<Marker> markers();

    /**
     * This method is used to obtain details of the HTTP cookies set in the response.
     *
     * @return A list of {@link Cookie} objects representing the cookies set in the response, if any.
     */
    List<Cookie> cookies();

    /**
     * This method is used to obtain the MIME type of the response, as stated in the HTTP headers.
     *
     * @return The stated MIME type.
     */
    MimeType statedMimeType();

    /**
     * This method is used to obtain the MIME type of the response, as inferred from the contents of the HTTP message body.
     *
     * @return The inferred MIME type.
     */
    MimeType inferredMimeType();

    /**
     * This method is used to retrieve the number of types given keywords appear in the response.
     *
     * @param keywords Keywords to count.
     * @return List of keyword counts in the order they were provided.
     */
    List<KeywordCount> keywordCounts(String... keywords);

    /**
     * This method is used to retrieve the values of response attributes.
     *
     * @param types Response attributes to retrieve values for.
     * @return List of {@link Attribute} objects.
     */
    List<Attribute> attributes(AttributeType... types);

    /**
     * This method is used to get the message as a byte array.
     *
     * @return The message as a byte array.
     */
    ByteArray toByteArray();

    /**
     * This method is used to get the message as a {@code String}.
     *
     * @return The message as a {@code String}.
     */
    @Override
    String toString();
    
    /**
     * This is a helper method that builds a modified response with the updated body.
     * Updates Content-Length header.
     *
     * @param body the new body for the response
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withBody(String body);

    /**
     * This is a helper method that builds a modified response with the updated body.
     * Updates Content-Length header.
     *
     * @param body the new body for the response
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withBody(ByteArray body);

    /**
     * Adds an HTTP header to the response.
     *
     * @param header The {@link HttpHeader} to add to the response.
     * @return The updated response containing the added header.
     */
    HttpResponse withAddedHeader(HttpHeader header);

    /**
     * Adds an HTTP header to the response.
     *
     * @param name  The name of the header.
     * @param value The value of the header.
     * @return The updated response containing the added header.
     */
    HttpResponse withAddedHeader(String name, String value);

    /**
     * Updates an existing HTTP header in the response with a new value.
     *
     * @param header The {@link HttpHeader} to update containing the new value.
     * @return The updated response containing the updated header.
     */
    HttpResponse withUpdatedHeader(HttpHeader header);

    /**
     * Updates an existing HTTP header in the response with a new value.
     *
     * @param name  The name of the header to update the value of.
     * @param value The new value of the specified HTTP header.
     * @return The updated response containing the updated header.
     */
    HttpResponse withUpdatedHeader(String name, String value);

    /**
     * Removes an existing HTTP header from the current response.
     *
     * @param header The {@link HttpHeader} to remove from the response.
     * @return The updated response containing the removed header.
     */
    HttpResponse withRemovedHeader(HttpHeader header);

    /**
     * Removes an existing HTTP header from the current response.
     *
     * @param name The name of the HTTP header to remove from the response.
     * @return The updated response containing the removed header.
     */
    HttpResponse withRemovedHeader(String name);

    /**
     * This is a helper method used to add request markers to the {@code HttpResponse} instance.
     *
     * @param markers Request markers to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    HttpResponse withMarkers(List<Marker> markers);

    /**
     * This is a helper method used create a new instance with markers to the {@code HttpResponse} instance.
     *
     * @param markers Request markers to add.
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    HttpResponse withMarkers(Marker... markers);
    /**
     * This is a helper method to create a new instance of {@link HttpResponse}.
     *
     * @param response The HTTP response.
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse(ByteArray response)
    {
        return FACTORY.httpResponse(response);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpResponse}.
     *
     * @param response The HTTP response.
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse(String response)
    {
        return FACTORY.httpResponse(response);
    }
}
