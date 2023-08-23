package burp.api.montoya.http.message;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StatusCodeClassTest
{
    @ParameterizedTest
    @MethodSource("statusCodesFor1xx")
    void givenStatusCode_when1xxContains_thenTrueIsReturned(int statusCode, boolean expected)
    {
        assertThat(StatusCodeClass.CLASS_1XX_INFORMATIONAL_RESPONSE.contains(statusCode)).isEqualTo(expected);
    }

    private static Stream<Arguments> statusCodesFor1xx()
    {
        return IntStream.range(0, 700).boxed().map(statusCode -> Arguments.arguments(statusCode, statusCode >= 100 && statusCode <= 199));
    }

    @ParameterizedTest
    @MethodSource("statusCodesFor2xx")
    void givenStatusCode_when2xxContains_thenTrueIsReturned(int statusCode, boolean expected)
    {
        assertThat(StatusCodeClass.CLASS_2XX_SUCCESS.contains(statusCode)).isEqualTo(expected);
    }

    private static Stream<Arguments> statusCodesFor2xx()
    {
        return IntStream.range(0, 700).boxed().map(statusCode -> Arguments.arguments(statusCode, statusCode >= 200 && statusCode <= 299));
    }

    @ParameterizedTest
    @MethodSource("statusCodesFor3xx")
    void givenStatusCode_when3xxContains_thenTrueIsReturned(int statusCode, boolean expected)
    {
        assertThat(StatusCodeClass.CLASS_3XX_REDIRECTION.contains(statusCode)).isEqualTo(expected);
    }

    private static Stream<Arguments> statusCodesFor3xx()
    {
        return IntStream.range(0, 700).boxed().map(statusCode -> Arguments.arguments(statusCode, statusCode >= 300 && statusCode <= 399));
    }

    @ParameterizedTest
    @MethodSource("statusCodesFor4xx")
    void givenStatusCode_when4xxContains_thenTrueIsReturned(int statusCode, boolean expected)
    {
        assertThat(StatusCodeClass.CLASS_4XX_CLIENT_ERRORS.contains(statusCode)).isEqualTo(expected);
    }

    private static Stream<Arguments> statusCodesFor4xx()
    {
        return IntStream.range(0, 700).boxed().map(statusCode -> Arguments.arguments(statusCode, statusCode >= 400 && statusCode <= 499));
    }

    @ParameterizedTest
    @MethodSource("statusCodesFor5xx")
    void givenStatusCode_when5xxContains_thenTrueIsReturned(int statusCode, boolean expected)
    {
        assertThat(StatusCodeClass.CLASS_5XX_SERVER_ERRORS.contains(statusCode)).isEqualTo(expected);
    }

    private static Stream<Arguments> statusCodesFor5xx()
    {
        return IntStream.range(0, 700).boxed().map(statusCode -> Arguments.arguments(statusCode, statusCode >= 500 && statusCode <= 599));
    }
}