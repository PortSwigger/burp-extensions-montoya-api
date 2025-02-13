package burp.api.montoya.project;

/**
 * Provides access to functionality relating to the project.
 */
public interface Project
{
    /**
     * Retrieves the name of the current project.
     *
     * @return name
     */
    String name();

    /**
     * Retrieves the unique identifier of the current project.
     *
     * @return identifier
     */
    String id();
}
