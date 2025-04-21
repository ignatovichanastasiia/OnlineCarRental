package application.services;

import java.time.LocalDate;

import application.exceptions.CarUnavailableException;
import application.exceptions.InvalidClientDataException;
import application.exceptions.InvalidRentalDatesException;
import application.exceptions.InvalidEmailException;

import application.models.Car;
import application.models.Client;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * The ValidationService class provides static methods for validating various inputs in the application.
 * It includes validation for client data, rental dates (using LocalDate), and car availability.
 */
public class ValidationService {

    import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Regular expression for validating an email address.
private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
// Compiled Pattern object with case-insensitive flag.
private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

/**
 * Combined method that validates both the client data and the email format.
 * <p>
 * This method performs the following checks:
 * <ul>
 *   <li>The client's name must not be null or empty.</li>
 *   <li>The client's email must not be null or empty.</li>
 *   <li>The client's email must match the specified email pattern.</li>
 * </ul>
 *
 * @param client the Client object whose data is to be validated.
 * @throws InvalidClientDataException if the client name or email is missing.
 * @throws InvalidEmailException if the email format is invalid.
 */
public static void validateClientData(Client client) throws InvalidClientDataException, InvalidEmailException {
    // Check that the client's name is not null or empty.
    if (client.getName() == null || client.getName().trim().isEmpty()) {
        throw new InvalidClientDataException("Client name cannot be empty.");
    }
    
    // Retrieve and validate the client's email.
    String email = client.getEmail();
    if (email == null || email.trim().isEmpty()) {
        throw new InvalidClientDataException("Client email cannot be empty.");
    }
    
    // Validate the email format using the regex pattern.
    Matcher matcher = EMAIL_PATTERN.matcher(email);
    if (!matcher.matches()) {
        throw new InvalidEmailException("Invalid email format: " + email);
    }
}
    /**
     * Validates the rental dates provided by the client.
     * <p>
     * This method checks that:
     * <ul>
     *   <li>Both the start and end dates are not null.</li>
     *   <li>The rental start date is before the rental end date.</li>
     *   <li>The rental start date is not in the past compared to today.</li>
     * </ul>
     *
     * @param localDateFrom the rental start date obtained from the DatePicker.
     * @param localDateTo   the rental end date obtained from the DatePicker.
     * @throws InvalidRentalDatesException if any of the date validations fail.
     */
    public static void validateRentalDates(LocalDate localDateFrom, LocalDate localDateTo) throws InvalidRentalDatesException {
        // Check that both dates are not null.
        if (localDateFrom == null || localDateTo == null) {
            throw new InvalidRentalDatesException("Rental dates cannot be null.");
        }
        // Ensure that the start date is before the end date.
        if (localDateTo.isBefore(localDateFrom)) {
            throw new InvalidRentalDatesException("The rental start date must be before the end date.");
        }
        // Ensure that the start date is not in the past relative to today's date.
        if (localDateFrom.isBefore(LocalDate.now())) {
            throw new InvalidRentalDatesException("The rental start date cannot be in the past.");
        }
    }

    /**
     * Validates that a car is available for rental.
     * <p>
     * This method checks that the car is not null and its availability status is true.
     *
     * @param car the Car object to be validated.
     * @throws CarUnavailableException if the car is null or not available for rental.
     */
    public static void validateCarAvailability(Car car) throws CarUnavailableException {
        if (car == null) {
            throw new CarUnavailableException("Car not found.");
        }
        if (!car.isAvailable()) {
            throw new CarUnavailableException("Car is not available for rental.");
        }
    }
}
