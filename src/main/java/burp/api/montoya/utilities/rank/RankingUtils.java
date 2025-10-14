package burp.api.montoya.utilities.rank;

import burp.api.montoya.http.message.HttpRequestResponse;

import java.util.Collection;
import java.util.List;

/**
 * Utilities for ranking HTTP request/response pairs based on different criteria.
 * <p>Note that the returned lists are not sorted by rank. This can be done using Collections.sort(). </p>
 */
public interface RankingUtils
{
    /**
     * Ranks a list of HTTP request/response pairs using the default Anomaly-based ranking algorithm
     *
     * @param requestResponses The list of HTTP request/response pairs to rank.
     * @return A list of ranked HTTP request/response pairs.
     */
    List<RankedHttpRequestResponse> rank(Collection<HttpRequestResponse> requestResponses);

    /**
     * Ranks a list of HTTP request/response pairs using the specified ranking algorithm.
     *
     * @param requestResponses The list of HTTP request/response pairs to rank.
     * @param algorithm The ranking algorithm to use for analysis.
     * @return A list of ranked HTTP request/response pairs.
     */
    List<RankedHttpRequestResponse> rank(Collection<HttpRequestResponse> requestResponses, RankingAlgorithm algorithm);
}
