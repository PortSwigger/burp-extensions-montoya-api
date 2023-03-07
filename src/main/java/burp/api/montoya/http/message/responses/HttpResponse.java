/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.responses;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.Marker;
import burp.api.montoya.http.message.Cookie;
import burp.api.montoya.http.message.HttpHeader;
import burp.api.montoya.http.message.HttpMessage;
import burp.api.montoya.http.message.MimeType;
import burp.api.montoya.http.message.responses.analysis.Attribute;
import burp.api.montoya.http.message.responses.analysis.AttributeType;
import burp.api.montoya.http.message.responses.analysis.KeywordCount;

import java.util.List;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Burp HTTP response able to retrieve and modify details about an HTTP response.
 */
public interface HttpResponse extends HttpMessage
{
    /**
     * Obtain the HTTP status code contained in the response.
     *
     * @return HTTP status code.
     */
    short statusCode();

    /**
     * Obtain the HTTP reason phrase contained in the response for HTTP 1 messages.
     * HTTP 2 messages will return a mapped phrase based on the status code.
     *
     * @return HTTP Reason phrase.
     */
    String reasonPhrase();

    /**
     * Return the HTTP Version text parsed from the response line for HTTP 1 messages.
     * HTTP 2 messages will return "HTTP/2"
     *
     * @return Version string
     */
    @Override
    String httpVersion();

    /**
     * {@inheritDoc}
     */
    @Override
    List<HttpHeader> headers();

    /**
     * {@inheritDoc}
     */
    @Override
    ByteArray body();

    /**
     * {@inheritDoc}
     */
    @Override
    String bodyToString();

    /**
     * {@inheritDoc}
     */
    @Override
    int bodyOffset();

    /**
     * {@inheritDoc}
     */
    @Override
    List<Marker> markers();

    /**
     * Obtain details of the HTTP cookies set in the response.
     *
     * @return A list of {@link Cookie} objects representing the cookies set in the response, if any.
     */
    List<Cookie> cookies();

    /**
     * Obtain the MIME type of the response, as stated in the HTTP headers.
     *
     * @return The stated MIME type.
     */
    MimeType statedMimeType();

    /**
     * Obtain the MIME type of the response, as inferred from the contents of the HTTP message body.
     *
     * @return The inferred MIME type.
     */
    MimeType inferredMimeType();

    /**
     * Retrieve the number of types given keywords appear in the response.
     *
     * @param keywords Keywords to count.
     *
     * @return List of keyword counts in the order they were provided.
     */
    List<KeywordCount> keywordCounts(String... keywords);

    /**
     * Retrieve the values of response attributes.
     *
     * @param types Response attributes to retrieve values for.
     *
     * @return List of {@link Attribute} objects.
     */
    List<Attribute> attributes(AttributeType... types);

    /**
     * {@inheritDoc}
     */
    @Override
    ByteArray toByteArray();

    /**
     * {@inheritDoc}
     */
    @Override
    String toString();

    /**
     * Create a copy of the {@code HttpResponse} in temporary file.<br>
     * This method is used to save the {@code HttpResponse} object to a temporary file,
     * so that it is no longer held in memory. Extensions can use this method to convert
     * {@code HttpResponse} objects into a form suitable for long-term usage.
     *
     * @return A new {@code HttpResponse} instance stored in temporary file.
     */
    HttpResponse copyToTempFile();

    /**
     * Create a copy of the {@code HttpResponse} with the provided status code.
     *
     * @param statusCode the new status code for response
     *
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withStatusCode(short statusCode);

    /**
     * Create a copy of the {@code HttpResponse} with the new reason phrase.
     *
     * @param reasonPhrase the new reason phrase for response
     *
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withReasonPhrase(String reasonPhrase);

    /**
     * Create a copy of the {@code HttpResponse} with the new http version.
     *
     * @param httpVersion the new http version for response
     *
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withHttpVersion(String httpVersion);

    /**
     * Create a copy of the {@code HttpResponse} with the updated body.<br>
     * Updates Content-Length header.
     *
     * @param body the new body for the response
     *
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withBody(String body);

    /**
     * Create a copy of the {@code HttpResponse} with the updated body.<br>
     * Updates Content-Length header.
     *
     * @param body the new body for the response
     *
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withBody(ByteArray body);

    /**
     * Create a copy of the {@code HttpResponse} with the added header.
     *
     * @param header The {@link HttpHeader} to add to the response.
     *
     * @return The updated response containing the added header.
     */
    HttpResponse withAddedHeader(HttpHeader header);

    /**
     * Create a copy of the {@code HttpResponse}  with the added header.
     *
     * @param name  The name of the header.
     * @param value The value of the header.
     *
     * @return The updated response containing the added header.
     */
    HttpResponse withAddedHeader(String name, String value);

    /**
     * Create a copy of the {@code HttpResponse}  with the updated header.
     *
     * @param header The {@link HttpHeader} to update containing the new value.
     *
     * @return The updated response containing the updated header.
     */
    HttpResponse withUpdatedHeader(HttpHeader header);

    /**
     * Create a copy of the {@code HttpResponse}  with the updated header.
     *
     * @param name  The name of the header to update the value of.
     * @param value The new value of the specified HTTP header.
     *
     * @return The updated response containing the updated header.
     */
    HttpResponse withUpdatedHeader(String name, String value);

    /**
     * Create a copy of the {@code HttpResponse}  with the removed header.
     *
     * @param header The {@link HttpHeader} to remove from the response.
     *
     * @return The updated response containing the removed header.
     */
    HttpResponse withRemovedHeader(HttpHeader header);

    /**
     * Create a copy of the {@code HttpResponse}  with the removed header.
     *
     * @param name The name of the HTTP header to remove from the response.
     *
     * @return The updated response containing the removed header.
     */
    HttpResponse withRemovedHeader(String name);

    /**
     * Create a copy of the {@code HttpResponse} with the added markers.
     *
     * @param markers Request markers to add.
     *
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    HttpResponse withMarkers(List<Marker> markers);

    /**
     * Create a copy of the {@code HttpResponse} with the added markers.
     *
     * @param markers Request markers to add.
     *
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    HttpResponse withMarkers(Marker... markers);

    /**
     * Create a new empty instance of {@link HttpResponse}.<br>
     *
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse()
    {
        return FACTORY.httpResponse();
    }

    /**
     * Create a new instance of {@link HttpResponse}.<br>
     *
     * @param response The HTTP response.
     *
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse(ByteArray response)
    {
        return FACTORY.httpResponse(response);
    }

    /**
     * Create a new instance of {@link HttpResponse}.<br>
     *
     * @param response The HTTP response.
     *
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse(String response)
    {
        return FACTORY.httpResponse(response);
    }
}
