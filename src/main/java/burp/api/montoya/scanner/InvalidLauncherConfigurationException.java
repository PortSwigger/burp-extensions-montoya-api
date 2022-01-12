/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.scanner;

/**
 * This exception is thrown if the configuration for a {@link Scan} is invalid.
 */
public class InvalidLauncherConfigurationException extends Exception
{
    public InvalidLauncherConfigurationException()
    {
        super();
    }

    public InvalidLauncherConfigurationException(String message)
    {
        super(message);
    }

    public InvalidLauncherConfigurationException(Throwable cause)
    {
        super(cause);
    }
}
