package org.unibl.exception;

/**
 * Exception thrown when division by zero is attempted.
 */
public class DivisionByZeroException extends RuntimeException {

    /**
     * Constructs a new DivisionByZeroException with the specified detail message.
     * 
     * @param message the detail message
     */
    public DivisionByZeroException(String message) {
        super(message);
    }
}
