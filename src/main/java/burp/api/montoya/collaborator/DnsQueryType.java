/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.collaborator;

/**
 * This enum represents Domain Name System (DNS) query types.
 */
public enum DnsQueryType
{
    A,
    NS,
    CNAME,
    SOA,
    PTR,
    HINFO,
    MX,
    TXT,
    AAAA,
    SRV,
    NAPTR,
    DS,
    DNSKEY,
    HTTPS,
    ALL,
    CAA,
    UNKNOWN
}
