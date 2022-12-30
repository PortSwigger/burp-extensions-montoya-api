package burp.api.montoya.core;

/**
 * Editions of Burp Suite.
 */
public enum BurpSuiteEdition
{
    /**
     * Burp Suite professional edition
     */
    PROFESSIONAL("Professional"),
    /**
     * Burp Suite community edition
     */
    COMMUNITY_EDITION("Community Edition"),
    /**
     * Burp Suite enterprise edition
     */
    ENTERPRISE_EDITION("Enterprise Edition");

    private final String displayName;

    BurpSuiteEdition(String displayName)
    {
        this.displayName = displayName;
    }

    /**
     * @return displayName for this edition of Burp Suite.
     */
    public String displayName()
    {
        return displayName;
    }
}
