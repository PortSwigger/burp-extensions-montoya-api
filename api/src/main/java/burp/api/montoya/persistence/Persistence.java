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
     * This method is used to access the functionality related to
     * storing of data in the Burp project. When Burp is started without
     * a project file, the data will be stored in memory.
     *
     * @return An implementation of the {@link PersistedObject} interface
     * which stores data in the project file or memory.
     */
    PersistedObject extensionData();

    /**
     * This method is used to access the functionality related to java preference store
     * in a way that survives reloads of the extension and of Burp Suite.
     *
     * @return An implementation of the {@link Preferences} interface
     * which stores data in a persistent way.
     */
    Preferences preferences();

    /**
     * This method is used to access the functionality related to temporary
     * files.
     *
     * @return An implementation of the {@link TemporaryFile} interface
     * which exposes temporary file based functionality.
     */
    TemporaryFile temporaryFile();
}
