package burp.api.montoya.organizer;

/**
 * The possible statuses of an Organizer item.
 */
public enum OrganizerItemStatus
{
    UNKNOWN("Unknown"),
    NEW("New"),
    IN_PROGRESS("In progress"),
    POSTPONED("Postponed"),
    DONE("Done"),
    IGNORED("Ignored");

    private final String displayName;

    OrganizerItemStatus(String displayName)
    {
        this.displayName = displayName;
    }

    /**
     * Returns the user-friendly label for this status, as displayed in Burp Organizer.
     *
     * @return The display name of the status.
     */
    public String displayName()
    {
        return this.displayName;
    }
}
