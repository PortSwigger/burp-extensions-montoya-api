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

public interface SiteMapRequestResponse extends HttpRequestResponse
{
    /**
     * This method used to update annotations to the {@code SiteMapRequestResponse} instance.
     *
     * @param annotations new annotations for the request/response.
     */
    void setMessageAnnotations(Annotations annotations);
}
