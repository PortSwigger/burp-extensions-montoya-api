/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.collaborator;

/**
 * This interface provides information about an SMTP interaction detected by
 * Burp Collaborator.
 */
public interface SmtpDetails
{
    /**
     * Returns the SMTP protocol.
     *
     * @return The protocol used by the interaction.
     */
    SmtpProtocol protocol();

    /**
     * Returns the SMTP conversation.
     *
     * @return The SMTP conversation between the client and the Collaborator
     * server.
     */
    String conversation();
}
