/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.responses;

import burp.api.montoya.http.MimeType;
import burp.api.montoya.http.message.HttpMessage;
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
     * This is a helper method to create a new instance of {@link HttpResponse}.
     *
     * @param response The HTTP response.
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse(byte[] response)
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

    /**
     * This is a helper method to create a new instance of {@link HttpResponse}.
     *
     * @param headers A list of HTTP headers.
     * @param body    An HTTP response body.
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse(List<String> headers, byte[] body)
    {
        return FACTORY.httpResponse(headers, body);
    }

    /**
     * This is a helper method to create a new instance of {@link HttpResponse}.
     *
     * @param headers A list of HTTP headers.
     * @param body    An HTTP response body.
     * @return A new {@link HttpResponse} instance.
     */
    static HttpResponse httpResponse(List<String> headers, String body)
    {
        return FACTORY.httpResponse(headers, body);
    }

    /**
     * This method is used to obtain the HTTP status code contained in the response.
     *
     * @return HTTP status code.
     */
    short statusCode();

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
    HttpResponse withBody(byte[] body);

    /**
     * Adds an HTTP header to the response.
     *
     * @param header The {@link HttpHeader} to add to the response.
     * @return The updated response containing the added header.
     */
    HttpResponse addHeader(HttpHeader header);

    /**
     * Adds an HTTP header to the response.
     *
     * @param name  The name of the header.
     * @param value The value of the header.
     * @return The updated response containing the added header.
     */
    HttpResponse addHeader(String name, String value);

    /**
     * Updates an existing HTTP header in the response with a new value.
     *
     * @param header The {@link HttpHeader} to update containing the new value.
     * @return The updated response containing the updated header.
     */
    HttpResponse updateHeader(HttpHeader header);

    /**
     * Updates an existing HTTP header in the response with a new value.
     *
     * @param name  The name of the header to update the value of.
     * @param value The new value of the specified HTTP header.
     * @return The updated response containing the updated header.
     */
    HttpResponse updateHeader(String name, String value);

    /**
     * Removes an existing HTTP header from the current response.
     *
     * @param header The {@link HttpHeader} to remove from the response.
     * @return The updated response containing the removed header.
     */
    HttpResponse removeHeader(HttpHeader header);

    /**
     * Removes an existing HTTP header from the current response.
     *
     * @param name The name of the HTTP header to remove from the response.
     * @return The updated response containing the removed header.
     */
    HttpResponse removeHeader(String name);
}
