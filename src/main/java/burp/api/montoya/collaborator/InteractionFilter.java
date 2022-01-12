/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.collaborator;

/**
 * This interface provides a filtering mechanism for use when retrieving
 * interactions from the Burp Collaborator server.
 * <p></p>
 * Helper methods are provided to create filters based on the interaction id
 * and the payload.
 */
public interface InteractionFilter
{
    /**
     * This method will be called for each interaction retrieved from the
     * Collaborator server and determines whether the interaction should be
     * included in the list of interactions returned.
     *
     * @param server The collaborator server that received the interaction.
     * @param interaction The interaction details.
     * @return {@code true} if the interaction should be included,
     * {@code false} if not.
     */
    boolean matches(CollaboratorServer server, Interaction interaction);

    /**
     * This method constructs an InteractionFilter which matches any
     * interaction with the specified interaction id.
     *
     * @param id The interaction id.
     * @return {@code true} if the interaction has the specified id,
     * {@code false} if not.
     */
    static InteractionFilter interactionIdFilter(String id)
    {
        return (server, interaction) -> interaction.id().toString().equals(id);
    }

    /**
     * This method constructs an InteractionFilter which matches any
     * interaction with the specified payload.
     *
     * @param payload The payload.
     * @return {@code true} if the interaction has the specified payload,
     * {@code false} if not.
     */
    static InteractionFilter interactionPayloadFilter(String payload)
    {
        if (payload == null)
        {
            return (server, interaction) -> false;
        }

        return (server, interaction) -> {
            String format = server.isLiteralAddress() ? "%s/" : ".%s";
            String interactionId = payload.replace(String.format(format, server.address()), "");
            return interaction.id().toString().equals(interactionId);
        };
    }
}
