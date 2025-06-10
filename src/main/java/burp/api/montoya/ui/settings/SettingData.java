package burp.api.montoya.ui.settings;

/**
 * This interface is used to retrieve data from a {@link SettingsPanel}
 * created using the {@link SettingsPanelBuilder}.
 */
public interface SettingData
{
    /**
     * @param name of the setting value.
     *
     * @return the setting value or empty string if none exists.
     */
    String getString(String name);

    /**
     * @param name of the setting value.
     *
     * @return the setting value or false if none exists.
     */
    boolean getBoolean(String name);

    /**
     * @param name of the setting value.
     *
     * @return setting value or 0 if none exists.
     */
    int getInteger(String name);
}
