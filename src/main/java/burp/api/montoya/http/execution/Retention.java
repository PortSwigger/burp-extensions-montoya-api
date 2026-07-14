package burp.api.montoya.http.execution;

/**
 * A {@link ResponseHandler}'s verdict on a single completed request: whether to retain its
 * {@link RequestResult} in the terminal {@link RequestExecutionResult}. Returned from
 * {@link ResponseHandler#onResponse}.
 *
 * <p>A run with no handler keeps every result. A handler returns {@link #DROP} for results it
 * does not want - useful for bounding memory on large runs, where the uninteresting results are
 * discarded as they arrive.
 */
public enum Retention
{
    /**
     * Keep this result in the terminal {@link RequestExecutionResult}.
     */
    KEEP,

    /**
     * Discard this result entirely: it does not appear in
     * {@link RequestExecutionResult#results()}. It is still counted in the run's
     * {@link ExecutionStats stats}.
     */
    DROP
}
