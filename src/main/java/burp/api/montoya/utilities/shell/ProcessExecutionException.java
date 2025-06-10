package burp.api.montoya.utilities.shell;

/**
 * Thrown to indicate that an error occurred while executing a process with {@link ShellUtils}.
 */
public class ProcessExecutionException extends RuntimeException
{
    public ProcessExecutionException(String message)
    {
        super(message);
    }

    public ProcessExecutionException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
