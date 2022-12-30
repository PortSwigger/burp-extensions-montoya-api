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
import burp.api.montoya.core.Marker;
import burp.api.montoya.core.Range;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.handler.RequestToSendAction;
import burp.api.montoya.http.handler.ResponseReceivedAction;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.headers.HttpHeader;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.HttpParameterType;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.http.sessions.ActionResult;
import burp.api.montoya.intruder.GeneratedPayload;
import burp.api.montoya.intruder.HttpRequestTemplate;
import burp.api.montoya.intruder.PayloadProcessingAction;
import burp.api.montoya.intruder.PayloadProcessingResult;
import burp.api.montoya.persistence.PersistedList;
import burp.api.montoya.persistence.PersistedObject;
import burp.api.montoya.proxy.MessageReceivedAction;
import burp.api.montoya.proxy.MessageSendAction;
import burp.api.montoya.proxy.http.ProxyRequestReceivedAction;
import burp.api.montoya.proxy.http.ProxyRequestToSendAction;
import burp.api.montoya.proxy.http.ProxyResponseReceivedAction;
import burp.api.montoya.proxy.http.ProxyResponseToSendAction;
import burp.api.montoya.proxy.websocket.BinaryMessageReceivedAction;
import burp.api.montoya.proxy.websocket.BinaryMessageToSendAction;
import burp.api.montoya.proxy.websocket.TextMessageReceivedAction;
import burp.api.montoya.proxy.websocket.TextMessageToSendAction;
import burp.api.montoya.scanner.AuditConfiguration;
import burp.api.montoya.scanner.AuditResult;
import burp.api.montoya.scanner.BuiltInAuditConfiguration;
import burp.api.montoya.scanner.CrawlConfiguration;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.scanner.audit.issues.AuditIssueConfidence;
import burp.api.montoya.scanner.audit.issues.AuditIssueDefinition;
import burp.api.montoya.scanner.audit.issues.AuditIssueSeverity;
import burp.api.montoya.sitemap.SiteMapFilter;
import burp.api.montoya.ui.Selection;
import burp.api.montoya.websocket.BinaryMessageAction;
import burp.api.montoya.websocket.MessageAction;
import burp.api.montoya.websocket.TextMessageAction;

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

    HttpResponse httpResponse();

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

    AuditIssue auditIssue(
            String name,
            String detail,
            String remediation,
            String baseUrl,
            AuditIssueSeverity severity,
            AuditIssueConfidence confidence,
            String background,
            String remediationBackground,
            AuditIssueSeverity typicalSeverity,
            List<HttpRequestResponse> requestResponses);

    Selection selection(ByteArray selectionContents);

    Selection selection(int startIndexInclusive, int endIndexExclusive);

    Selection selection(ByteArray selectionContents, int startIndexInclusive, int endIndexExclusive);

    SecretKey secretKey(String encodedKey);

    ProxyRequestReceivedAction proxyRequestReceivedAction(HttpRequest request, Annotations annotations, MessageReceivedAction action);

    ProxyRequestToSendAction proxyRequestToSendAction(HttpRequest request, Annotations annotations, MessageSendAction action);

    ProxyResponseToSendAction proxyResponseToReturnAction(HttpResponse response, Annotations annotations, MessageSendAction action);

    ProxyResponseReceivedAction proxyResponseReceivedAction(HttpResponse response, Annotations annotations, MessageReceivedAction action);

    RequestToSendAction requestResult(HttpRequest request, Annotations annotations);

    ResponseReceivedAction responseResult(HttpResponse response, Annotations annotations);

    HttpRequestTemplate httpRequestTemplate(ByteArray content, List<Range> insertionPointOffsets);

    HttpRequestTemplate httpRequestTemplate(HttpRequest request, List<Range> insertionPointOffsets);

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

    TextMessageAction continueWithTextMessage(String payload);

    TextMessageAction dropTextMessage();

    TextMessageAction textMessageAction(String payload, MessageAction action);

    BinaryMessageAction continueWithBinaryMessage(ByteArray payload);

    BinaryMessageAction dropBinaryMessage();

    BinaryMessageAction binaryMessageAction(ByteArray payload, MessageAction action);

    BinaryMessageReceivedAction followUserRulesInitialProxyBinaryMessage(ByteArray payload);

    TextMessageReceivedAction followUserRulesInitialProxyTextMessage(String payload);

    BinaryMessageReceivedAction interceptInitialProxyBinaryMessage(ByteArray payload);

    TextMessageReceivedAction interceptInitialProxyTextMessage(String payload);

    BinaryMessageReceivedAction dropInitialProxyBinaryMessage();

    TextMessageReceivedAction dropInitialProxyTextMessage();

    BinaryMessageReceivedAction doNotInterceptInitialProxyBinaryMessage(ByteArray payload);

    TextMessageReceivedAction doNotInterceptInitialProxyTextMessage(String payload);

    BinaryMessageToSendAction continueWithFinalProxyBinaryMessage(ByteArray payload);

    TextMessageToSendAction continueWithFinalProxyTextMessage(String payload);

    BinaryMessageToSendAction dropFinalProxyBinaryMessage();

    TextMessageToSendAction dropFinalProxyTextMessage();

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

    AuditResult auditResult(List<AuditIssue> auditIssues);

    AuditResult auditResult(AuditIssue... auditIssues);

    AuditConfiguration auditConfiguration(BuiltInAuditConfiguration builtInAuditConfiguration);

    CrawlConfiguration crawlConfiguration(String... seedUrls);

    HttpParameter urlParameter(String name, String value);

    HttpParameter bodyParameter(String name, String value);

    HttpParameter cookieParameter(String name, String value);

    GeneratedPayload payload(String payload);

    GeneratedPayload payload(ByteArray payload);

    GeneratedPayload payloadEnd();

    PayloadProcessingResult usePayload(ByteArray processedPayload);

    PayloadProcessingResult skipPayload();

    ProxyRequestToSendAction requestFinalInterceptResultContinueWith(HttpRequest request);

    ProxyRequestToSendAction requestFinalInterceptResultContinueWith(HttpRequest request, Annotations annotations);

    ProxyRequestToSendAction requestFinalInterceptResultDrop();

    ProxyResponseToSendAction responseFinalInterceptResultDrop();

    ProxyResponseToSendAction responseFinalInterceptResultContinueWith(HttpResponse response, Annotations annotations);

    ProxyResponseToSendAction responseFinalInterceptResultContinueWith(HttpResponse response);

    ProxyResponseReceivedAction responseInitialInterceptResultIntercept(HttpResponse response);

    ProxyResponseReceivedAction responseInitialInterceptResultIntercept(HttpResponse response, Annotations annotations);

    ProxyResponseReceivedAction responseInitialInterceptResultDoNotIntercept(HttpResponse response);

    ProxyResponseReceivedAction responseInitialInterceptResultDoNotIntercept(HttpResponse response, Annotations annotations);

    ProxyResponseReceivedAction responseInitialInterceptResultFollowUserRules(HttpResponse response);

    ProxyResponseReceivedAction responseInitialInterceptResultFollowUserRules(HttpResponse response, Annotations annotations);

    ProxyResponseReceivedAction responseInitialInterceptResultDrop();

    ProxyRequestReceivedAction requestInitialInterceptResultIntercept(HttpRequest request);

    ProxyRequestReceivedAction requestInitialInterceptResultIntercept(HttpRequest request, Annotations annotations);

    ProxyRequestReceivedAction requestInitialInterceptResultDoNotIntercept(HttpRequest request);

    ProxyRequestReceivedAction requestInitialInterceptResultDoNotIntercept(HttpRequest request, Annotations annotations);

    ProxyRequestReceivedAction requestInitialInterceptResultFollowUserRules(HttpRequest request);

    ProxyRequestReceivedAction requestInitialInterceptResultFollowUserRules(HttpRequest request, Annotations annotations);

    ProxyRequestReceivedAction requestInitialInterceptResultDrop();

    ResponseReceivedAction responseResult(HttpResponse response);

    RequestToSendAction requestResult(HttpRequest request);

    HighlightColor highlightColor(String color);

    ActionResult actionResult(HttpRequest request);

    ActionResult actionResult(HttpRequest request, Annotations annotations);
}
