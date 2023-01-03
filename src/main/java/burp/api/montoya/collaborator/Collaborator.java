/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.collaborator;

/**
 * Provides access to the facilities of Burp Collaborator.
 */
public interface Collaborator
{
    /**
     * Create a new Burp Collaborator client
     * that can be used to generate Burp Collaborator payloads and poll the
     * Collaborator server for any network interactions that result from using
     * those payloads.
     *
     * @return A new instance of {@link CollaboratorClient} that can be used to
     * generate Collaborator payloads and retrieve interactions.
     */
    CollaboratorClient createClient();

    /**
     * Restore a {@link CollaboratorClient} from a previous
     * session. This allows you to retrieve the interactions that were identified
     * from a specific payloads.
     *
     * @param secretKey The key to restore the {@link CollaboratorClient} from the previous session.
     *
     * @return A new instance of {@link CollaboratorClient} that can be used to
     * generate Collaborator payloads and retrieve interactions.
     */
    CollaboratorClient restoreClient(SecretKey secretKey);
}
