package application.exceptions;

/**
 * Thrown when the driver's license number is invalid.
 */
public class InvalidDriversLicenseNumberException extends Exception {
    public InvalidDriversLicenseNumberException(String message) {
        super(message);
    }
}