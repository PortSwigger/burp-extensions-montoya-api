/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor;

import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.ui.Selection;

import javax.swing.JComponent;
import java.util.Optional;

/**
 * Provides extensions with an instance of Burp Suites HTTP request editor to use in their own user interface.
 */
public interface HttpRequestEditor extends Editor
{
    /**
     * @return an instance of {@link HttpRequest} derived from the contents of the editor.
     */
    HttpRequest getRequest();

    /**
     * Display the contents of an HTTP request in the editor.
     *
     * @param request The HTTP request to be set.
     */
    void setRequest(HttpRequest request);

    /**
     * {@inheritDoc}
     */
    @Override
    void setSearchExpression(String expression);

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isModified();

    /**
     * {@inheritDoc}
     */
    @Override
    int caretPosition();

    /**
     * {@inheritDoc}
     */
    @Override
    Optional<Selection> selection();

    /**
     * {@inheritDoc}
     */
    @Override
    JComponent uiComponent();
}
