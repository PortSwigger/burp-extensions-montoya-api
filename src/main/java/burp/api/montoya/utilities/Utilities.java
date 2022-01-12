/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.utilities;

/**
 * This interface gives you access to other interfaces that have various data conversion and querying features.
 */
public interface Utilities
{
    /**
     * @return an instance of {@link burp.api.montoya.utilities.ByteUtils}
     */
    ByteUtils byteUtils();

    /**
     * @return an instance of {@link burp.api.montoya.utilities.URLUtils}
     */
    URLUtils urlUtils();

    /**
     * @return  an instance of {@link burp.api.montoya.utilities.Base64Utils}
     */
    Base64Utils base64Utils();
}
