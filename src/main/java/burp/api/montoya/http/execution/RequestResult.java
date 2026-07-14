package burp.api.montoya.http.execution;

import burp.api.montoya.http.message.HttpRequestResponse;

/**
 * The record of a single request sent by a {@link RequestExecutionEngine}: its
 * correlation label, what happened to it, and the request/response exchange.
 *
 * <p>One {@code RequestResult} exists per queued request. Instances are passed to a
 * {@link ResponseHandler} as each request completes and collected into
 * {@link RequestExecutionResult#results()}. They are an immutable record created by Burp;
 * there is no caller-facing factory. Whether a result is kept is decided by the
 * {@link ResponseHandler}'s {@link Retention} verdict, not on the result itself.
 */
public interface RequestResult
{
    /**
     * @return The correlation label supplied to {@link RequestExecutionEngine#queue(burp.api.montoya.http.message.requests.HttpRequest, String)}
     * (or {@link RequestExecution#queue(burp.api.montoya.http.message.requests.HttpRequest, String)}), echoed back unchanged.
     * {@code ""} when none was given; never {@code null}.
     */
    String label();

    /**
     * @return What happened to this request.
     */
    RequestStatus status();

    /**
     * The request/response exchange. The response is present whenever {@link #status()} is
     * {@link RequestStatus#RESPONDED}; for other outcomes only the request is present.
     *
     * @return The exchange for this request.
     */
    HttpRequestResponse requestResponse();
}
