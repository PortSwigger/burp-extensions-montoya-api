/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.collaborator;

import java.net.InetAddress;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * This interface provides details of an interaction with the Burp Collaborator
 * server.
 */
public interface Interaction
{
    /**
     * Returns the id.
     *
     * @return The interaction id.
     */
    InteractionId id();

    /**
     * Returns the type.
     *
     * @return The type of interaction.
     */
    InteractionType type();

    /**
     * Returns the timestamp.
     *
     * @return The timestamp of the interaction.
     */
    ZonedDateTime timeStamp();

    /**
     * Returns the client IP address.
     *
     * @return The IP address of the client performing the interaction.
     */
    InetAddress clientIp();

    /**
     * Returns the DNS interaction details.
     *
     * @return Details of the DNS interaction or empty if the interaction was
     * not DNS.
     */
    Optional<DnsDetails> dnsDetails();

    /**
     * Returns the HTTP interaction details.
     *
     * @return Details of the HTTP interaction or empty if the interaction was
     * not HTTP.
     */
    Optional<HttpDetails> httpDetails();

    /**
     * Returns the SMTP interaction details.
     *
     * @return Details of the SMTP interaction or empty if the interaction was
     * not SMTP.
     */
    Optional<SmtpDetails> smtpDetails();

    /**
     * Return custom data from the payload.
     *
     * @return The custom data.
     */
    Optional<String> customData();
}
