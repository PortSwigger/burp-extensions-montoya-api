package burp.api.montoya.http.execution;

/**
 * Controls the lifetime of a {@link RequestExecutionEngine} run and reports when it finishes.
 * Obtained from {@link RequestExecution#lifetime()}.
 *
 * <p>A run can be {@link #pause() paused} and {@link #resume() resumed}, or {@link #cancel()
 * cancelled} outright. Completion is observed either by blocking on {@link #awaitCompletion()}
 * or by registering an {@link #onComplete(CompletionHandler) onComplete} callback - the API
 * deliberately does not expose a {@link java.util.concurrent.CompletableFuture}.
 */
public interface RequestExecutionLifetime
{
    /**
     * Pause the run: stop sending new requests. Requests already in flight are allowed to
     * finish. Has no effect if the run has already finished. Resume with {@link #resume()}.
     */
    void pause();

    /**
     * Resume a {@link #pause() paused} run. Has no effect if the run is not paused or has
     * already finished.
     */
    void resume();

    /**
     * Cancel the run. No further requests are scheduled; requests already in flight are
     * allowed to finish, after which the run completes with the partial results. Requests
     * abandoned before they were sent are recorded with {@link RequestStatus#DROPPED}.
     */
    void cancel();

    /**
     * @return {@code true} once every queued request has finished or the run has been cancelled.
     */
    boolean finished();

    /**
     * Block the calling thread until the run finishes (drains or is cancelled) and return its
     * final result. Returns immediately if the run has already finished.
     *
     * @return The run's {@link RequestExecutionResult}.
     */
    RequestExecutionResult awaitCompletion();

    /**
     * Register a callback to be invoked once when the run finishes (drains or is cancelled),
     * without blocking. If the run has already finished, the handler is invoked immediately.
     *
     * @param handler The handler to invoke with the final {@link RequestExecutionResult}.
     */
    void onComplete(CompletionHandler handler);
}
