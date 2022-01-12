/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class HttpServiceTest
{
    @Test
    void givenHostPortAndSecure_whenServiceConstructed_thenServiceIsCorrect()
    {
        HttpService service = HttpService.from("localhost", 8443, true);

        assertThat(service.host()).isEqualTo("localhost");
        assertThat(service.port()).isEqualTo(8443);
        assertThat(service.secure()).isEqualTo(true);
    }

    @Test
    void givenHostAndInsecure_whenServiceConstructed_thenServiceIsCorrect()
    {
        HttpService service = HttpService.from("localhost", false);

        assertThat(service.host()).isEqualTo("localhost");
        assertThat(service.port()).isEqualTo(80);
        assertThat(service.secure()).isEqualTo(false);
    }

    @Test
    void givenHostAndSecure_whenServiceConstructed_thenServiceIsCorrect()
    {
        HttpService service = HttpService.from("localhost", true);

        assertThat(service.host()).isEqualTo("localhost");
        assertThat(service.port()).isEqualTo(443);
        assertThat(service.secure()).isEqualTo(true);
    }

    @Test
    void givenInvalidUrl_whenServiceConstructed_thenCorrectExceptionIsThrown()
    {
        assertThatIllegalArgumentException().isThrownBy(() -> HttpService.from("invalid"));
    }

    @ParameterizedTest
    @MethodSource("serviceFromUrl")
    void givenUrl_whenServiceConstructed_thenServiceIsCorrect(String url, String host, int port, boolean secure)
    {
        HttpService service = HttpService.from(url);

        assertThat(service.host()).isEqualTo(host);
        assertThat(service.port()).isEqualTo(port);
        assertThat(service.secure()).isEqualTo(secure);
    }

    private static Stream<Arguments> serviceFromUrl()
    {
        return Stream.of(
                Arguments.of("http://localhost", "localhost", 80, false),
                Arguments.of("https://localhost", "localhost", 443, true),
                Arguments.of("http://localhost:8080", "localhost", 8080, false),
                Arguments.of("https://localhost:8443", "localhost", 8443, true)
        );
    }
}