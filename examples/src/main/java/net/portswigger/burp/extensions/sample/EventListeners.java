/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package net.portswigger.burp.extensions.sample;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.ToolSource;
import burp.api.montoya.extension.Extension;
import burp.api.montoya.extension.ExtensionUnloadingHandler;
import burp.api.montoya.http.Http;
import burp.api.montoya.http.HttpHandler;
import burp.api.montoya.http.RequestResult;
import burp.api.montoya.http.ResponseResult;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.proxy.InterceptedHttpRequest;
import burp.api.montoya.proxy.InterceptedHttpResponse;
import burp.api.montoya.proxy.Proxy;
import burp.api.montoya.proxy.ProxyHttpRequestHandler;
import burp.api.montoya.proxy.ProxyHttpResponseHandler;
import burp.api.montoya.proxy.RequestFinalInterceptResult;
import burp.api.montoya.proxy.RequestInitialInterceptResult;
import burp.api.montoya.proxy.ResponseFinalInterceptResult;
import burp.api.montoya.proxy.ResponseInitialInterceptResult;
import burp.api.montoya.scanner.audit.AuditIssueHandler;
import burp.api.montoya.scanner.audit.issues.AuditIssue;

import java.io.PrintStream;

//Burp will auto-detect and load any class that extends BurpExtension.
public class EventListeners implements BurpExtension
{
    private PrintStream out;
    private PrintStream err;

    @Override
    public void initialize(MontoyaApi api)
    {
        Http http = api.http();
        Proxy proxy = api.proxy();
        Logging logging = api.logging();
        Extension extension = api.extension();

        // set our extension name
        api.extension().setName("Event listeners");

        // obtain our output and error streams
        out = logging.output();
        err = logging.error();

        // register ourselves as an HTTP handler
        http.registerHttpHandler(new MyHttpHandler());

        // register ourselves as a Proxy handler
        proxy.registerRequestHandler(new MyProxyHttpRequestHandler());
        proxy.registerResponseHandler(new MyProxyHttpResponseHandler());

        // register ourselves as an Audit Issue handler
        api.scanner().registerAuditIssueHandler(new MyAuditIssueListenerHandler());

        // register ourselves as an extension unload handler
        extension.registerUnloadingHandler(new MyExtensionUnloadHandler());
    }

    private class MyHttpHandler implements HttpHandler
    {
        @Override
        public RequestResult handleHttpRequest(HttpRequest request, Annotations annotations, ToolSource toolSource)
        {
            out.println("HTTP request to " + request.httpService().toString() + " [" + toolSource.toolType().toolName() + "]");

            return RequestResult.requestResult(request, annotations);
        }

        @Override
        public ResponseResult handleHttpResponse(HttpResponse response, HttpRequest initiatingRequest, Annotations annotations, ToolSource toolSource)
        {
            out.println("HTTP response from " + initiatingRequest.httpService().toString() + " [" + toolSource.toolType().toolName() + "]");

            return ResponseResult.responseResult(response, annotations);
        }
    }

    private class MyProxyHttpRequestHandler implements ProxyHttpRequestHandler
    {
        @Override
        public RequestInitialInterceptResult handleReceivedRequest(InterceptedHttpRequest interceptedRequest, Annotations annotations)
        {
            out.println("Initial intercepted proxy request to " + interceptedRequest.httpService().toString());

            return RequestInitialInterceptResult.followUserRules(interceptedRequest);
        }

        @Override
        public RequestFinalInterceptResult handleRequestToIssue(InterceptedHttpRequest interceptedRequest, Annotations annotations)
        {
            out.println("Final intercepted proxy request to " + interceptedRequest.httpService().toString());

            return RequestFinalInterceptResult.continueWith(interceptedRequest);
        }
    }

    private class MyProxyHttpResponseHandler implements ProxyHttpResponseHandler
    {
        @Override
        public ResponseInitialInterceptResult handleReceivedResponse(InterceptedHttpResponse interceptedResponse, HttpRequest request, Annotations annotations)
        {
            out.println("Initial intercepted proxy response from " + request.httpService().toString());

            return ResponseInitialInterceptResult.followUserRules(interceptedResponse);
        }

        @Override
        public ResponseFinalInterceptResult handleResponseToReturn(InterceptedHttpResponse interceptedResponse, HttpRequest request, Annotations annotations)
        {
            out.println("Final intercepted proxy response from " + request.httpService().toString());

            return ResponseFinalInterceptResult.continueWith(interceptedResponse);
        }
    }

    private class MyAuditIssueListenerHandler implements AuditIssueHandler
    {
        @Override
        public void handleNewAuditIssue(AuditIssue auditIssue)
        {
            out.println("New scan issue: " + auditIssue.name());
        }
    }

    private class MyExtensionUnloadHandler implements ExtensionUnloadingHandler
    {
        @Override
        public void extensionUnloaded()
        {
            out.println("Extension was unloaded.");
        }
    }
}