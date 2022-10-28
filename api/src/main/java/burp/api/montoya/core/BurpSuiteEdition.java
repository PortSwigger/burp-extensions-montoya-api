package burp.api.montoya.core;

public enum BurpSuiteEdition
{
    PROFESSIONAL("Professional"),
    COMMUNITY_EDITION("Community Edition"),
    ENTERPRISE_EDITION("Enterprise Edition");

    public final String name;

    BurpSuiteEdition(String name)
    {
        this.name = name;
    }
}
