/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.core;

import org.junit.jupiter.api.Test;

import static burp.api.montoya.core.HighlightColor.BLUE;
import static burp.api.montoya.core.HighlightColor.CYAN;
import static burp.api.montoya.core.HighlightColor.NONE;

import static org.assertj.core.api.Assertions.assertThat;

class MessageAnnotationsTest
{
    @Test
    void createMessageAnnotationTest()
    {
        MessageAnnotations messageAnnotations = MessageAnnotations.from("foo", BLUE);

        assertThat(messageAnnotations.comment()).isEqualTo("foo");
        assertThat(messageAnnotations.highlightColor()).isEqualTo(BLUE);
    }

    @Test
    void createMessageAnnotationWithNullCommentTest()
    {
        MessageAnnotations messageAnnotations = MessageAnnotations.from(null, BLUE);

        assertThat(messageAnnotations.comment()).isEqualTo("");
        assertThat(messageAnnotations.highlightColor()).isEqualTo(BLUE);
    }

    @Test
    void createMessageAnnotationFromCommentTest()
    {
        MessageAnnotations messageAnnotations = MessageAnnotations.comment("foo");

        assertThat(messageAnnotations.comment()).isEqualTo("foo");
        assertThat(messageAnnotations.highlightColor()).isEqualTo(NONE);
    }

    @Test
    void createMessageAnnotationFromColorTest()
    {
        MessageAnnotations messageAnnotations = MessageAnnotations.highlightColor(BLUE);

        assertThat(messageAnnotations.comment()).isEqualTo("");
        assertThat(messageAnnotations.highlightColor()).isEqualTo(BLUE);
    }

    @Test
    void withCommentTest()
    {
        MessageAnnotations messageAnnotations = MessageAnnotations.from("foo", BLUE);

        MessageAnnotations modifiedAnnotations = messageAnnotations.withComment("bar");

        assertThat(messageAnnotations.comment()).isEqualTo("foo");
        assertThat(messageAnnotations.highlightColor()).isEqualTo(BLUE);
        assertThat(modifiedAnnotations.comment()).isEqualTo("bar");
        assertThat(modifiedAnnotations.highlightColor()).isEqualTo(BLUE);
    }

    @Test
    void withHighlightColorTest()
    {
        MessageAnnotations messageAnnotations = MessageAnnotations.from("foo", BLUE);

        MessageAnnotations modifiedAnnotations = messageAnnotations.withHighlightColor(CYAN);

        assertThat(messageAnnotations.comment()).isEqualTo("foo");
        assertThat(messageAnnotations.highlightColor()).isEqualTo(BLUE);
        assertThat(modifiedAnnotations.comment()).isEqualTo("foo");
        assertThat(modifiedAnnotations.highlightColor()).isEqualTo(CYAN);
    }
}