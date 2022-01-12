/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.comparer;

/**
 * This interface provides access to the functionality of the Comparer tool.
 */
public interface Comparer
{
    /**
     * This method can be used to send data to the Comparer tool.
     *
     * @param data The data to be sent to Comparer.
     */
    void sendToComparer(byte[]... data);
}
