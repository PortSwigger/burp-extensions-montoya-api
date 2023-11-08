package burp.api.montoya.scanner.bchecks;

/**
 * Provides access to functionality related to BChecks.
 */
public interface BChecks
{
    /**
     * This method can be used to import a BCheck.
     *
     * @param script the BCheck script to import
     *
     * @return The {@link BCheckImportResult} which contains the result of importing the BCheck.
     */
    BCheckImportResult importBCheck(String script);
}
