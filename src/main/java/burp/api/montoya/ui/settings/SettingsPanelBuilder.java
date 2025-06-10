package burp.api.montoya.ui.settings;

import java.util.Collection;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Used to build an instance of {@link SettingsPanelWithData}.
 */
public interface SettingsPanelBuilder
{
    /**
     * Sets the type of data persistence.
     *
     * @param persistence type of data persistence.
     *
     * @return instance of the builder.
     */
    SettingsPanelBuilder withPersistence(SettingsPanelPersistence persistence);

    /**
     * Sets the title to be displayed within Settings panel.
     * If no title is set then the extension name will be used.
     *
     * @param title the title.
     *
     * @return instance of the builder.
     */
    SettingsPanelBuilder withTitle(String title);

    /**
     * Sets the description to be displayed within Settings panel.
     *
     * @param description the description.
     *
     * @return instance of the builder.
     */
    SettingsPanelBuilder withDescription(String description);

    /**
     * Used to add a {@link SettingsPanelSetting} to the Settings panel.
     *
     * @param entry the entry.
     *
     * @return instance of the builder.
     */
    SettingsPanelBuilder withSetting(SettingsPanelSetting entry);

    /**
     * Used to add multiple {@link SettingsPanelSetting} to the Settings panel.
     *
     * @param entries the entries.
     *
     * @return instance of the builder.
     */
    SettingsPanelBuilder withSettings(SettingsPanelSetting... entries);

    /**
     * A set of keywords used by the Settings search function to help users find the Settings panel.
     *
     * @return instance of the builder.
     */
    SettingsPanelBuilder withKeywords(String... keywords);

    /**
     * A set of keywords used by the Settings search function to help users find the Settings panel.
     *
     * @return instance of the builder.
     */
    SettingsPanelBuilder withKeywords(Collection<String> keywords);

    /**
     * Used to build the settings panel.
     *
     * @return the settings panel
     */
    SettingsPanelWithData build();

    /**
     * Used to obtain an instance of the SettingsPanelBuilder.
     *
     * @return an instance of the SettingsPanelBuilder.
     */
    static SettingsPanelBuilder settingsPanel()
    {
        return FACTORY.settingsPanel();
    }
}
