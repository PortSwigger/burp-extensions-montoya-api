package burp.api.montoya.utilities.shell;

/**
 * Defines how non-zero exit codes are handled.
 */
public enum ExitCodeBehavior
{
    /**
     * Throw an exception if the process returns a non-zero exit code.
     */
    FAIL_ON_NON_ZERO,

    /**
     * Silently ignore non-zero exit codes.
     */
    ALLOW_NON_ZERO
}
