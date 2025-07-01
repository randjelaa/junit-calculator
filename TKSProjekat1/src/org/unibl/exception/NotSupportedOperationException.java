package org.unibl.exception;

/**
 * Exception thrown when an unsupported operator is used.
 */
public class NotSupportedOperationException extends RuntimeException {

    /**
     * Constructs a new NotSupportedOperationException with the specified detail message.
     * 
     * @param message the detail message
     */
    public NotSupportedOperationException(String message) {
        super(message);
    }
}
