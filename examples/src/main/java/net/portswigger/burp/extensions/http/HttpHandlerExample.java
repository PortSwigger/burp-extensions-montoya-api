/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package net.portswigger.burp.extensions.http;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.HighlightColor;
import burp.api.montoya.core.ToolSource;
import burp.api.montoya.http.HttpHandler;
import burp.api.montoya.http.RequestResult;
import burp.api.montoya.http.ResponseResult;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.logging.Logging;


//Burp will auto-detect and load any class that extends BurpExtension.
public class HttpHandlerExample implements BurpExtension
{
    @Override
    public void initialize(MontoyaApi api)
    {
        //Register our http handler with Burp.
        api.http().registerHttpHandler(new MyHttpHandler(api));
    }

    private static class MyHttpHandler implements HttpHandler
    {
        private final Logging logging;

        public MyHttpHandler(MontoyaApi api)
        {
            this.logging = api.logging();
        }

        @Override
        public RequestResult handleHttpRequest(HttpRequest request, Annotations annotations, ToolSource toolSource)
        {
            // If the request is a post, log the body and add a comment annotation.
            if (request.method().equalsIgnoreCase("POST"))
            {
                annotations = annotations.withComment("Request was a post");
                logging.logToOutput(request.bodyAsString());
            }

            //Modify the request by adding a url param.
            HttpRequest modifiedRequest = request.withAddedParameters(HttpParameter.urlParameter("foo", "bar"));

            //Return the modified request to burp with updated annotations.
            return RequestResult.requestResult(modifiedRequest, annotations);
        }

        @Override
        public ResponseResult handleHttpResponse(HttpResponse response, HttpRequest request, Annotations annotations, ToolSource toolSource)
        {
            //Highlight all responses where the request had a Content-Length header.
            if (request.headers().stream().anyMatch(header -> header.name().equalsIgnoreCase("Content-Length")))
            {
                annotations = annotations.withHighlightColor(HighlightColor.BLUE);
            }
            return ResponseResult.responseResult(response, annotations);
        }
    }
}
