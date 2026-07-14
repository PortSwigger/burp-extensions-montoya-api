package burp.api.montoya.http.execution;

import java.util.List;

/**
 * The outcome of a {@link RequestExecutionEngine} run: the {@link RequestResult}s it
 * retained, along with its final {@link #stats()}.
 */
public interface RequestExecutionResult
{
    /**
     * The {@link RequestResult}s retained by the run, in completion order. A result the
     * {@link ResponseHandler} returned {@link Retention#DROP} for is excluded entirely. Use
     * {@link #stats()} for totals (which still count dropped results).
     *
     * @return The retained request results, in completion order.
     */
    List<RequestResult> results();

    /**
     * @return The run's final progress counts and total {@link ExecutionStats#elapsed() duration}.
     */
    ExecutionStats stats();

    /**
     * @return {@code true} if the run was cancelled before all queued requests completed.
     */
    boolean cancelled();
}
