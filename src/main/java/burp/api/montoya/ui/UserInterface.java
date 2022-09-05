/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui;

import burp.api.montoya.core.Registration;
import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;
import burp.api.montoya.ui.editor.EditorOptions;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.HttpResponseEditor;
import burp.api.montoya.ui.editor.RawEditor;
import burp.api.montoya.ui.editor.extension.ExtensionHttpRequestEditorProvider;
import burp.api.montoya.ui.editor.extension.ExtensionHttpResponseEditorProvider;
import burp.api.montoya.ui.swing.SwingUtils;

import java.awt.Component;

/**
 * This interface gives you access to various user interface related features.
 * Such as registering your own User Interface providers, creating instances of Burps various editors
 * and applying themes to custom components.
 */
public interface UserInterface
{
    /**
     * This method is used to add a custom tab to the main Burp Suite window.
     *
     * @param title     The text to be displayed in the tab heading.
     * @param component The component that will be rendered within the custom tab.
     * @return A {@link Registration} of the custom suite tab.
     */
    Registration registerSuiteTab(String title, Component component);

    /**
     * This method can be used to register a provider of custom context menu items.
     *
     * @param provider The provider to register.
     * @return A {@link Registration} of the context menu item provider.
     */
    Registration registerContextMenuItemsProvider(ContextMenuItemsProvider provider);

    /**
     * This method can be used to register a provider of custom HTTP request editors.
     *
     * @param provider The provider to register.
     * @return A {@link Registration} of the HTTP request editor provider.
     */
    Registration registerHttpRequestEditorProvider(ExtensionHttpRequestEditorProvider provider);

    /**
     * This method can be used to register a provider of custom HTTP response editors.
     *
     * @param provider The provider to register.
     * @return A {@link Registration} of the HTTP response editor provider.
     */
    Registration registerHttpResponseEditorProvider(ExtensionHttpResponseEditorProvider provider);

    /**
     * This method is used to create a new instance of Burp's plain text editor, for the extension to use in its own UI.
     *
     * @param options Optional options to apply to the editor.
     * @return An instance of the {@link RawEditor} interface.
     */
    RawEditor createRawEditor(EditorOptions... options);

    /**
     * This method is used to create a new instance of Burp's HTTP request editor, for the extension to use in its own UI.
     *
     * @param options Optional options to apply to the editor.
     * @return An instance of the {@link HttpRequestEditor} interface.
     */
    HttpRequestEditor createHttpRequestEditor(EditorOptions... options);

    /**
     * This method is used to create a new instance of Burp's HTTP response editor, for the extension to use in its own UI.
     *
     * @param options Optional options to apply to the editor.
     * @return An instance of the {@link HttpResponseEditor} interface.
     */
    HttpResponseEditor createHttpResponseEditor(EditorOptions... options);

    /**
     * This method is used to customize UI components in line with Burp's UI style, including font size, colors, table line spacing, etc.
     * The action is performed recursively on any child components of the passed-in component.
     *
     * @param component The component to be customized.
     */
    void applyThemeToComponent(Component component);

    /**
     * This method is used to identify the theme currently being used.
     *
     * @return the current {@link Theme}
     */
    Theme currentTheme();

    /**
     * @return an instance of {@link SwingUtils}
     */
    SwingUtils swingUtils();
}
