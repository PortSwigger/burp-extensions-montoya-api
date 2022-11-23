package net.portswigger.burp.extensions.proxyhandler;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.proxy.InterceptedHttpResponse;
import burp.api.montoya.proxy.ProxyHttpResponseHandler;
import burp.api.montoya.proxy.ResponseFinalInterceptResult;
import burp.api.montoya.proxy.ResponseInitialInterceptResult;

import static burp.api.montoya.core.HighlightColor.BLUE;
import static burp.api.montoya.proxy.ResponseFinalInterceptResult.continueWith;
import static burp.api.montoya.proxy.ResponseInitialInterceptResult.followUserRules;

class MyProxyHttpResponseHandler implements ProxyHttpResponseHandler
{
    @Override
    public ResponseInitialInterceptResult handleReceivedResponse(InterceptedHttpResponse interceptedResponse, HttpRequest request, Annotations annotations)
    {
        //Highlight all responses that have username in them
        if (interceptedResponse.bodyToString().contains("username"))
        {
            return followUserRules(interceptedResponse, annotations.withHighlightColor(BLUE));
        }

        return followUserRules(interceptedResponse, annotations);
    }

    @Override
    public ResponseFinalInterceptResult handleResponseToReturn(InterceptedHttpResponse interceptedResponse, HttpRequest request, Annotations annotations)
    {
        return continueWith(interceptedResponse, annotations);
    }
}
