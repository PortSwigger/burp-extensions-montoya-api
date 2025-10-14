package burp.api.montoya.utilities.rank;

/**
 * Algorithms available for ranking HTTP request/response pairs.
 * These algorithms analyze request/response data to assign ranking scores.
 * <p>
 * Note that the rank values are relative to other members in the collection.
 */
public enum RankingAlgorithm
{
    /**
     * Anomaly-based ranking algorithm that identifies unusual HTTP responses within a
     * given dataset. The higher the rank value, the more distinct the response is within the dataset.
     */
    ANOMALY
}
