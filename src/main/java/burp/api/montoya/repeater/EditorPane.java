package burp.api.montoya.repeater;

import burp.api.montoya.core.ByteArray;

/**
 * Represents an editable pane allowing content to be modified in Repeater.
 */
public interface EditorPane
{
    /**
     * Replace the contents of the editor with the specified text.
     *
     * @param contents The new content as a plain string.
     */
    void set(String contents);

    /**
     * Replace the contents of the editor with the specified Burp ByteArray.
     *
     * @param contents The new contents for the pane as a {@link ByteArray}.
     */
    void set(ByteArray contents);

    /**
     * Sets the contents of the editor using an arbitrary object.
     * The object’s {@code toString()} method will be used to obtain the contents.
     *
     * @param contents An object whose {@code toString()} result will be set as the editor contents.
     */
    void set(Object contents);

    /**
     * Find all instances of the specified text and replace them with the given replacement text.
     *
     * @param search      The text to find within the editor.
     * @param replacement The text to replace each occurrence with.
     */
    void replace(String search, String replacement);
}
