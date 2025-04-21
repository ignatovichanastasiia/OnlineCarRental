package application.exceptions;

/**
 * Thrown when the identity number (Teudat Zehut) is invalid.
 */
public class InvalidIdentityNumberException extends Exception {
    public InvalidIdentityNumberException(String message) {
        super(message);
    }
}