package burp.api.montoya.collaborator;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Secret key that is associated with a {@link CollaboratorClient}
 */
public interface SecretKey
{
    /**
     * Secret key in string form.
     *
     * @return The base64 encoded secret key.
     */
    @Override
    String toString();

    /**
     * Create an instance of {@link SecretKey} which
     * you will be able to use to restore a previously created {@link CollaboratorClient}
     * with the {@link Collaborator#restoreClient(SecretKey)} method.
     *
     * @param encodedKey The base64 encoded raw secret key.
     *
     * @return An instance of {@link SecretKey} wrapping the provided secret key.
     */
    static SecretKey secretKey(String encodedKey)
    {
        return FACTORY.secretKey(encodedKey);
    }
}
