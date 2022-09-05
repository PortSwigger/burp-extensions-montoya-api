/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.misc;

import burp.api.montoya.core.Registration;
import burp.api.montoya.core.Version;

import java.util.List;

/**
 * This interface provides access to Miscellaneous functionality related to
 * Burp.
 */
public interface Misc
{
    /**
     * This method is used to set the display name for the current extension,
     * which will be displayed within the user interface for the Extender tool.
     *
     * @param extensionName the name of the extension
     */
    void setExtensionName(String extensionName);

    /**
     * This method retrieves the absolute path name of the file from which the
     * current extension was loaded.
     *
     * @return The absolute path name of the file from which the current
     * extension was loaded.
     */
    String extensionFilename();

    /**
     * This method determines whether the current extension was loaded as a BApp
     * (a Burp App from the BApp Store).
     *
     * @return Returns {@code true} if the current extension was loaded as
     * a BApp.
     */
    boolean isLoadedFromBappStore();

    /**
     * This method retrieves information about the version of Burp in which the
     * extension is running. It can be used by extensions to dynamically adjust
     * their behavior depending on the functionality and APIs supported by the
     * current version.
     *
     * @return The Burp {@link Version}.
     */
    Version burpVersion();

    /**
     * This method causes Burp to save its current project-level configuration
     * in JSON format. This is the same format that can be saved and loaded via
     * the Burp user interface. To include only certain sections of the
     * configuration, you can optionally supply the path to each section that
     * should be included, for example: "project_options.connections". If no
     * paths are provided, then the entire configuration will be saved.
     *
     * @param paths A list of Strings representing the path to each
     *              configuration section that should be included.
     * @return A String representing the current configuration in JSON format.
     */
    String exportProjectOptionsAsJson(String... paths);

    /**
     * This method causes Burp to load a new project-level configuration from
     * the JSON String provided. This is the same format that can be saved and
     * loaded via the Burp user interface. Partial configurations are
     * acceptable, and any settings not specified will be left unmodified.
     * <p>
     * Any user-level configuration options contained in the input will be
     * ignored.
     *
     * @param json A JSON String containing the new configuration.
     */
    void importProjectOptionsFromJson(String json);

    /**
     * This method returns the command line arguments that were passed to Burp
     * on startup.
     *
     * @return The command line arguments that were passed to Burp on startup.
     */
    List<String> commandLineArguments();

    /**
     * This method is used to unload the extension from Burp Suite.
     */
    void unloadExtension();

    /**
     * This method is used to register a handler which will be notified of
     * changes to the extension's state. <b>Note:</b> Any extensions that start
     * background threads or open system resources (such as files or database
     * connections) should register a listener and terminate threads / close
     * resources when the extension is unloaded.
     *
     * @param handler An object created by the extension that implements the
     *                {@link ExtensionUnloadHandler} interface.
     * @return The {@link Registration} for the handler.
     */
    Registration registerExtensionUnloadHandler(ExtensionUnloadHandler handler);

    /**
     * This method can be used to shut down Burp programmatically.
     *
     * @param options The shutdown options for shutting down Burp
     *                programmatically. For example {@link ShutdownOptions#PROMPT_USER} will
     *                display a dialog to the user allowing them to confirm or cancel the
     *                shut down.
     */
    void shutdownBurp(ShutdownOptions... options);
}
