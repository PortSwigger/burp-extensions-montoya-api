/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.core;

/**
 * This interface represents a range of integer values between two values
 * where the range is includes the start value but excludes the end value.
 */
public interface Range
{
    int startIndexInclusive();

    int endIndexExclusive();

    /**
     * This method can be used to create a range object from two indices.
     *
     * @param startIndexInclusive The start index of the range inclusive of this value.
     * @param endIndexExclusive The end index of the range exclusive of this value.
     * @return The range.
     */
    static Range of(int startIndexInclusive, int endIndexExclusive)
    {
        return new Range()
        {
            @Override
            public int startIndexInclusive()
            {
                return startIndexInclusive;
            }

            @Override
            public int endIndexExclusive()
            {
               return endIndexExclusive;
            }

            @Override
            public String toString()
            {
                return String.format("[%d, %d]", startIndexInclusive, endIndexExclusive);
            }
        };
    }
}
