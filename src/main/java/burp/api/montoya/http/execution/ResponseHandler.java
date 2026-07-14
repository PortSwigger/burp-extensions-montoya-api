package burp.api.montoya.http.execution;

import burp.api.montoya.http.Http;

/**
 * A reactive handler invoked by a {@link RequestExecutionEngine} once per request as it
 * completes, passed to {@link RequestExecutionEngine#sendAll(ResponseHandler)}. Each call
 * returns a {@link Retention} verdict deciding whether that result is kept in the terminal
 * {@link RequestExecutionResult} or discarded.
 *
 * <p>Invocation is <strong>serial</strong> - at most one call is in progress at a time,
 * in completion order - so handler-captured state needs no synchronization. The handler
 * receives the live {@link RequestExecution} it is part of, which it can use to queue further
 * requests into the same run or control the run via its
 * {@link RequestExecution#lifetime() lifetime}.
 *
 * <p>{@link RequestStatus#DROPPED} requests (abandoned before sending when the run is cancelled)
 * are <em>not</em> delivered to the handler, though they still appear in
 * {@link RequestExecutionResult#results()}.
 *
 * <p>Exceptions thrown by the handler are caught and logged; the run continues.
 *
 * <p>To flag "interesting" responses by how they differ from a baseline, build a
 * {@link Http#createResponseVariationsAnalyzer()} or
 * {@link Http#createResponseKeywordsAnalyzer(java.util.List)}, feed it baseline responses,
 * then {@link Retention#DROP} the responses that do not vary - the engine has no built-in
 * "interesting" flag by design.
 *
 * <pre>
 * engine.sendAll((result, execution) -&gt; {
 *     if (result.status() == RequestStatus.RESPONDED &amp;&amp; isInteresting(result.requestResponse())) {
 *         execution.queue(followUpFor(result));   // keeps the run alive
 *         return Retention.KEEP;
 *     }
 *     return Retention.DROP;
 * });
 * </pre>
 */
@FunctionalInterface
public interface ResponseHandler
{
    /**
     * Invoked once for each request that reaches a terminal {@link RequestStatus} other than
     * {@link RequestStatus#DROPPED}.
     *
     * @param result    The completed request's result.
     * @param execution The live run - queue more requests, read {@link RequestExecution#stats() progress}, or control it via its {@link RequestExecution#lifetime() lifetime}.
     *
     * @return {@link Retention#KEEP} to retain this result in the terminal {@link RequestExecutionResult}, or {@link Retention#DROP} to discard it.
     */
    Retention onResponse(RequestResult result, RequestExecution execution);
}
