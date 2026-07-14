package burp.api.montoya.http.execution;

import burp.api.montoya.http.message.requests.HttpRequest;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * A request emitted by a {@link RequestSource}, paired with its optional correlation
 * label (echoed back on the corresponding {@link RequestResult#label()}).
 */
public interface SourcedRequest
{
    /**
     * @return The request to send.
     */
    HttpRequest request();

    /**
     * @return The correlation label; {@code ""} when none, never {@code null}.
     */
    String label();

    /**
     * Create a sourced request with no correlation label.
     *
     * @param request The request to send.
     *
     * @return The sourced request.
     */
    static SourcedRequest sourcedRequest(HttpRequest request)
    {
        return FACTORY.sourcedRequest(request);
    }

    /**
     * Create a sourced request with a correlation label.
     *
     * @param request The request to send.
     * @param label   A correlation label; {@code null} is treated as {@code ""}.
     *
     * @return The sourced request.
     */
    static SourcedRequest sourcedRequest(HttpRequest request, String label)
    {
        return FACTORY.sourcedRequest(request, label);
    }
}
