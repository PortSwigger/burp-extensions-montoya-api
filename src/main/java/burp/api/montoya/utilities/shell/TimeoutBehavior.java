package burp.api.montoya.utilities.shell;

/**
 * Defines how process timeout should be handled.
 */
public enum TimeoutBehavior
{
    /**
     * Throw an exception if the process times out.
     */
    FAIL_ON_TIMEOUT,

    /**
     * Silently ignore process execution timeout.
     */
    ALLOW_TIMEOUT
}
