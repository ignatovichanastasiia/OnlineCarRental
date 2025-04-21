package application.exceptions;

/**
 * Thrown when the credit card number is invalid.
 */
public class InvalidCreditCardNumberException extends Exception {
    public InvalidCreditCardNumberException(String message) {
        super(message);
    }
}