package burp.api.montoya.http.handler;

import java.time.Duration;

/**
 * Timing data
 */
public interface TimingData
{
    /**
     * The time between when Burp sent the request and the start of the response being received.
     *
     * @return the duration or null if no response returned.
     */
    Duration timeBetweenRequestSentAndStartOfResponse();

    /**
     * The time between when Burp sent the request and the end of the response being received.
     *
     * @return the duration or null if no response returned or the response never completes.
     */
    Duration timeBetweenRequestSentAndEndOfResponse();
}