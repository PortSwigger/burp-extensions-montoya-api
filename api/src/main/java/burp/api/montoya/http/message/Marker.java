/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http.message;

import burp.api.montoya.core.Range;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

public interface Marker
{
    /**
     * @return The range of the marker.
     */
    Range range();

    /**
     * This method can be used to create a marker object with a range.
     *
     * @param range The range of the marker.
     * @return The marker with the range.
     */
    static Marker marker(Range range)
    {
        return FACTORY.marker(range);
    }

    /**
     * This method can be used to create a marker object from two indices representing a range.
     *
     * @param startIndexInclusive The start index of the range inclusive of this value.
     * @param endIndexExclusive   The end index of the range exclusive of this value.
     * @return The marker with the range.
     */
    static Marker marker(int startIndexInclusive, int endIndexExclusive)
    {
        return FACTORY.marker(startIndexInclusive, endIndexExclusive);
    }
}