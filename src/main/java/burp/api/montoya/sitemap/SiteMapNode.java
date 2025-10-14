/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.sitemap;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.scanner.audit.issues.AuditIssue;

import java.util.List;

/**
 * This interface is used to represent items in the Burp's site map.
 */
public interface SiteMapNode
{
    /**
     * Retrieve the URL associated with the site map's node.
     *
     * @return The URL of the node.
     */
    String url();

    /**
     * Retrieve the {@link HttpRequestResponse} associated with the site map's node.
     *
     * @return The {@link HttpRequestResponse} for the node.
     */
    HttpRequestResponse requestResponse();

    /**
     * Retrieve the list of {@link AuditIssue} associated with the site map's node.
     *
     * @return A List containing any audit issues for the node.
     */
    List<AuditIssue> issues();
}
