package burp.api.montoya.bambda;

import java.util.List;

/**
 * The result of importing a Bambda
 */
public interface BambdaImportResult
{
    /**
     * The status of an imported Bambda
     */
    enum Status
    {
        LOADED_WITHOUT_ERRORS,
        LOADED_WITH_ERRORS
    }

    /**
     * The status of the Bambda after import
     *
     * @return the status
     */
    Status status();

    /**
     * @return a list of error messages, or an empty list if the script was imported without errors.
     */
    List<String> importErrors();
}
