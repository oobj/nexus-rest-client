package br.com.oobj.nexus.exception;

/**
 * Represents the scenario when an artifact is not found on Nexus.
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 10/03/2017
 */
public class ArtifactNotFoundException extends NexusServiceException {

    public ArtifactNotFoundException() {
    }

    public ArtifactNotFoundException(String message) {
        super(message);
    }

    public ArtifactNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArtifactNotFoundException(Throwable cause) {
        super(cause);
    }
}
