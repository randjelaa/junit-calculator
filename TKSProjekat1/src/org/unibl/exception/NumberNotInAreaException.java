package org.unibl.exception;

/**
 * Exception thrown when a number is not in the valid range.
 */
public class NumberNotInAreaException extends RuntimeException {
	
	/**
     * Constructs a new NumberNotInAreaException with the specified detail message.
     * 
     * @param message the detail message
     */
    public NumberNotInAreaException(String message) {
        super(message);
    }
}