/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.editor;

import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.ui.Selection;

import java.awt.Component;
import java.util.Optional;

/**
 * Provides extensions with an instance of Burp Suites HTTP response editor to use in their own user interface.
 */
public interface HttpResponseEditor extends Editor
{
    /**
     * @return an instance of {@link HttpResponse} derived from the contents of the editor.
     */
    HttpResponse getResponse();

    /**
     * Display the contents of an HTTP response in the editor.
     *
     * @param response The HTTP response to be set.
     */
    void setResponse(HttpResponse response);

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
    Component uiComponent();
}
