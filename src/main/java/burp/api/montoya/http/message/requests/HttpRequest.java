/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.requests;

import burp.api.montoya.http.ContentType;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.HttpTransformation;
import burp.api.montoya.http.message.HttpMessage;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.ParsedHttpParameter;

import java.util.List;

/**
 * This interface is used to retrieve key details about an HTTP request.
 */
public interface HttpRequest extends HttpMessage
{
    /**
     * This method is used to retrieve the HTTP service for the request.
     *
     * @return An {@link HttpService} object containing details of the HTTP service.
     */
    HttpService httpService();

    /**
     * @return The HTTP method used in the request.
     */
    String method();

    /**
     * @return The URL in the request.
     */
    String url();

    /**
     * @return The content type of the message body.
     */
    ContentType contentType();

    /**
     * @return The parameters contained in the request.
     */
    List<ParsedHttpParameter> parameters();

    /**
     * This is a helper method that adds an HTTP service reference to a request.
     *
     * @param service An {@link HttpService} reference to add.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withService(HttpService service);

    /**
     * This is a helper method that adds HTTP parameters to a request.
     *
     * @param parameters HTTP parameters to add.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withAddedParameters(List<HttpParameter> parameters);

    /**
     * This is a helper method that adds HTTP parameters to a request.
     *
     * @param parameters HTTP parameters to add.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withAddedParameters(HttpParameter... parameters);

    /**
     * This is a helper method that removes HTTP parameters from a request.
     *
     * @param parameters HTTP parameters to remove.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withRemovedParameters(List<HttpParameter> parameters);

    /**
     * This is a helper method that removes HTTP parameters from a request.
     *
     * @param parameters HTTP parameters to remove.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withRemovedParameters(HttpParameter... parameters);

    /**
     * This is a helper method that updates existing HTTP parameters in a request.
     * If a parameter does not exist in the request, a new one will be created.
     *
     * @param parameters HTTP parameters to update.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withUpdatedParameters(List<HttpParameter> parameters);

    /**
     * This is a helper method that updates existing HTTP parameters in a request.
     * If a parameter does not exist in the request, a new one will be created.
     *
     * @param parameters HTTP parameters to update.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withUpdatedParameters(HttpParameter... parameters);

    /**
     * This is a helper method that applies a transformation to a request.
     *
     * @param transformation Transformation to apply.
     * @return A new {@code HttpRequest} instance.
     */
    HttpRequest withTransformationApplied(HttpTransformation transformation);
}
