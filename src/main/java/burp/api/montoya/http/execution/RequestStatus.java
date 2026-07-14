package burp.api.montoya.http.execution;

/**
 * The status of a single request sent by a {@link RequestExecutionEngine} - what happened
 * when the engine tried to send it.
 *
 * <p>{@link ExecutionStats#completed()} counts {@link #RESPONDED} results;
 * {@link ExecutionStats#failed()} counts all of {@link #TIMED_OUT},
 * {@link #CONNECTION_FAILED} and {@link #DROPPED}. Every request in a terminated run
 * has exactly one status, so {@code requested() == completed() + failed()}.
 */
public enum RequestStatus
{
    /**
     * A response was received - any HTTP status code, including 4xx and 5xx. A status
     * code means an answer came back, so it is always {@code RESPONDED}; only
     * transport-level failures are {@link #TIMED_OUT} or {@link #CONNECTION_FAILED}.
     */
    RESPONDED,

    /**
     * No response arrived within the timeout given to
     * {@link RequestExecutionEngine#sendAll(java.time.Duration) sendAll}.
     */
    TIMED_OUT,

    /**
     * The request could not complete at the transport level - connection refused or
     * reset, unknown host, TLS failure, or no usable response data.
     */
    CONNECTION_FAILED,

    /**
     * The request was never sent - it was abandoned because the run was cancelled
     * before it left the queue. Dropped requests appear in
     * {@link RequestExecutionResult#results()} but are not delivered to a
     * {@link ResponseHandler}.
     */
    DROPPED
}
