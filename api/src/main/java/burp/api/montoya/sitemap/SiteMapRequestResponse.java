/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.sitemap;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.http.message.HttpRequestResponse;

public interface SiteMapRequestResponse
{
    /**
     * This method retrieves the annotations for the request/response pair.
     *
     * @return The {@link Annotations} for the request/response pair.
     */
    Annotations getAnnotations();

    /**
     * This method used to update annotations to the {@code SiteMapRequestResponse} instance.
     *
     * @param annotations new annotations for the request/response.
     */
    void setAnnotations(Annotations annotations);

    /**
     * This method retrieves the request/response pair.
     *
     * @return The underlying {@link HttpRequestResponse}.
     */
    HttpRequestResponse requestResponse();
}
