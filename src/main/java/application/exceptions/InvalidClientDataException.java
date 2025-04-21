package application.exceptions;

/**
 * Exception thrown when client data is invalid.
 */
public class InvalidClientDataException extends Exception {

	/**
	 * Constructs a new InvalidClientDataException with {@code null} as its detail
	 * message.
	 */
	public InvalidClientDataException() {
		super();
	}

	/**
	 * Constructs a new InvalidClientDataException with the specified detail
	 * message.
	 *
	 * @param message the detail message.
	 */
	public InvalidClientDataException(String message) {
		super(message);
	}

	/**
	 * Constructs a new InvalidClientDataException with the specified detail message
	 * and cause.
	 *
	 * @param message the detail message.
	 * @param cause   the cause of the exception.
	 */
	public InvalidClientDataException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new InvalidClientDataException with the specified cause.
	 *
	 * @param cause the cause of the exception.
	 */
	public InvalidClientDataException(Throwable cause) {
		super(cause);
	}

}

