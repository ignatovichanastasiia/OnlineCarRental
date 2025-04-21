package application.exceptions;

public class InvalidShopDataException extends Exception {
    public InvalidShopDataException() {
        super();
    }

    public InvalidShopDataException(String message) {
        super(message);
    }

    public InvalidShopDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidShopDataException(Throwable cause) {
        super(cause);
    }
}