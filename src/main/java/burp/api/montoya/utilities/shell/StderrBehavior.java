package burp.api.montoya.utilities.shell;

/**
 * Defines how stderr (standard error) output is handled.
 */
public enum StderrBehavior
{
    /**
     * Merge stderr output with the stdout stream.
     */
    MERGE,

    /**
     * Discard all stderr output.
     */
    DISCARD
}
