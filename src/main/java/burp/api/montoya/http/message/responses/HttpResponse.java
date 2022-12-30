/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.responses;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.Marker;
import burp.api.montoya.http.message.HttpMessage;
import burp.api.montoya.http.message.MimeType;
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
public interface HttpResponse extends HttpMessage
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
     *
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
     *
     * @return List of keyword counts in the order they were provided.
     */
    List<KeywordCount> keywordCounts(String... keywords);

    /**
     * This method is used to retrieve the values of response attributes.
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
     * This is a helper method that builds a modified response with the provided status code.
     *
     * @param statusCode the new status code for response
     *
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withStatusCode(short statusCode);

    /**
     * This is a helper method that builds a modified response with the new reason phrase.
     *
     * @param reasonPhrase the new reason phrase for response
     *
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withReasonPhrase(String reasonPhrase);

    /**
     * This is a helper method that builds a modified response with the new http version.
     *
     * @param httpVersion the new http version for response
     *
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withHttpVersion(String httpVersion);

    /**
     * This is a helper method that builds a modified response with the updated body.
     * Updates Content-Length header.
     *
     * @param body the new body for the response
     *
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withBody(String body);

    /**
     * This is a helper method that builds a modified response with the updated body.
     * Updates Content-Length header.
     *
     * @param body the new body for the response
     *
     * @return A new {@code HttpResponse} instance.
     */
    HttpResponse withBody(ByteArray body);

    /**
     * Adds an HTTP header to the response.
     *
     * @param header The {@link HttpHeader} to add to the response.
     *
     * @return The updated response containing the added header.
     */
    HttpResponse withAddedHeader(HttpHeader header);

    /**
     * Adds an HTTP header to the response.
     *
     * @param name  The name of the header.
     * @param value The value of the header.
     *
     * @return The updated response containing the added header.
     */
    HttpResponse withAddedHeader(String name, String value);

    /**
     * Updates an existing HTTP header in the response with a new value.
     *
     * @param header The {@link HttpHeader} to update containing the new value.
     *
     * @return The updated response containing the updated header.
     */
    HttpResponse withUpdatedHeader(HttpHeader header);

    /**
     * Updates an existing HTTP header in the response with a new value.
     *
     * @param name  The name of the header to update the value of.
     * @param value The new value of the specified HTTP header.
     *
     * @return The updated response containing the updated header.
     */
    HttpResponse withUpdatedHeader(String name, String value);

    /**
     * Removes an existing HTTP header from the current response.
     *
     * @param header The {@link HttpHeader} to remove from the response.
     *
     * @return The updated response containing the removed header.
     */
    HttpResponse withRemovedHeader(HttpHeader header);

    /**
     * Removes an existing HTTP header from the current response.
     *
     * @param name The name of the HTTP header to remove from the response.
     *
     * @return The updated response containing the removed header.
     */
    HttpResponse withRemovedHeader(String name);

    /**
     * This is a helper method used to add request markers to the {@code HttpResponse} instance.
     *
     * @param markers Request markers to add.
     *
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    HttpResponse withMarkers(List<Marker> markers);

    /**
     * This is a helper method used create a new instance with markers to the {@code HttpResponse} instance.
     *
     * @param markers Request markers to add.
     *
     * @return A new {@code MarkedHttpRequestResponse} instance.
     */
    HttpResponse withMarkers(Marker... markers);

    /**
     * This is a helper method to create a new empty instance of {@link HttpResponse}.
     *
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse()
    {
        return FACTORY.httpResponse();
    }

    /**
     * This is a helper method to create a new instance of {@link HttpResponse}.
     * This object's data will be stored in temporary memory-mapped file.
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
     * This is a helper method to create a new instance of {@link HttpResponse}.
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param response The HTTP response.
     *
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse(String response)
    {
        return FACTORY.httpResponse(response);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpResponse} from the given {@link HttpResponse}.
     * This object's data will be stored in temporary memory-mapped file.
     *
     * @param httpResponseToCopy source HTTP response.
     *
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse(HttpResponse httpResponseToCopy)
    {
        return FACTORY.httpResponse(httpResponseToCopy);
    }
}
