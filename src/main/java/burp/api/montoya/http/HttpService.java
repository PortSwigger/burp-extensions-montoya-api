/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

import java.net.MalformedURLException;
import java.net.URL;

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
     * This is a helper method used to create a new instance of {@code HttpService}.
     *
     * @param host The hostname or IP address for the service.
     * @param secure True is the secure connection is to be used.
     * @return A new {@code HttpService} instance.
     */
    static HttpService from(String host, boolean secure)
    {
        return from(host, secure ? 443 : 80, secure);
    }

    /**
     * This is a helper method used to create a new instance of {@code HttpService}.
     *
     * @param baseUrl The URL for the service.
     * @return A new {@code HttpService} instance.
     * @throws IllegalArgumentException If the provided URL is invalid.
     */
    static HttpService from(String baseUrl)
    {
        try
        {
            URL url = new URL(baseUrl);

            int port = url.getPort();
            String host = url.getHost();
            boolean secure = url.getProtocol().equalsIgnoreCase("https");

            return port == -1 ? from(host, secure) : from(host, port, secure);
        }
        catch (MalformedURLException e)
        {
            throw new IllegalArgumentException(baseUrl + " is invalid", e);
        }
    }

    /**
     * This is a helper method used to create a new instance of {@code HttpService}.
     *
     * @param host The hostname or IP address for the service.
     * @param port The port number for the service.
     * @param secure True is the secure connection is to be used.
     * @return A new {@code HttpService} instance.
     */
    static HttpService from(String host, int port, boolean secure)
    {
        return new HttpService()
        {
            @Override
            public String host()
            {
                return host;
            }

            @Override
            public int port()
            {
                return port;
            }

            @Override
            public boolean secure()
            {
                return secure;
            }
        };
    }
}
