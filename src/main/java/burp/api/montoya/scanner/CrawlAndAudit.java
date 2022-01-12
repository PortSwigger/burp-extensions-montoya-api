/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner;

import burp.api.montoya.scanner.audit.Audit;

/**
 * This interface represents an instance of a crawl and audit in the Burp
 * Scanner tool.
 */
public interface CrawlAndAudit extends Crawl, Audit
{
}
