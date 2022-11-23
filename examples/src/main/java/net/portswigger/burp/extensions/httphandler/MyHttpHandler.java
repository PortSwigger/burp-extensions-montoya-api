package net.portswigger.burp.extensions.httphandler;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.HighlightColor;
import burp.api.montoya.core.ToolSource;
import burp.api.montoya.http.HttpHandler;
import burp.api.montoya.http.RequestResult;
import burp.api.montoya.http.ResponseResult;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.logging.Logging;

import static burp.api.montoya.http.RequestResult.requestResult;
import static burp.api.montoya.http.ResponseResult.responseResult;
import static burp.api.montoya.http.message.params.HttpParameter.urlParameter;

class MyHttpHandler implements HttpHandler
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
            logging.logToOutput(request.bodyToString());
        }

        //Modify the request by adding a url param.
        HttpRequest modifiedRequest = request.withAddedParameters(urlParameter("foo", "bar"));

        //Return the modified request to burp with updated annotations.
        return requestResult(modifiedRequest, annotations);
    }

    @Override
    public ResponseResult handleHttpResponse(HttpResponse response, HttpRequest request, Annotations annotations, ToolSource toolSource)
    {
        //Highlight all responses where the request had a Content-Length header.
        if (request.headers().stream().anyMatch(header -> header.name().equalsIgnoreCase("Content-Length")))
        {
            annotations = annotations.withHighlightColor(HighlightColor.BLUE);
        }

        return responseResult(response, annotations);
    }
}
