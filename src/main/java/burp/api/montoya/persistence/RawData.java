/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.persistence;

/**
 * This interface represents a class that provides access to persisted data.
 */
public interface RawData
{
    /**
     * This method is used to access a copy of the persisted data.
     *
     * @return A copy of the persisted data.
     */
    byte[] copyOfData();
}
