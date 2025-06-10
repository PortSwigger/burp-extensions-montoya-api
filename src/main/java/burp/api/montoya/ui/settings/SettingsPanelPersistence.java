package burp.api.montoya.ui.settings;

/**
 * Used to define the persistence behavior of {@link SettingsPanelWithData}.
 */
public enum SettingsPanelPersistence
{
    /**
     * Settings value are held in memory and not saved when Burp closes.
     */
    NONE,

    /**
     * Settings are saved within the current project file.
     */
    PROJECT_SETTINGS,

    /**
     * Settings are saved within user data.
     */
    USER_SETTINGS
}
