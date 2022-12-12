/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.internal;

import burp.api.montoya.collaborator.InteractionFilter;
import burp.api.montoya.collaborator.SecretKey;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.HighlightColor;
import burp.api.montoya.core.Range;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.RequestResult;
import burp.api.montoya.http.ResponseResult;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.Marker;
import burp.api.montoya.http.message.headers.HttpHeader;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.HttpParameterType;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.intruder.HttpRequestTemplate;
import burp.api.montoya.intruder.PayloadProcessingAction;
import burp.api.montoya.intruder.PayloadProcessingResult;
import burp.api.montoya.persistence.PersistedList;
import burp.api.montoya.persistence.PersistedObject;
import burp.api.montoya.proxy.FinalInterceptAction;
import burp.api.montoya.proxy.InitialInterceptAction;
import burp.api.montoya.proxy.ProxyWebSocketFinalInterceptBinaryMessage;
import burp.api.montoya.proxy.ProxyWebSocketFinalInterceptTextMessage;
import burp.api.montoya.proxy.ProxyWebSocketInitialInterceptBinaryMessage;
import burp.api.montoya.proxy.ProxyWebSocketInitialInterceptTextMessage;
import burp.api.montoya.proxy.RequestFinalInterceptResult;
import burp.api.montoya.proxy.RequestInitialInterceptResult;
import burp.api.montoya.proxy.ResponseFinalInterceptResult;
import burp.api.montoya.proxy.ResponseInitialInterceptResult;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.scanner.audit.issues.AuditIssueConfidence;
import burp.api.montoya.scanner.audit.issues.AuditIssueDefinition;
import burp.api.montoya.scanner.audit.issues.AuditIssueSeverity;
import burp.api.montoya.sitemap.SiteMapFilter;
import burp.api.montoya.ui.Selection;
import burp.api.montoya.websocket.WebSocketBinaryMessage;
import burp.api.montoya.websocket.WebSocketTextMessage;

import java.util.List;

public interface MontoyaObjectFactory
{
    HttpService httpService(String baseUrl);

    HttpService httpService(String host, boolean secure);

    HttpService httpService(String host, int port, boolean secure);

    HttpHeader httpHeader(String name, String value);

    HttpHeader httpHeader(String header);

    HttpParameter parameter(String name, String value, HttpParameterType type);
    
    HttpRequest httpRequest();

    HttpRequest httpRequest(ByteArray request);

    HttpRequest httpRequest(String request);
    
    HttpRequest httpRequest(HttpService service, ByteArray request);

    HttpRequest httpRequest(HttpService service, String request);

    HttpRequest httpRequest(HttpRequest httpRequestToCopy);

    HttpRequest http2Request(HttpService service, List<HttpHeader> headers, String body);

    HttpRequest http2Request(HttpService service, List<HttpHeader> headers, ByteArray body);

    HttpRequest httpRequestFromUrl(String url);

    HttpResponse httpResponse(String response);

    HttpResponse httpResponse(ByteArray response);

    HttpResponse httpResponse(HttpResponse httpResponseToCopy);

    HttpRequestResponse httpRequestResponse(HttpRequest request, HttpResponse response, Annotations annotations);

    HttpRequestResponse httpRequestResponse(HttpRequest request, HttpResponse response);

    HttpRequestResponse httpRequestResponse(HttpRequestResponse httpRequestResponseToCopy);

    Range range(int startIndexInclusive, int endIndexExclusive);
    
    Annotations annotations();

    Annotations annotations(String comment);

    Annotations annotations(HighlightColor highlightColor);
    
    Annotations annotations(String comment, HighlightColor highlightColor);

    AuditInsertionPoint auditInsertionPoint(String name, HttpRequest baseRequest, int startIndexInclusive, int endIndexExclusive);

    AuditIssueDefinition auditIssueDefinition(String name, String background, String remediation, AuditIssueSeverity typicalSeverity);

    AuditIssue auditIssue(String name, String detail, String remediation, String baseUrl, AuditIssueSeverity severity, AuditIssueConfidence confidence, String background, String remediationBackground, AuditIssueSeverity typicalSeverity, List<HttpRequestResponse> requestResponses);

    Selection selection(ByteArray selectionContents);

    Selection selection(int startIndexInclusive, int endIndexExclusive);

    Selection selection(ByteArray selectionContents, int startIndexInclusive, int endIndexExclusive);

    SecretKey secretKey(String encodedKey);

    RequestInitialInterceptResult initialInterceptResult(HttpRequest request, Annotations annotations, InitialInterceptAction action);

    RequestFinalInterceptResult finalInterceptResult(HttpRequest request, Annotations annotations, FinalInterceptAction action);

    ResponseFinalInterceptResult finalInterceptResult(HttpResponse response, Annotations annotations, FinalInterceptAction action);

    ResponseInitialInterceptResult initialInterceptResult(HttpResponse response, Annotations annotations, InitialInterceptAction action);

    RequestResult requestResult(HttpRequest request, Annotations annotations);

    ResponseResult responseResult(HttpResponse response, Annotations annotations);

    HttpRequestTemplate httpRequestTemplate(ByteArray content, List<Range> insertionPointOffsets);

    PayloadProcessingResult payloadProcessingResult(ByteArray processedPayload, PayloadProcessingAction action);

    InteractionFilter interactionIdFilter(String id);

    InteractionFilter interactionPayloadFilter(String payload);

    SiteMapFilter prefixFilter(String prefix);

    Marker marker(Range range);

    Marker marker(int startIndexInclusive, int endIndexExclusive);

    ByteArray byteArrayOfLength(int length);

    ByteArray byteArray(byte[] bytes);

    ByteArray byteArray(int[] ints);

    ByteArray byteArray(String text);

    ByteArray byteArray(ByteArray byteArrayToCopy);

    WebSocketTextMessage continueWithTextMessage(String payload);

    WebSocketTextMessage dropTextMessage();

    WebSocketBinaryMessage continueWithBinaryMessage(ByteArray payload);

    WebSocketBinaryMessage dropBinaryMessage();

    ProxyWebSocketInitialInterceptBinaryMessage interceptInitialProxyBinaryMessage(ByteArray payload);

    ProxyWebSocketInitialInterceptTextMessage interceptInitialProxyTextMessage(String payload);

    ProxyWebSocketInitialInterceptBinaryMessage dropInitialProxyBinaryMessage();

    ProxyWebSocketInitialInterceptTextMessage dropInitialProxyTextMessage();

    ProxyWebSocketInitialInterceptBinaryMessage doNotInterceptInitialProxyBinaryMessage(ByteArray payload);

    ProxyWebSocketInitialInterceptTextMessage doNotInterceptInitialProxyTextMessage(String payload);

    ProxyWebSocketFinalInterceptBinaryMessage continueWithFinalProxyBinaryMessage(ByteArray payload);

    ProxyWebSocketFinalInterceptTextMessage continueWithFinalProxyTextMessage(String payload);

    ProxyWebSocketFinalInterceptBinaryMessage dropFinalProxyBinaryMessage();

    ProxyWebSocketFinalInterceptTextMessage dropFinalProxyTextMessage();

    PersistedObject persistedObject();

    PersistedList<Boolean> persistedBooleanList();

    PersistedList<Short> persistedShortList();

    PersistedList<Integer> persistedIntegerList();

    PersistedList<Long> persistedLongList();

    PersistedList<String> persistedStringList();

    PersistedList<ByteArray> persistedByteArrayList();

    PersistedList<HttpRequest> persistedHttpRequestList();

    PersistedList<HttpResponse> persistedHttpResponseList();

    PersistedList<HttpRequestResponse> persistedHttpRequestResponseList();
}
