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
import burp.api.montoya.http.HttpHandler;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.RequestResult;
import burp.api.montoya.http.ResponseResult;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

//Burp will auto-detect and load any class that extends BurpExtension.
public class TrafficRedirector implements BurpExtension
{
    private static final String HOST_FROM = "host1.example.org";
    private static final String HOST_TO = "host2.example.org";

    @Override
    public void initialize(MontoyaApi api)
    {
        // set our extension name
        api.extension().setName("Traffic redirector");

        // register ourselves as an HTTP handler
        api.http().registerHttpHandler(new MyHttpHandler());
    }

    private class MyHttpHandler implements HttpHandler
    {
        @Override
        public RequestResult handleHttpRequest(HttpRequest request, Annotations annotations, ToolSource toolSource)
        {
            HttpService service = request.httpService();

            if (HOST_FROM.equalsIgnoreCase(service.host()))
            {
                HttpRequest newRequest = request.withService(new HttpService()
                {
                    @Override
                    public String host()
                    {
                        return HOST_TO;
                    }

                    @Override
                    public int port()
                    {
                        return service.port();
                    }

                    @Override
                    public boolean secure()
                    {
                        return service.secure();
                    }
                });

                return RequestResult.requestResult(newRequest, annotations);
            }

            return RequestResult.requestResult(request, annotations);
        }

        @Override
        public ResponseResult handleHttpResponse(HttpResponse response, HttpRequest initiatingRequest, Annotations annotations, ToolSource toolSource)
        {
            return ResponseResult.responseResult(response, annotations);
        }
    }
}
