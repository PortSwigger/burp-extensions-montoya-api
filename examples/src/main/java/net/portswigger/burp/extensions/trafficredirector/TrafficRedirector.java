/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package net.portswigger.burp.extensions.trafficredirector;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.ToolSource;
import burp.api.montoya.http.HttpHandler;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.OutgoingRequest;
import burp.api.montoya.http.RequestResult;
import burp.api.montoya.http.ResponseResult;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import static burp.api.montoya.http.HttpService.httpService;
import static burp.api.montoya.http.RequestResult.requestResult;
import static burp.api.montoya.http.ResponseResult.responseResult;

//Burp will auto-detect and load any class that extends BurpExtension.
public class TrafficRedirector implements BurpExtension
{
    private static final String HOST_FROM = "host1.example.org";
    private static final String HOST_TO = "host2.example.org";

    @Override
    public void initialize(MontoyaApi api)
    {
        // set extension name
        api.extension().setName("Traffic redirector");

        // register a new HTTP handler
        api.http().registerHttpHandler(new MyHttpHandler());
    }

    private static class MyHttpHandler implements HttpHandler
    {
        @Override
        public RequestResult handleHttpRequest(OutgoingRequest outgoingRequest)
        {
            HttpService service = outgoingRequest.request().httpService();

            if (HOST_FROM.equalsIgnoreCase(service.host()))
            {
                HttpRequest newRequest = outgoingRequest.request().withService(httpService(HOST_TO, service.port(), service.secure()));

                return requestResult(newRequest, outgoingRequest.annotations());
            }

            return requestResult(outgoingRequest.request(), outgoingRequest.annotations());
        }

        @Override
        public ResponseResult handleHttpResponse(HttpResponse response, HttpRequest initiatingRequest, Annotations annotations, ToolSource toolSource)
        {
            return responseResult(response, annotations);
        }
    }
}
