/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package net.portswigger.burp.extender.proxy;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.HighlightColor;
import burp.api.montoya.http.ContentType;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.proxy.InterceptedHttpRequest;
import burp.api.montoya.proxy.InterceptedHttpResponse;
import burp.api.montoya.proxy.ProxyHttpRequestHandler;
import burp.api.montoya.proxy.ProxyHttpResponseHandler;
import burp.api.montoya.proxy.RequestFinalInterceptResult;
import burp.api.montoya.proxy.RequestInitialInterceptResult;
import burp.api.montoya.proxy.ResponseFinalInterceptResult;
import burp.api.montoya.proxy.ResponseInitialInterceptResult;

//Burp will auto-detect and load any class that extends BurpExtension.
public class ProxyHandlerExample implements BurpExtension
{
    @Override
    public void initialize(MontoyaApi api)
    {
        //Register our http handlers with Burp.
        api.proxy().registerRequestHandler(new MyProxyHttpRequestHandler());
        api.proxy().registerResponseHandler(new MyProxyHttpResponseHandler());
    }

    private static class MyProxyHttpRequestHandler implements ProxyHttpRequestHandler
    {
        @Override
        public RequestInitialInterceptResult handleReceivedRequest(InterceptedHttpRequest interceptedRequest, Annotations annotations)
        {
            //Drop all post requests
            if (interceptedRequest.method().equals("POST"))
            {
                return RequestInitialInterceptResult.drop();
            }

            //Do not intercept any request with foo in the url
            if (interceptedRequest.url().contains("foo"))
            {
                return RequestInitialInterceptResult.doNotIntercept(interceptedRequest);
            }

            //If the content type is json, highlight the request and follow burp rules for interception
            if (ContentType.JSON == interceptedRequest.contentType())
            {
                return RequestInitialInterceptResult.followUserRules(interceptedRequest, annotations.withHighlightColor(HighlightColor.RED));
            }

            //Intercept all other requests
            return RequestInitialInterceptResult.intercept(interceptedRequest);
        }

        @Override
        public RequestFinalInterceptResult handleRequestToIssue(InterceptedHttpRequest interceptedRequest, Annotations annotations)
        {
            //Do nothing with the user modified request, continue as normal.
            return RequestFinalInterceptResult.continueWith(interceptedRequest, annotations);
        }
    }

    private static class MyProxyHttpResponseHandler implements ProxyHttpResponseHandler
    {
        @Override
        public ResponseInitialInterceptResult handleReceivedResponse(InterceptedHttpResponse interceptedResponse, HttpRequest request, Annotations annotations)
        {
            //Highlight all responses that have username in them
            if (interceptedResponse.bodyAsString().contains("username"))
            {
                return ResponseInitialInterceptResult.followUserRules(interceptedResponse, annotations.withHighlightColor(HighlightColor.BLUE));
            }

            return ResponseInitialInterceptResult.followUserRules(interceptedResponse, annotations);
        }

        @Override
        public ResponseFinalInterceptResult handleResponseToReturn(InterceptedHttpResponse interceptedResponse, HttpRequest request, Annotations annotations)
        {
            return ResponseFinalInterceptResult.continueWith(interceptedResponse, annotations);
        }
    }
}
