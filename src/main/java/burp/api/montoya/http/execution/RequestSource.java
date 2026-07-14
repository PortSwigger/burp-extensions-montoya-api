package burp.api.montoya.http.execution;

import burp.api.montoya.http.message.requests.HttpRequest;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * A lazy source of requests for a {@link RequestExecutionEngine}, passed to
 * {@link RequestExecutionEngine#sendAll(RequestSource)}. The engine pulls the next request
 * on demand - only when it has capacity to send it - so requests are <strong>generated
 * lazily</strong> and only a bounded number are ever held in memory at once, regardless of
 * the total count. This is what lets a run cover an arbitrarily large (even unbounded)
 * number of requests without materialising them all up front.
 *
 * <p>{@link #next()} is called serially by the engine (never concurrently), so a stateful
 * source needs no synchronisation. Pair this with a {@link ResponseHandler} that returns
 * {@link Retention#DROP} for the uninteresting responses to keep the result set bounded too.
 *
 * <pre>
 * Iterator&lt;String&gt; words = hugeWordlist.iterator();
 * engine.sendAll(
 *     () -&gt; words.hasNext() ? SourcedRequest.sourcedRequest(base.withParameter(q(words.next()))) : null,
 *     (result, execution) -&gt; interesting(result) ? Retention.KEEP : Retention.DROP);
 * </pre>
 */
@FunctionalInterface
public interface RequestSource
{
    /**
     * @return The next request to send (with its optional label), or {@code null} when the
     * source is exhausted. Called serially, on demand. Throwing aborts the run with that error.
     */
    SourcedRequest next();

    /**
     * Adapt an {@link Iterator} of requests into a source (no correlation labels).
     *
     * @param iterator The requests to send, pulled lazily.
     *
     * @return A request source over the iterator.
     */
    static RequestSource from(Iterator<HttpRequest> iterator)
    {
        return () -> iterator.hasNext() ? SourcedRequest.sourcedRequest(iterator.next()) : null;
    }

    /**
     * Adapt a {@link Stream} of requests into a source (no correlation labels). The stream is
     * consumed lazily, so an infinite or generated stream is fine.
     *
     * @param stream The requests to send, pulled lazily.
     *
     * @return A request source over the stream.
     */
    static RequestSource from(Stream<HttpRequest> stream)
    {
        return from(stream.iterator());
    }
}
