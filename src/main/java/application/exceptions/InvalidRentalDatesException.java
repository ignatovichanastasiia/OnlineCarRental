package application.exceptions;

/**
 * Исключение, выбрасываемое при некорректных датах аренды.
 */
public class InvalidRentalDatesException extends Exception {
    public InvalidRentalDatesException(String message) {
        super(message);
    }
}