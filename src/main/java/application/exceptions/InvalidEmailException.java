package application.exceptions;


/**
 * Custom exception thrown when an email address is invalid.
 */
public class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}
