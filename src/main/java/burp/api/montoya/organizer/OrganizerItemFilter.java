package burp.api.montoya.organizer;

/**
 * Defines a filter for selecting {@link OrganizerItem} items returned by
 * {@link Organizer#items(OrganizerItemFilter)}.
 */
public interface OrganizerItemFilter
{
    /**
     * Determines whether the item should be included in the filtered results.
     * The method is called for each item in Organizer.
     *
     * @param item The {@link OrganizerItem} to evaluate.
     *
     * @return {@code true} if the item should be included in the results; {@code false} otherwise.
     */
    boolean matches(OrganizerItem item);
}
