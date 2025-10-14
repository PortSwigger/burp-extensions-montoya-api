package burp.api.montoya.ui.settings;

import java.util.List;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Represents an entry within a Settings panel.
 */
public interface SettingsPanelSetting
{
    /**
     * Used to build a text field that only accepts integer values.
     *
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting integerSetting(String name)
    {
        return FACTORY.integerSetting(name);
    }

    /**
     * Used to build a text field that only accepts integer values and has an initial value.
     * If a value with the same name has been previously persisted value then this will be used
     * instead of the default value.
     *
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     * @param defaultValue the initial value.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting integerSetting(String name, int defaultValue)
    {
        return FACTORY.integerSetting(name, defaultValue);
    }

    /**
     * Used to build a text field with a description that only accepts integer values.
     *
     * @param description the description displayed for this setting.
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting integerSetting(String description, String name)
    {
        return FACTORY.integerSetting(description, name);
    }

    /**
     * Used to build a text field with a description that only accepts integer values and has an initial value.
     * If a value with the same name has been previously persisted value then this will be used
     * instead of the default value.
     *
     * @param description the description displayed for this setting.
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     * @param defaultValue the initial value.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting integerSetting(String description, String name, int defaultValue)
    {
        return FACTORY.integerSetting(description, name, defaultValue);
    }

    /**
     * Used to build a checkbox.
     *
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting booleanSetting(String name)
    {
        return FACTORY.booleanSetting(name);
    }

    /**
     * Used to a checkbox that is set via an initial selection state.
     * If a value with the same name has been previously persisted state then this will be used
     * instead of the default state.
     *
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     * @param defaultValue the initial value.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting booleanSetting(String name, boolean defaultValue)
    {
        return FACTORY.booleanSetting(name, defaultValue);
    }

    /**
     * Used to build a checkbox with a description.
     *
     * @param description the description displayed for this setting.
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting booleanSetting(String description, String name)
    {
        return FACTORY.booleanSetting(description, name);
    }

    /**
     * Used to build a checkbox with a description that is set via an initial selection state.
     * If a value with the same name has been previously persisted state then this will be used
     * instead of the default state.
     *
     * @param description the description displayed for this setting.
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     * @param defaultValue the initial value.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting booleanSetting(String description, String name, boolean defaultValue)
    {
        return FACTORY.booleanSetting(description, name, defaultValue);
    }

    /**
     * Used to build a text field.
     *
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting stringSetting(String name)
    {
        return FACTORY.stringSetting(name);
    }

    /**
     * Used to build a text field that has an initial value.
     * If a value with the same name has been previously persisted value then this will be used
     * instead of the default value.
     *
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     * @param defaultValue the initial value.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting stringSetting(String name, String defaultValue)
    {
        return FACTORY.stringSetting(name, defaultValue);
    }

    /**
     * Used to build a text field with a description that has an initial value.
     * If a value with the same name has been previously persisted value then this will be used
     * instead of the default value.
     *
     * @param description the description displayed for this setting.
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     * @param defaultValue the initial value.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting stringSetting(String description, String name, String defaultValue)
    {
        return FACTORY.stringSetting(description, name, defaultValue);
    }

    /**
     * Used to build a combo box.
     *
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     * @param values the values to use within the combo box.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting listSetting(String name, String... values)
    {
        return FACTORY.listSetting(name, values);
    }

    /**
     * Used to build a combo box that has a specific initial value selected.
     * If a value with the same name has been previously persisted value then this will be used
     * instead of the default value.
     *
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     * @param values the values to use within the combo box.
     * @param defaultValue the initial value.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting listSetting(String name, List<String> values, String defaultValue)
    {
        return FACTORY.listSetting(name, values, defaultValue);
    }

    /**
     * Used to build a combo box with a description that has a specific initial value selected.
     * If a value with the same name has been previously persisted value then this will be used
     * instead of the default value.
     *
     * @param description the description displayed for this setting.
     * @param name the name used to access the associated data via {@link SettingsPanelWithData}.
     * @param values the values to use within the combo box.
     * @param defaultValue the initial value.
     *
     * @return the SettingsPanelSetting.
     */
    static SettingsPanelSetting listSetting(String description, String name, List<String> values, String defaultValue)
    {
        return FACTORY.listSetting(description, name, values, defaultValue);
    }
}
