/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface is used to provide details about an HTTP service, to which HTTP requests can be sent.
 */
public interface HttpService
{

    /**
     * Create a new instance of {@code HttpService}.
     *
     * @param baseUrl The URL for the service.
     * @return A new {@code HttpService} instance.
     * @throws IllegalArgumentException If the provided URL is invalid.
     */
    static HttpService httpService(String baseUrl)
    {
        return FACTORY.httpService(baseUrl);
    }

    /**
     * Create a new instance of {@code HttpService}.
     *
     * @param host   The hostname or IP address for the service.
     * @param secure True is the secure connection is to be used.
     * @return A new {@code HttpService} instance.
     */
    static HttpService httpService(String host, boolean secure)
    {
        return FACTORY.httpService(host, secure);
    }

    /**
     * Create a new instance of {@code HttpService}.
     *
     * @param host   The hostname or IP address for the service.
     * @param port   The port number for the service.
     * @param secure True is the secure connection is to be used.
     * @return A new {@code HttpService} instance.
     */
    static HttpService httpService(String host, int port, boolean secure)
    {
        return FACTORY.httpService(host, port, secure);
    }

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
