/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.core;

/**
 * This interface represents annotations that can be applied to a message.
 */
public interface MessageAnnotations
{
    /**
     * An instance of message annotations representing no annotations.
     */
    MessageAnnotations NONE = from("", HighlightColor.NONE);

    String comment();

    HighlightColor highlightColor();

    /**
     * This method is used to construct a copy of the message annotations with
     * a new comment.
     *
     * @param comment The new comment.
     * @return The new message annotations.
     */
    MessageAnnotations withComment(String comment);

    /**
     * This method is used to construct a copy of the message annotations with
     * a new highlight color.
     *
     * @param highlightColor The new highlight color.
     * @return The new message annotations.
     */
    MessageAnnotations withHighlightColor(HighlightColor highlightColor);

    /**
     * This method is used to construct a message annotations from a comment.
     * @param comment        The comment of the annotation
     * @return The message annotations.
     */
    static MessageAnnotations comment(String comment)
    {
        return from(comment, HighlightColor.NONE);
    }

    /**
     * This method is used to construct a message annotations from a highlight
     * color.
     * @param highlightColor The highlight color of the annotation
     *
     * @return The message annotations.
     */
    static MessageAnnotations highlightColor(HighlightColor highlightColor)
    {
        return from("", highlightColor);
    }

    /**
     * This method is used to construct a message annotations from a comment
     * and a highlight color.
     *
     * @param comment        The comment of the annotation
     * @param highlightColor The highlight color of the annotation
     * @return The message annotations.
     */
    static MessageAnnotations from(String comment, HighlightColor highlightColor)
    {
        if (comment == null)
        {
            comment = "";
        }

        return new MessageAnnotationsImpl(comment, highlightColor);
    }

    class MessageAnnotationsImpl implements MessageAnnotations
    {
        private final String comment;
        private final HighlightColor highlightColor;

        private MessageAnnotationsImpl(String comment, HighlightColor highlightColor)
        {
            this.comment = comment;
            this.highlightColor = highlightColor;
        }

        @Override
        public String comment()
        {
            return comment;
        }

        @Override
        public HighlightColor highlightColor()
        {
            return highlightColor;
        }

        @Override
        public MessageAnnotations withComment(String comment)
        {
            return new MessageAnnotationsImpl(comment, highlightColor);
        }

        @Override
        public MessageAnnotations withHighlightColor(HighlightColor highlightColor)
        {
            return new MessageAnnotationsImpl(comment, highlightColor);
        }
    }
}
