/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.collaborator;

import java.util.Optional;

/**
 * This interface represents a Burp Collaborator payload.
 */
public interface CollaboratorPayload
{
    /**
     * Returns the payload's interaction id.
     *
     * @return The interaction id of the payload.
     */
    InteractionId id();

    /**
     * Return custom data from the payload.
     *
     * @return The payload's custom data.
     */
    Optional<String> customData();

    /**
     * Returns an optional instance of CollaboratorServer describing the
     * server location for this payload. If the payload was generated without
     * the server location this method will return an empty Optional.
     *
     * @return Details of the collaborator server referenced in the payload
     * or empty if the payload was generated without the server location.
     */
    Optional<CollaboratorServer> server();

    /**
     * Returns a string containing the payload.
     *
     * @return The payload string.
     */
    @Override
    String toString();
}
