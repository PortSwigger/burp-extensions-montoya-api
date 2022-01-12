/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence;

/**
 * This interface provides access to the functionality related to persistence.
 */
public interface Persistence
{
    /**
     * This method is used to access the functionality related to persistence
     * in a way that survives reloads of the extension and of Burp Suite.
     *
     * @return An implementation of the {@link PersistenceContext} interface
     * which stores data in a persistent way.
     */
    PersistenceContext userContext();

    /**
     * This method is used to access the functionality related to persistence
     * in a way that holds the values in memory but doesn't survive reloads
     * of the extension or of Burp Suite.
     *
     * @return An implementation of the {@link PersistenceContext} interface
     * which stores data in memory.
     */
    PersistenceContext sessionContext();

    /**
     * This method is used to access the functionality related to temporary
     * files.
     *
     * @return An implementation of the {@link TemporaryFileContext} interface
     * which exposes temporary file based functionality.
     */
    TemporaryFileContext temporaryFileContext();
}
