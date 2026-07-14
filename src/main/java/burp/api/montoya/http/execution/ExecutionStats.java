package burp.api.montoya.http.execution;

import java.time.Duration;

/**
 * A snapshot of a {@link RequestExecutionEngine} run's progress: how many requests have been
 * requested, completed and failed, how many are in flight or pending, and how long the run
 * has been going. The same shape is reported live during a run (via
 * {@link RequestExecution#stats()}) and for the terminated run (via
 * {@link RequestExecutionResult#stats()}).
 */
public interface ExecutionStats
{
    /**
     * @return The number of requests requested so far for this run.
     */
    int requested();

    /**
     * @return The number of requests that have received a response so far ({@link RequestStatus#RESPONDED}).
     */
    int completed();

    /**
     * @return The number of requests that have failed so far (connection failure, timeout, unknown host, or abandoned on cancellation).
     */
    int failed();

    /**
     * @return The number of requests sent and awaiting a response.
     */
    int inFlight();

    /**
     * @return The number of requests queued but not yet sent.
     */
    int pending();

    /**
     * @return The elapsed time for the run so far, or its total duration once finished.
     */
    Duration elapsed();
}
