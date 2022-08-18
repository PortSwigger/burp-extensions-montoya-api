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
import burp.api.montoya.http.message.responses.analysis.Attribute;
import burp.api.montoya.http.message.responses.analysis.AttributeType;
import burp.api.montoya.http.message.responses.analysis.KeywordCount;

import java.util.List;

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
}
