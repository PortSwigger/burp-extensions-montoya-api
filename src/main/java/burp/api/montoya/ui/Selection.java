/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui;

import burp.api.montoya.core.Range;

/**
 * This interface provides helpful information and functionality relating to a user's selection within the user interface.
 */
public interface Selection
{
    /**
     * @return The contents that are derived from within the user's selection range.
     */
    byte[] contents();

    /**
     * @return The positional data of where the user has selected.
     */
    Range offsets();

    /**
     * A helper method to create an instance of {@link Selection} without positional data.
     *
     * @param selectionContents The contents of the selection.
     * @return A new instance of {@link Selection}
     */
    static Selection ofContents(byte[] selectionContents)
    {
        return of(selectionContents, -1, -1);
    }

    /**
     * A helper method to create an instance of {@link Selection} without content data.
     *
     * @param startIndexInclusive The start position of the selection range.
     * @param endIndexExclusive The end position of the selection range.
     * @return A new instance of {@link Selection}
     */
    static Selection ofOffsets(int startIndexInclusive, int endIndexExclusive)
    {
        return of(null, startIndexInclusive, endIndexExclusive);
    }

    /**
     * A helper method to create an instance of {@link Selection}.
     *
     * @param selectionContents The contents of the selection.
     * @param startIndexInclusive The start position of the selection range.
     * @param endIndexExclusive The end position of the selection range.
     * @return A new instance of {@link Selection}
     */
    static Selection of(byte[] selectionContents, int startIndexInclusive, int endIndexExclusive)
    {
        return new Selection()
        {
            @Override
            public byte[] contents()
            {
                return selectionContents;
            }

            @Override
            public Range offsets()
            {
                return Range.of(startIndexInclusive, endIndexExclusive);
            }
        };
    }
}
