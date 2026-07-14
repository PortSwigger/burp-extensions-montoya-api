package burp.api.montoya.http.execution;

/**
 * A callback invoked once when a {@link RequestExecutionEngine} run finishes - either because
 * every queued request completed or because the run was cancelled. Register one with
 * {@link RequestExecutionLifetime#onComplete(CompletionHandler)} to be notified without
 * blocking a thread.
 */
@FunctionalInterface
public interface CompletionHandler
{
    /**
     * Invoked once with the run's final {@link RequestExecutionResult}.
     *
     * @param result The collected results of the finished (or cancelled) run.
     */
    void onComplete(RequestExecutionResult result);
}
