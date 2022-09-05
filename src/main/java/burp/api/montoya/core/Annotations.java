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
 * This interface represents annotations that can be applied to a message.
 */
public interface Annotations
{
    /**
     * This method is used to construct empty annotations.
     *
     * @return The annotations.
     */
    static Annotations annotations()
    {
        return annotations("", HighlightColor.NONE);
    }

    /**
     * This method is used to construct annotations from a comment.
     *
     * @param comment The comment of the annotation
     * @return The annotations.
     */
    static Annotations annotations(String comment)
    {
        return annotations(comment, HighlightColor.NONE);
    }

    /**
     * This method is used to construct a annotations from a highlight
     * color.
     *
     * @param highlightColor The highlight color of the annotation
     * @return The annotations.
     */
    static Annotations annotations(HighlightColor highlightColor)
    {
        return annotations("", highlightColor);
    }

    /**
     * This method is used to construct a annotations from a comment
     * and a highlight color.
     *
     * @param comment        The comment of the annotation
     * @param highlightColor The highlight color of the annotation
     * @return The annotations.
     */
    static Annotations annotations(String comment, HighlightColor highlightColor)
    {
        return FACTORY.annotations(comment, highlightColor);
    }

    String comment();

    HighlightColor highlightColor();

    /**
     * This method is used to construct a copy of the annotations with
     * a new comment.
     *
     * @param comment The new comment.
     * @return The new annotations.
     */
    Annotations withComment(String comment);

    /**
     * This method is used to construct a copy of the annotations with
     * a new highlight color.
     *
     * @param highlightColor The new highlight color.
     * @return The new annotations.
     */
    Annotations withHighlightColor(HighlightColor highlightColor);
}
