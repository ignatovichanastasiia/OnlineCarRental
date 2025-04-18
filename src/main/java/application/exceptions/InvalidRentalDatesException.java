package application.exceptions;

/**
 * Exception thrown when rental dates are invalid.
 */
public class InvalidRentalDatesException extends Exception {

    /**
     * Constructs a new InvalidRentalDatesException with {@code null} as its detail message.
     */
    public InvalidRentalDatesException() {
        super();
    }

    /**
     * Constructs a new InvalidRentalDatesException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidRentalDatesException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidRentalDatesException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public InvalidRentalDatesException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new InvalidRentalDatesException with the specified cause.
     *
     * @param cause the cause of the exception.
     */
    public InvalidRentalDatesException(Throwable cause) {
        super(cause);
    }
}