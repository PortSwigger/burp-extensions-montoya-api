/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.handler;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.Marker;
import burp.api.montoya.core.ToolSource;
import burp.api.montoya.http.message.Cookie;
import burp.api.montoya.http.message.HttpHeader;
import burp.api.montoya.http.message.MimeType;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.http.message.responses.analysis.Attribute;
import burp.api.montoya.http.message.responses.analysis.AttributeType;
import burp.api.montoya.http.message.responses.analysis.KeywordCount;

import java.util.List;

/**
 * Burp {@link HttpResponse} with additional methods to retrieve initiating {@link HttpRequest} as well as the {@link Annotations} and {@link ToolSource} of the request.
 */
public interface HttpResponseReceived extends HttpResponse
{
    /**
     * @return The ID for this response which is identical to the ID on the corresponding request.
     */
    int messageId();

    /**
     * @return initiatingRequest The HTTP request that was sent.
     */
    HttpRequest initiatingRequest();

    /**
     * @return Annotations for request/response.
     */
    Annotations annotations();

    /**
     * @return ToolSource which indicates which Burp tool sent the request.
     */
    ToolSource toolSource();

    /**
     * {@inheritDoc}
     */
    @Override
    short statusCode();

    /**
     * {@inheritDoc}
     */
    @Override
    String reasonPhrase();

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    List<Cookie> cookies();

    /**
     * {@inheritDoc}
     */
    @Override
    MimeType statedMimeType();

    /**
     * {@inheritDoc}
     */
    @Override
    MimeType inferredMimeType();

    /**
     * {@inheritDoc}
     */
    @Override
    List<KeywordCount> keywordCounts(String... keywords);

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    HttpResponse withStatusCode(short statusCode);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withReasonPhrase(String reasonPhrase);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withHttpVersion(String httpVersion);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withBody(String body);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withBody(ByteArray body);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withAddedHeader(HttpHeader header);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withAddedHeader(String name, String value);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withUpdatedHeader(HttpHeader header);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withUpdatedHeader(String name, String value);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withRemovedHeader(HttpHeader header);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withRemovedHeader(String name);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withMarkers(List<Marker> markers);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpResponse withMarkers(Marker... markers);
}
