/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor;

/**
 * This interface provides extensions with an instance of Burp Suite's HTTP text editor to use in their own user interface.
 */
public interface RawEditor extends Editor
{
    /**
     * @param editable Boolean flag to toggle if this text editor is editable or not.
     */
    void setEditable(boolean editable);

    /**
     * @return The contents of the text editor.
     */
    byte[] getContents();

    /**
     * This method can be used to set content within the text editor programmatically.
     * The default system charset is used when encoding to a String.
     *
     * @param contents The content to set in the text editor.
     */
    void setContents(byte[] contents);
}
