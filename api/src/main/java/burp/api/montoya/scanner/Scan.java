/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner;

import burp.api.montoya.core.Range;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.scanner.audit.Audit;

import java.util.List;

/**
 * This interface represents an instance of a scan that can be configured
 * before starting a crawl, audit or crawl and audit in the Burp Scanner tool.
 */
public interface Scan
{
    /**
     * This method can be used to add a URL to this scan.
     *
     * @param url The URL to add to this scan.
     */
    void addUrl(String url);

    /**
     * This method can be used to add an HTTP request to this scan.
     *
     * @param request The {@link HttpRequest} to add to this scan.
     */
    default void addRequest(HttpRequest request)
    {
        addRequest(request, null);
    }

    /**
     * This method can be used to add an HTTP request to this scan.
     *
     * @param request               The {@link HttpRequest} to add to this scan.
     * @param insertionPointOffsets The list of {@link Range}s representing the
     *                              insertion point offsets.
     */
    void addRequest(HttpRequest request, List<Range> insertionPointOffsets);

    /**
     * This method can be used to add an HTTP request and response to this
     * scan.
     *
     * @param requestResponse The {@link HttpRequestResponse} to add to this
     *                        scan.
     */
    void addRequestResponse(HttpRequestResponse requestResponse);

    /**
     * This method can be used to add a built-in configuration to this scan.
     *
     * @param configuration The {@link BuiltInScanConfiguration} to add to this
     *                      scan.
     */
    void addConfiguration(BuiltInScanConfiguration configuration);

    /**
     * This method can be used to start a crawl in the Burp Scanner tool.
     *
     * @return The {@link Crawl} started in the Burp Scanner tool.
     * @throws InvalidLauncherConfigurationException if the configuration for
     *                                               this scan is invalid.
     */
    Crawl startCrawl() throws InvalidLauncherConfigurationException;

    /**
     * This method can be used to start an audit in the Burp Scanner tool.
     *
     * @return The {@link Audit} started in the Burp Scanner tool.
     * @throws InvalidLauncherConfigurationException if the configuration for
     *                                               this scan is invalid.
     */
    Audit startAudit() throws InvalidLauncherConfigurationException;
}
