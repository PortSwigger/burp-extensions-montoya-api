/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui;

import burp.api.montoya.core.Range;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface provides helpful information and functionality relating to a user's selection within the user interface.
 */
public interface Selection
{
    /**
     * A helper method to create an instance of {@link Selection} without positional data.
     *
     * @param selectionContents The contents of the selection.
     * @return A new instance of {@link Selection}
     */
    static Selection selection(byte[] selectionContents)
    {
        return selection(selectionContents, -1, -1);
    }

    /**
     * A helper method to create an instance of {@link Selection} without content data.
     *
     * @param startIndexInclusive The start position of the selection range.
     * @param endIndexExclusive   The end position of the selection range.
     * @return A new instance of {@link Selection}
     */
    static Selection selection(int startIndexInclusive, int endIndexExclusive)
    {
        return selection(null, startIndexInclusive, endIndexExclusive);
    }

    /**
     * A helper method to create an instance of {@link Selection}.
     *
     * @param selectionContents   The contents of the selection.
     * @param startIndexInclusive The start position of the selection range.
     * @param endIndexExclusive   The end position of the selection range.
     * @return A new instance of {@link Selection}
     */
    static Selection selection(byte[] selectionContents, int startIndexInclusive, int endIndexExclusive)
    {
        return FACTORY.selection(selectionContents, startIndexInclusive, endIndexExclusive);
    }

    /**
     * @return The contents that are derived from within the user's selection range.
     */
    byte[] contents();

    /**
     * @return The positional data of where the user has selected.
     */
    Range offsets();
}
