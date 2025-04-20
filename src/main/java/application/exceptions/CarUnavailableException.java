

package application.exceptions;

/**
 * Thrown when a car is not available for rental.
 */
public class CarUnavailableException extends Exception {

<<<<<<< HEAD
	/**
	 * Constructs a new CarUnavailableException with {@code null} as its detail
	 * message.
	 */
	public CarUnavailableException() {
		super();
	}
=======

    /**
     * Constructs a new CarUnavailableException with {@code null} as its detail message.
     */
    public CarUnavailableException() {
        super();
    }
>>>>>>> branch 'Anat' of https://github.com/Johntarakay/OnlineCarRental.git

	/**
	 * Constructs a new CarUnavailableException with the specified detail message.
	 *
	 * @param message the detail message.
	 */
	public CarUnavailableException(String message) {
		super(message);
	}

	/**
	 * Constructs a new CarUnavailableException with the specified detail message
	 * and cause.
	 *
	 * @param message the detail message.
	 * @param cause   the cause of the exception.
	 */
	public CarUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}

<<<<<<< HEAD
	/**
	 * Constructs a new CarUnavailableException with the specified cause.
	 *
	 * @param cause the cause of the exception.
	 */
	public CarUnavailableException(Throwable cause) {
		super(cause);
	}
=======
    /**
     * Constructs a new CarUnavailableException with the specified cause.
     *
     * @param cause the cause of the exception.
     */
    public CarUnavailableException(Throwable cause) {
        super(cause);
    }

>>>>>>> branch 'Anat' of https://github.com/Johntarakay/OnlineCarRental.git
}

