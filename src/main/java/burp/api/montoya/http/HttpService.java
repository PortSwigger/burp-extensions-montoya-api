/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

/**
 * This interface is used to provide details about an HTTP service, to which HTTP requests can be sent.
 */
public interface HttpService
{
    /**
     * @return The hostname or IP address for the service.
     */
    String host();

    /**
     * @return The port number for the service.
     */
    int port();

    /**
     * @return True is a secure protocol is used for the connection, false otherwise.
     */
    boolean secure();

    /**
     * @return The {@code String} representation of the service.
     */
    @Override
    String toString();
}
