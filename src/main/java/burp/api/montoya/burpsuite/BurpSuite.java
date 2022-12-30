/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.burpsuite;

import burp.api.montoya.core.Version;

import java.util.List;

/**
 * This interface provides access to functionality related to
 * the Burp Suite application.
 */
public interface BurpSuite
{
    /**
     * This method retrieves information about the version of Burp in which the
     * extension is running. It can be used by extensions to dynamically adjust
     * their behavior depending on the functionality and APIs supported by the
     * current version.
     *
     * @return The Burp {@link Version}.
     */
    Version version();

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
     *
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
     * This method can be used to shut down Burp programmatically.
     *
     * @param options The shutdown options for shutting down Burp
     *                programmatically. For example {@link ShutdownOptions#PROMPT_USER} will
     *                display a dialog to the user allowing them to confirm or cancel the
     *                shut down.
     */
    void shutdown(ShutdownOptions... options);
}
