package burp.api.montoya.http.message;

import burp.api.montoya.core.Range;

/**
 * Status code classes that are defined in the HTTP standard.
 */
public enum StatusCodeClass
{
    CLASS_1XX_INFORMATIONAL_RESPONSE(Range.range(100, 200)),
    CLASS_2XX_SUCCESS(Range.range(200, 300)),
    CLASS_3XX_REDIRECTION(Range.range(300, 400)),
    CLASS_4XX_CLIENT_ERRORS(Range.range(400, 500)),
    CLASS_5XX_SERVER_ERRORS(Range.range(500, 600));

    private final Range range;

    StatusCodeClass(Range range)
    {
        this.range = range;
    }

    /**
     * @param statusCode The status code to test.
     *
     * @return True if the status code is in the status code class.
     */
    public boolean contains(short statusCode)
    {
        return range.contains(statusCode);
    }
}
