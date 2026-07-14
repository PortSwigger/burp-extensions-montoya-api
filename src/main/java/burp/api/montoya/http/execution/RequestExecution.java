package burp.api.montoya.http.execution;

import burp.api.montoya.http.message.requests.HttpRequest;

/**
 * A live handle to a running {@link RequestExecutionEngine} send. Returned by
 * {@link RequestExecutionEngine#sendAll()} immediately, before the run has finished, so a
 * long-running send never blocks the caller. The same handle is passed to a
 * {@link ResponseHandler} for each completed request, so a handler can react to the run it is
 * part of.
 *
 * <p>Use it to {@link #queue(HttpRequest) queue} follow-up requests into the run, read live
 * progress via {@link #stats()}, and control or await the run via its {@link #lifetime()}.
 */
public interface RequestExecution
{
    /**
     * Queue another request into this run, with no correlation label. Equivalent to
     * {@code queue(request, "")}. Queuing keeps the run alive, so a {@link ResponseHandler} that
     * queues follow-ups extends the run, which drains naturally once queuing stops.
     *
     * @param request The full HTTP request to send.
     */
    void queue(HttpRequest request);

    /**
     * Queue another request into this run, tagged with a correlation label echoed back on its
     * {@link RequestResult#label()}.
     *
     * @param request The full HTTP request to send.
     * @param label   A correlation label; {@code null} is treated as {@code ""}.
     */
    void queue(HttpRequest request, String label);

    /**
     * @return A live snapshot of the run's progress.
     */
    ExecutionStats stats();

    /**
     * @return The run's lifetime - pause, resume, cancel, and await or be notified of completion.
     */
    RequestExecutionLifetime lifetime();
}
