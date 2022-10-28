package burp.api.montoya.persistence.support;

import java.util.List;

public interface KeySupport
{
    /**
     * This method is used to list all keys currently mapped for the current
     * persistence environment ({@code Node, Preferences or TemporaryFile})
     *
     * @return List of keys.
     */
    List<String> listKeys();
}
