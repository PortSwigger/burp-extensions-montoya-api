package burp.api.montoya.utilities.shell;

import java.time.Duration;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * This interface defines options for executing a process.
 */
public interface ExecuteOptions
{
    /**
     * Sets the maximum duration the process is allowed to run in seconds. Defaults to 10 seconds. To disable the timeout, use 0.
     * <p>
     * Use {@link ExecuteOptions#withTimeoutBehavior} to define the behavior regarding whether timeouts should throw an exception or be silently ignored.
     *
     * @param seconds The timeout duration, in seconds. Use 0 for unlimited.
     *
     * @return An {@link ExecuteOptions} instance with the updated timeout.
     */
    ExecuteOptions withTimeout(int seconds);

    /**
     * Sets the maximum duration the process is allowed to run. Defaults to 10 seconds. To disable the timeout, use {@link Duration#ZERO}.
     * <p>
     * Use {@link ExecuteOptions#withTimeoutBehavior} to define the behavior regarding whether timeouts should throw an exception or be silently ignored.
     *
     * @param duration The timeout duration. Use {@code Duration.ZERO} for unlimited.
     *
     * @return An {@link ExecuteOptions} instance with the updated timeout.
     */
    ExecuteOptions withTimeout(Duration duration);

    /**
     * Sets the behavior regarding whether timeouts should throw an exception or be silently ignored. Defaults to {@link TimeoutBehavior#FAIL_ON_TIMEOUT}.
     *
     * @param behavior The timeout behavior.
     *
     * @return An {@link ExecuteOptions} instance with the updated timeout behavior.
     */
    ExecuteOptions withTimeoutBehavior(TimeoutBehavior behavior);

    /**
     * Sets how stderr (standard error) output is handled. Defaults to {@link StderrBehavior#DISCARD}.
     *
     * @param behavior The stderr handling behavior.
     *
     * @return An {@link ExecuteOptions} instance with the updated stderr behavior.
     */
    ExecuteOptions withStderrBehavior(StderrBehavior behavior);

    /**
     * Sets whether non-zero exit codes throw an exception, or are silently ignored. Defaults to {@link ExitCodeBehavior#FAIL_ON_NON_ZERO}.
     *
     * @param behavior The non-zero exit code handling behavior.
     *
     * @return An {@link ExecuteOptions} instance with the updated non-zero exit behavior.
     */
    ExecuteOptions withExitCodeBehavior(ExitCodeBehavior behavior);

    /**
     * Defines an environment variable to be used by the process.
     *
     * @param name  The variable name.
     * @param value The variable value.
     *
     * @return An {@link ExecuteOptions} instance with the added variable.
     */
    ExecuteOptions withEnvironmentVariable(String name, String value);


    /**
     * Creates a new {@link ExecuteOptions} instance.
     *
     * @return An {@link ExecuteOptions} instance with default settings.
     */
    static ExecuteOptions executeOptions()
    {
        return FACTORY.executeOptions();
    }
}
