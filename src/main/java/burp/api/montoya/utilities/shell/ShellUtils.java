package burp.api.montoya.utilities.shell;

/**
 * Provides utilities for launching processes from the OS shell.
 */
public interface ShellUtils
{
    /**
     * Executes the specified command using the default execution options. Splits each parameter on whitespace characters. If you require whitespace, consider using {@link ShellUtils#execute(String...)}.
     * <p>
     * <b>Warning:</b> Avoid using this with arbitrary input. If you are accepting arbitrary input, consider using {@link ShellUtils#execute(String...)} instead to minimize the risk of OS command injection.
     * </p>
     *
     * @param command The command and its parameters, separated by whitespace.
     *
     * @return The output produced by the command.
     */
    String dangerouslyExecute(String command);

    /**
     * Executes the specified command using the specified execution options. Splits each parameter on whitespace characters. If you require whitespace, consider using {@link ShellUtils#execute(ExecuteOptions, String...)}.
     * <p>
     * <b>Warning:</b> Avoid using this with arbitrary input. If you are accepting arbitrary input, consider using {@link ShellUtils#execute(String...)} instead to minimize the risk of OS command injection.
     * </p>
     *
     * @param options The options that control how the command is executed.
     * @param command The command and its parameters, separated by whitespace.
     *
     * @return The output produced by the command.
     */
    String dangerouslyExecute(ExecuteOptions options, String command);

    /**
     * Executes the specified command using the default execution options.
     *
     * @param command The command and its parameters, specified as separate strings.
     *
     * @return The output produced by this command.
     */
    String execute(String... command);

    /**
     * Executes the specified command using the specified execution options.
     *
     * @param options The options that control how the command is executed.
     * @param command The command and its parameters, specified as separate strings.
     *
     * @return The output produced by this command.
     */
    String execute(ExecuteOptions options, String... command);
}
