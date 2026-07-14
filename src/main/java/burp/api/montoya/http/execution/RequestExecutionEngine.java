package burp.api.montoya.http.execution;

import burp.api.montoya.http.Http;
import burp.api.montoya.http.message.requests.HttpRequest;

import java.time.Duration;

/**
 * A managed engine for sending many HTTP requests concurrently, subject to its
 * {@link ResourcePool} (concurrency, throttling, retries). The run is visible and controllable
 * as a task on the Burp dashboard.
 *
 * <p>Obtain an instance from {@link Http#createRequestEngine()} or
 * {@link Http#createRequestEngine(RequestEngineOptions)}, then drive it one of three ways:
 *
 * <ul>
 *   <li><b>Eager</b> - {@link #queue(HttpRequest) queue} the requests up front, then
 *       {@link #sendAll()}.</li>
 *   <li><b>Reactive</b> - queue one or more seed requests, then {@link #sendAll(ResponseHandler)}
 *       and let the handler {@link RequestExecution#queue queue} follow-ups as responses arrive.</li>
 *   <li><b>Streaming</b> - supply a {@link #sendAll(RequestSource) source} that generates
 *       requests lazily on demand, for very large or open-ended runs.</li>
 * </ul>
 *
 * <p>Each {@code sendAll} overload optionally takes a per-request {@link Duration} timeout; the
 * overloads without one apply a sensible default. The send runs in the background and
 * {@code sendAll} returns immediately with a {@link RequestExecution} handle - so a long-running
 * send never blocks the caller. Each queued request may carry a correlation
 * {@link #queue(HttpRequest, String) label} echoed back on its {@link RequestResult}.
 *
 * <p>An engine is <strong>single-use</strong>: exactly one {@code sendAll} call starts its one
 * run. Any further {@code sendAll} call (of any overload) throws {@link IllegalStateException};
 * create a new engine for another run.
 */
public interface RequestExecutionEngine
{
    /**
     * Add a request to the engine's queue. Equivalent to {@code queue(request, "")}.
     *
     * @param request The full HTTP request to send.
     */
    void queue(HttpRequest request);

    /**
     * Add a request to the engine's queue, tagged with a correlation label that is echoed
     * back on the corresponding {@link RequestResult#label()}. The label is opaque to the
     * engine; a {@code null} label is normalised to {@code ""}.
     *
     * @param request The full HTTP request to send.
     * @param label   A correlation label; {@code null} is treated as {@code ""}.
     */
    void queue(HttpRequest request, String label);

    /**
     * Start the run from the eagerly {@link #queue(HttpRequest) queued} requests, using the
     * default per-request timeout. Requests are sent in the background and this method returns
     * immediately with a live {@link RequestExecution} handle.
     *
     * @return A live handle to the running send.
     *
     * @throws IllegalStateException if a run has already been started on this engine.
     */
    RequestExecution sendAll();

    /**
     * As {@link #sendAll()}, with an explicit per-request timeout: a request with no response
     * within {@code timeout} is recorded as {@link RequestStatus#TIMED_OUT} (and retried per the
     * {@link ResourcePool}).
     *
     * @param timeout The per-request timeout (positive).
     *
     * @return A live handle to the running send.
     *
     * @throws IllegalArgumentException if {@code timeout} is null, zero or negative.
     * @throws IllegalStateException    if a run has already been started on this engine.
     */
    RequestExecution sendAll(Duration timeout);

    /**
     * Start the run with the default per-request timeout, invoking {@code handler} once per
     * request as it completes - serially, in completion order (see {@link ResponseHandler}). The
     * handler decides whether each result is {@link Retention kept or dropped} and may queue
     * more requests via the {@link RequestExecution} it receives. Returns immediately.
     *
     * @param handler Invoked for each completed request (except {@link RequestStatus#DROPPED} ones).
     *
     * @return A live handle to the running send.
     *
     * @throws IllegalStateException if a run has already been started on this engine.
     */
    RequestExecution sendAll(ResponseHandler handler);

    /**
     * As {@link #sendAll(ResponseHandler)}, with an explicit per-request timeout.
     *
     * @param handler Invoked for each completed request (except {@link RequestStatus#DROPPED} ones).
     * @param timeout The per-request timeout (positive).
     *
     * @return A live handle to the running send.
     *
     * @throws IllegalArgumentException if {@code timeout} is null, zero or negative.
     * @throws IllegalStateException    if a run has already been started on this engine.
     */
    RequestExecution sendAll(ResponseHandler handler, Duration timeout);

    /**
     * Start a run that pulls its requests lazily from {@code source} instead of from a
     * pre-queued list, using the default per-request timeout. The engine asks the source for the
     * next request only when it has capacity to send it, so requests are generated on demand and
     * memory stays bounded regardless of the total count - use this for very large or open-ended
     * runs. Returns immediately.
     *
     * <p>A streaming run cannot be combined with eagerly {@link #queue(HttpRequest) queued}
     * requests - use one or the other.
     *
     * @param source The lazy source of requests to send.
     *
     * @return A live handle to the running send.
     *
     * @throws IllegalStateException if a run has already been started on this engine, or if
     *                               requests were already {@link #queue(HttpRequest) queued} on this engine.
     */
    RequestExecution sendAll(RequestSource source);

    /**
     * As {@link #sendAll(RequestSource)}, with an explicit per-request timeout.
     *
     * @param source  The lazy source of requests to send.
     * @param timeout The per-request timeout (positive).
     *
     * @return A live handle to the running send.
     *
     * @throws IllegalArgumentException if {@code timeout} is null, zero or negative.
     * @throws IllegalStateException    if a run has already been started, or requests were already queued.
     */
    RequestExecution sendAll(RequestSource source, Duration timeout);

    /**
     * As {@link #sendAll(RequestSource)}, additionally invoking {@code handler} once per request
     * as it completes - serially (see {@link ResponseHandler}) - using the default per-request
     * timeout. The handler decides whether each result is {@link Retention kept or dropped},
     * which can bound the result set on a large run. Returns immediately.
     *
     * @param source  The lazy source of requests to send.
     * @param handler Invoked for each completed request (except {@link RequestStatus#DROPPED} ones).
     *
     * @return A live handle to the running send.
     *
     * @throws IllegalStateException if a run has already been started, or requests were already queued.
     */
    RequestExecution sendAll(RequestSource source, ResponseHandler handler);

    /**
     * As {@link #sendAll(RequestSource, ResponseHandler)}, with an explicit per-request timeout.
     *
     * @param source  The lazy source of requests to send.
     * @param handler Invoked for each completed request (except {@link RequestStatus#DROPPED} ones).
     * @param timeout The per-request timeout (positive).
     *
     * @return A live handle to the running send.
     *
     * @throws IllegalArgumentException if {@code timeout} is null, zero or negative.
     * @throws IllegalStateException    if a run has already been started, or requests were already queued.
     */
    RequestExecution sendAll(RequestSource source, ResponseHandler handler, Duration timeout);
}
