/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.collaborator;


/**
 * This interface represents a Burp Collaborator interaction id.
 */
public interface InteractionId
{
    /**
     * Returns a string containing the interaction id.
     *
     * @return The interaction id string.
     */
    @Override
    String toString();
}
