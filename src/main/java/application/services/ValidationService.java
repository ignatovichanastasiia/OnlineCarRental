package application.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.exceptions.CarUnavailableException;
import application.exceptions.InvalidClientDataException;
import application.exceptions.InvalidCreditCardNumberException;
import application.exceptions.InvalidDriversLicenseNumberException;
import application.exceptions.InvalidEmailException;
import application.exceptions.InvalidIdentityNumberException;
import application.exceptions.InvalidRentalDatesException;
import application.models.Car;
import application.models.Client;

/**
 * The ValidationService class provides static methods for validating various inputs in the application.
 * It includes validations for client data, email format, rental dates (using LocalDate), 
 * and car availability.
 */
public class ValidationService {

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
        // Validate client's name is not null or empty.
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new InvalidClientDataException("Client name cannot be empty.");
        }
        
        // Retrieve the client's email.
        String email = client.getEmail();
        // Validate that the client's email is not null or empty.
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidClientDataException("Client email cannot be empty.");
        }
        
        // Validate the email format using regex.
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidEmailException("Invalid email format: " + email);
        }
    }

    /**
     * Validates the client name.
     *
     * @param name the client name to validate.
     * @throws InvalidClientDataException if the name is null or empty.
     */
    public static void validateName(String name) throws InvalidClientDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidClientDataException("Client name cannot be empty.");
        }
    }

    /**
     * Validates the given email address.
     *
     * @param email the email address to validate.
     * @throws InvalidClientDataException if the email is null or empty.
     * @throws InvalidEmailException if the email format is invalid.
     */
    public static void validateEmail(String email) throws InvalidClientDataException, InvalidEmailException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidClientDataException("Client email cannot be empty.");
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidEmailException("Invalid email format: " + email);
        }
    }

    /**
     * Validates the client phone number.
     *
     * @param phone the client phone number to validate.
     * @throws InvalidClientDataException if the phone is null or empty.
     */
    public static void validatePhone(String phone) throws InvalidClientDataException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidClientDataException("Client phone cannot be empty.");
        }
    }
    
    /**
     * Validates the identity number (Teudat Zehut).
     * <p>
     * This method ensures that:
     * <ul>
     *   <li>The identity number is not null or empty.</li>
     *   <li>The identity number contains only digits.</li>
     *   <li>The identity number is 9 digits long (padding with leading zeros if necessary).</li>
     *   <li>The identity number passes the checksum validation using the standard algorithm.</li>
     * </ul>
     *
     * @param identityNumber the identity number to validate.
     * @throws InvalidIdentityNumberException if the identity number is invalid.
     */
    public static void validateIdentityNumber(String identityNumber) throws InvalidIdentityNumberException {
        if (identityNumber == null || identityNumber.trim().isEmpty()) {
            throw new InvalidIdentityNumberException("Identity number cannot be empty.");
        }
        // Remove any non-digit characters.
        String sanitized = identityNumber.replaceAll("\\D", "");
        
        // Pad with leading zeros if length is less than 9.
        while (sanitized.length() < 9) {
            sanitized = "0" + sanitized;
        }
        if (sanitized.length() != 9) {
            throw new InvalidIdentityNumberException("Identity number must be 9 digits long.");
        }
        
        // Validate using the checksum algorithm.
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(sanitized.charAt(i));
            int multiplier = (i % 2 == 0) ? 1 : 2;
            int product = digit * multiplier;
            if (product > 9) {
                product -= 9;
            }
            sum += product;
        }
        if (sum % 10 != 0) {
            throw new InvalidIdentityNumberException("Invalid identity number.");
        }
    }
    
    /**
     * Validates the credit card number using the Luhn algorithm.
     * <p>
     * This method ensures that:
     * <ul>
     *   <li>The credit card number is not null or empty.</li>
     *   <li>The credit card number contains only numeric characters (after removing spaces).</li>
     *   <li>The credit card number passes the Luhn checksum algorithm.</li>
     * </ul>
     *
     * @param creditCardNumber the credit card number to validate.
     * @throws InvalidCreditCardNumberException if the credit card number is null, empty, non-numeric, or invalid.
     */
    public static void validateCreditCardNumber(String creditCardNumber) throws InvalidCreditCardNumberException {
        if (creditCardNumber == null || creditCardNumber.trim().isEmpty()) {
            throw new InvalidCreditCardNumberException("Credit card number cannot be empty.");
        }
        String sanitized = creditCardNumber.replaceAll("\\s+", "");
        if (!sanitized.matches("\\d+")) {
            throw new InvalidCreditCardNumberException("Credit card number must be numeric.");
        }
        
        int sum = 0;
        boolean alternate = false;
        for (int i = sanitized.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(sanitized.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        if (sum % 10 != 0) {
            throw new InvalidCreditCardNumberException("Invalid credit card number.");
        }
    }
    
    /**
     * Validates the driver's license number.
     * <p>
     * This method checks that:
     * <ul>
     *   <li>The driver's license number is not null or empty.</li>
     *   <li>The driver's license number matches the required format (numeric and 7 to 9 digits).</li>
     * </ul>
     *
     * @param driversLicenseNumber the driver's license number to validate.
     * @throws InvalidDriversLicenseNumberException if the driver's license number is invalid.
     */
    public static void validateDriversLicenseNumber(String driversLicenseNumber) throws InvalidDriversLicenseNumberException {
        if (driversLicenseNumber == null || driversLicenseNumber.trim().isEmpty()) {
            throw new InvalidDriversLicenseNumberException("Driver's license number cannot be empty.");
        }
        String sanitized = driversLicenseNumber.trim();
        if (!sanitized.matches("\\d{7,9}")) {
            throw new InvalidDriversLicenseNumberException("Driver's license number must be numeric and between 7 and 9 digits.");
        }
    }

    /**
     * Validates the rental dates provided by the client.
     * <p>
     * This method performs the following checks:
     * <ul>
     *   <li>Both the start and end dates are not null.</li>
     *   <li>The rental start date is before the rental end date.</li>
     *   <li>The rental start date is not in the past compared to today's date.</li>
     * </ul>
     *
     * @param startDate the rental start date.
     * @param endDate the rental end date.
     * @throws InvalidRentalDatesException if any of the date validations fail.
     */
    public static void validateRentalDates(Date startDate, Date endDate)
            throws InvalidRentalDatesException {
        // Ensure both dates are not null.
        if (startDate == null || endDate == null) {
            throw new InvalidRentalDatesException("Rental dates cannot be null.");
        }
        // Ensure the start date is before the end date.
        if (endDate.isBefore(startDate)) {
            throw new InvalidRentalDatesException("The rental start date must be before the end date.");
        }
        // Ensure the start date is not in the past relative to today.
        if (startDate.isBefore(LocalDate.now())) {
            throw new InvalidRentalDatesException("The rental start date cannot be in the past.");
        }
    }

    /**
     * Validates that a car is available for rental.
     * <p>
     * This method checks that the car is not null and its availability status is true.
     *
     * @param car the Car object to be validated.
     * @throws CarUnavailableException if the car is null or not available.
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