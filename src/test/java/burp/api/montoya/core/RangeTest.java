/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RangeTest
{
    public static Stream<Arguments> ranges()
    {
        return Stream.of(
                arguments(Range.of(1, 3), "[1, 3]"),
                arguments(Range.of(10, 30), "[10, 30]"),
                arguments(Range.of(100, 300), "[100, 300]")
        );
    }

    @ParameterizedTest
    @MethodSource("ranges")
    void givenRange_whenToString_thenCorrect(Range range, String expectedRangeString)
    {
        assertThat(range.toString()).isEqualTo(expectedRangeString);
    }
}