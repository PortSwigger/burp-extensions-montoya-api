package burp.api.montoya.bambda;

/**
 * Provides access to functionality related to Bambdas.
 */
public interface Bambda
{
    /**
     * This method can be used to import a Bambda.
     * If a script with the same ID already exists in the library, it will be replaced.
     *
     * @param script the Bambda script to import
     *
     * @return The {@link BambdaImportResult} containing the result of importing the Bambda.
     */
    BambdaImportResult importBambda(String script);
}
