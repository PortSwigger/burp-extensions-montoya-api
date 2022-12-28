package net.portswigger.burp.extensions.httphandler;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.HighlightColor;
import burp.api.montoya.http.HttpHandler;
import burp.api.montoya.http.IncomingHttpResponse;
import burp.api.montoya.http.OutgoingHttpRequest;
import burp.api.montoya.http.RequestResult;
import burp.api.montoya.http.ResponseResult;
import burp.api.montoya.http.message.requests.HttpRequest;
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
    public RequestResult handleHttpRequest(OutgoingHttpRequest outgoingRequest)
    {
        Annotations annotations = outgoingRequest.annotations();
        // If the request is a post, log the body and add a comment annotation.
        if (outgoingRequest.method().equalsIgnoreCase("POST"))
        {
            annotations = outgoingRequest.annotations().withComment("Request was a post");
            logging.logToOutput(outgoingRequest.bodyToString());
        }

        //Modify the request by adding a url param.
        HttpRequest modifiedRequest = outgoingRequest.withAddedParameters(urlParameter("foo", "bar"));

        //Return the modified request to burp with updated annotations.
        return requestResult(modifiedRequest, annotations);
    }

    @Override
    public ResponseResult handleHttpResponse(IncomingHttpResponse incomingResponse)
    {
        Annotations annotations = incomingResponse.annotations();
        //Highlight all responses where the request had a Content-Length header.
        if (incomingResponse.initiatingRequest().headers().stream().anyMatch(header -> header.name().equalsIgnoreCase("Content-Length")))
        {
            annotations = annotations.withHighlightColor(HighlightColor.BLUE);
        }

        return responseResult(incomingResponse, annotations);
    }
}
