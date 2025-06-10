package burp.api.montoya.repeater;

/**
 * Provides access to the editable HTTP message components in Repeater.
 */
public interface HttpEditor
{
    /**
     * Retrieves the editor pane for modifying the HTTP request.
     *
     * @return An {@link EditorPane} instance for editing the request.
     */
    EditorPane requestPane();

    /**
     * Retrieves the editor pane for modifying the HTTP response.
     *
     * @return An {@link EditorPane} instance for editing the response.
     */
    EditorPane responsePane();
}
