/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.proxy.http;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.Marker;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.message.ContentType;
import burp.api.montoya.http.message.HttpHeader;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.ParsedHttpParameter;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.requests.HttpTransformation;

import java.net.InetAddress;
import java.util.List;

/**
 * HTTP request intercepted by Burp Proxy.
 */
public interface InterceptedRequest extends InterceptedHttpMessage, HttpRequest
{

    /**
     * @return Annotations for request/response.
     */
    Annotations annotations();

    /**
     * {@inheritDoc}
     */
    @Override
    HttpService httpService();

    /**
     * {@inheritDoc}
     */
    @Override
    String url();

    /**
     * {@inheritDoc}
     */
    @Override
    String method();

    /**
     * {@inheritDoc}
     */
    @Override
    String path();

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
    ContentType contentType();

    /**
     * {@inheritDoc}
     */
    @Override
    List<ParsedHttpParameter> parameters();

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
    ByteArray toByteArray();

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withService(HttpService service);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withPath(String path);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withMethod(String method);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withAddedParameters(List<HttpParameter> parameters);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withAddedParameters(HttpParameter... parameters);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withRemovedParameters(List<HttpParameter> parameters);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withRemovedParameters(HttpParameter... parameters);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withUpdatedParameters(List<HttpParameter> parameters);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withUpdatedParameters(HttpParameter... parameters);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withTransformationApplied(HttpTransformation transformation);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withBody(String body);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withBody(ByteArray body);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withAddedHeader(String name, String value);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withAddedHeader(HttpHeader header);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withUpdatedHeader(String name, String value);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withUpdatedHeader(HttpHeader header);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withRemovedHeader(String name);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withRemovedHeader(HttpHeader header);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withMarkers(List<Marker> markers);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withMarkers(Marker... markers);

    /**
     * {@inheritDoc}
     */
    @Override
    HttpRequest withDefaultHeaders();

    /**
     * {@inheritDoc}
     */
    @Override
    int messageId();

    /**
     * {@inheritDoc}
     */
    @Override
    String listenerInterface();

    /**
     * {@inheritDoc}
     */
    @Override
    InetAddress sourceIpAddress();

    /**
     * {@inheritDoc}
     */
    @Override
    InetAddress destinationIpAddress();
}
