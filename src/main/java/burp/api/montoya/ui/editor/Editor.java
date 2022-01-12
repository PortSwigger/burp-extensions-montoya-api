/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor;

import burp.api.montoya.ui.Selection;

import javax.swing.JComponent;
import java.util.Optional;

/**
 * This interface provides the shared behaviour between the different editor types available from the Extender API.
 */
public interface Editor
{
    /**
     * This method is used to update the search expression that is shown in the search bar below the editor.
     *
     * @param expression The search expression.
     */
    void setSearchExpression(String expression);

    /**
     * @return True if the user has modified the contents of the editor since the last time the content was set programmatically.
     */
    boolean isModified();

    /**
     * @return The index of the position for the carat within the current message editor.
     */
    int caretPosition();

    /**
     * This will return {@link Optional#empty()} if the user has not made a selection.
     *
     * @return An {@link Optional} containing the users current selection in the editor.
     */
    Optional<Selection> selection();

    /**
     * @return UI component of the editor, for extensions to add to their own UI.
     */
    JComponent uiComponent();
}
