package burp.api.montoya.http.message;

/**
 * Status code classes that are defined in the HTTP standard.
 */
public enum StatusCodeClass
{
    /**
     * The Enum that represents status codes 100 to 199.
     */
    CLASS_1XX_INFORMATIONAL_RESPONSE,
    /**
     * The Enum that represents status codes 200 to 299.
     */
    CLASS_2XX_SUCCESS,
    /**
     * The Enum that represents status codes 300 to 399.
     */
    CLASS_3XX_REDIRECTION,
    /**
     * The Enum that represents status codes 400 to 499.
     */
    CLASS_4XX_CLIENT_ERRORS,
    /**
     * The Enum that represents status codes 500 to 599.
     */
    CLASS_5XX_SERVER_ERRORS
}
