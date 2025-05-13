package burp.api.montoya.ui.settings;

import javax.swing.JComponent;
import java.util.Set;

import static java.util.Collections.emptySet;

/**
 * Represents a custom settings panel displayed in Burp's Settings dialog.
 */
public interface SettingsPanel
{
    /**
     * @return the UI component to display in the Settings dialog.
     */
    JComponent uiComponent();

    /**
     * A set of keywords used by the Settings search function to help users find this panel.
     *
     * @return the set of keywords.
     */
    default Set<String> keywords()
    {
        return emptySet();
    }
}
