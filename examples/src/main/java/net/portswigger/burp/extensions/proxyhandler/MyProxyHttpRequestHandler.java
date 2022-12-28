package net.portswigger.burp.extensions.proxyhandler;

import burp.api.montoya.proxy.InterceptedHttpRequest;
import burp.api.montoya.proxy.ProxyHttpRequestHandler;
import burp.api.montoya.proxy.RequestFinalInterceptResult;
import burp.api.montoya.proxy.RequestInitialInterceptResult;

import static burp.api.montoya.core.HighlightColor.RED;
import static burp.api.montoya.http.ContentType.JSON;
import static burp.api.montoya.proxy.RequestFinalInterceptResult.continueWith;
import static burp.api.montoya.proxy.RequestInitialInterceptResult.doNotIntercept;
import static burp.api.montoya.proxy.RequestInitialInterceptResult.drop;
import static burp.api.montoya.proxy.RequestInitialInterceptResult.followUserRules;
import static burp.api.montoya.proxy.RequestInitialInterceptResult.intercept;

class MyProxyHttpRequestHandler implements ProxyHttpRequestHandler
{
    @Override
    public RequestInitialInterceptResult handleReceivedRequest(InterceptedHttpRequest interceptedRequest)
    {
        //Drop all post requests
        if (interceptedRequest.method().equals("POST"))
        {
            return drop();
        }

        //Do not intercept any request with foo in the url
        if (interceptedRequest.url().contains("foo"))
        {
            return doNotIntercept(interceptedRequest);
        }

        //If the content type is json, highlight the request and follow burp rules for interception
        if (interceptedRequest.contentType() == JSON)
        {
            return followUserRules(interceptedRequest, interceptedRequest.annotations().withHighlightColor(RED));
        }

        //Intercept all other requests
        return intercept(interceptedRequest);
    }

    @Override
    public RequestFinalInterceptResult handleRequestToIssue(InterceptedHttpRequest interceptedRequest)
    {
        //Do nothing with the user modified request, continue as normal.
        return continueWith(interceptedRequest);
    }
}
