package burp.api.montoya.utilities.rank;

import burp.api.montoya.http.message.HttpRequestResponse;

/**
 * A wrapper for a {@link burp.api.montoya.http.message.HttpRequestResponse} that contains a rank value.
 */
public interface RankedHttpRequestResponse extends Comparable<RankedHttpRequestResponse>
{
    /**
     * @return The HTTP request/response pair.
     */
    HttpRequestResponse requestResponse();

    /**
     * @return The rank value associated with this HTTP request/response pair.
     */
    int rank();
}
