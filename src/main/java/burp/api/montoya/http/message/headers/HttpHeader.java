/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message.headers;

/**
 * This interface is used to hold details about an HTTP/2 header.
 */
public interface HttpHeader
{
    /**
     * @return The name of the header.
     */
    String name();

    /**
     * @return The value of the header.
     */
    String value();

    /**
     * @return The {@code String} representation of the header.
     */
    @Override
    String toString();

    /**
     * This is a helper method for creating a new instance of {@code HttpHeader} from name and value.
     *
     * @param name The name of the header.
     * @param value The value of the header.
     * @return A new {@code HttpHeader} instance.
     */
    static HttpHeader from(String name, String value)
    {
        String header = name + ": " + value;

        return new HttpHeader()
        {
            @Override
            public String name()
            {
                return name;
            }

            @Override
            public String value()
            {
                return value;
            }

            @Override
            public String toString()
            {
                return header;
            }
        };
    }

    /**
     * This is a helper method for creating a new instance of HttpHeader from a {@code String} header representation.
     *
     * @param header The {@code String} header representation.
     * @return A new {@code HttpHeader} instance.
     */
    static HttpHeader from(String header)
    {
        int colonIndex = header.indexOf(":");
        int valueStartIndex = colonIndex != -1 && colonIndex + 1 < header.length() && header.charAt(colonIndex + 1) == ' ' ? colonIndex + 2 : colonIndex + 1;

        String name = colonIndex == -1 ? header : header.substring(0, colonIndex);
        String value = colonIndex == -1 ? "" : header.substring(valueStartIndex);

        return new HttpHeader()
        {
            @Override
            public String name()
            {
                return name;
            }

            @Override
            public String value()
            {
                return value;
            }

            @Override
            public String toString()
            {
                return header;
            }
        };
    }
}
