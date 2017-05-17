package br.com.oobj.nexus.exception;

/**
 * Base exception.
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 09/03/2017
 */
public class NexusServiceException extends Exception {

    public NexusServiceException() {
    }

    public NexusServiceException(String message) {
        super(message);
    }

    public NexusServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NexusServiceException(Throwable cause) {
        super(cause);
    }
}
