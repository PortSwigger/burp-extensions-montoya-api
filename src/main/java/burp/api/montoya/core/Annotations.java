/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.core;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Annotations stored with requests and responses in Burp Suite.
 */
public interface Annotations
{
    /**
     * @return the comment
     */
    String comment();

    /**
     * Set (mutate) the current annotations comment value
     *
     * @param comment the comment to set on the current annotation
     */
    void setComment(String comment);

    /**
     * @return the highlight color;
     */
    HighlightColor highlightColor();

    /**
     * Set (mutate) the current annotations highlight color value
     *
     * @param highlightColor the highlight color to set on the current annotation
     */
    void setHighlightColor(HighlightColor highlightColor);

    /**
     * Create a copy of the annotations with a new comment.
     *
     * @param comment The new comment.
     *
     * @return The new annotations.
     */
    Annotations withComment(String comment);

    /**
     * Create a copy of the annotations with a new highlight color.
     *
     * @param highlightColor The new highlight color.
     *
     * @return The new annotations.
     */
    Annotations withHighlightColor(HighlightColor highlightColor);

    /**
     * Create a new empty annotations.
     *
     * @return The annotations.
     */
    static Annotations annotations()
    {
        return FACTORY.annotations();
    }

    /**
     * Create a new annotations with a comment.
     *
     * @param comment The comment of the annotation
     *
     * @return The annotations.
     */
    static Annotations annotations(String comment)
    {
        return FACTORY.annotations(comment);
    }

    /**
     * Create a new annotations with a highlight color.
     *
     * @param highlightColor The highlight color of the annotation
     *
     * @return The annotations.
     */
    static Annotations annotations(HighlightColor highlightColor)
    {
        return FACTORY.annotations(highlightColor);
    }

    /**
     * Create a new annotations with a comment and a highlight color.
     *
     * @param comment        The comment of the annotation
     * @param highlightColor The highlight color of the annotation
     *
     * @return The annotations.
     */
    static Annotations annotations(String comment, HighlightColor highlightColor)
    {
        return FACTORY.annotations(comment, highlightColor);
    }
}
