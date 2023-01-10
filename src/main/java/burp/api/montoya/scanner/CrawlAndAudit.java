/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner;

/**
 * Crawl and audit in the Burp Scanner tool.
 */
public interface CrawlAndAudit extends ScanTask
{
    /**
     * {@inheritDoc}
     */
    @Override
    void delete();

    /**
     * {@inheritDoc}
     */
    @Override
    String statusMessage();

    /**
     * {@inheritDoc}
     */
    @Override
    int requestCount();

    /**
     * {@inheritDoc}
     */
    @Override
    int errorCount();
}
