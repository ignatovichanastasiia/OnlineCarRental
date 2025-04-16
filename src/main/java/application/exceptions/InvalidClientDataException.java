package application.exceptions;

/**
 * Исключение в случае некорректных данных клиента.
 */
public class InvalidClientDataException extends Exception {
    public InvalidClientDataException(String message) {
        super(message);
    }
}