package application.services;

import java.time.LocalDate; // Используется там, где требуется
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.exceptions.CarUnavailableException;
import application.exceptions.InvalidClientDataException;
import application.exceptions.InvalidCreditCardNumberException;
import application.exceptions.InvalidDriversLicenseNumberException;
import application.exceptions.InvalidEmailException;
import application.exceptions.InvalidIdentityNumberException;
import application.exceptions.InvalidRentalDatesException;
import application.exceptions.InvalidShopDataException;
import application.models.Car;
import application.models.Client;

/**
 * The ValidationService class provides various static methods to validate client data,
 * identity numbers, credit card details, driver's license information, rental dates,
 * car availability, and shop data.
 */
public class ValidationService {

    // Email regex pattern to validate email addresses.  
    private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    /**
     * Validates the client data.
     *
     * @param client The Client object to be validated.
     * @throws InvalidClientDataException if the client's name or email is empty.
     * @throws InvalidEmailException if the email format is invalid.
     */
    public static void validateClientData(Client client) throws InvalidClientDataException, InvalidEmailException {
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new InvalidClientDataException("Client name cannot be empty.");
        }
        
        String email = client.getEmail();
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidClientDataException("Client email cannot be empty.");
        }
        
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidEmailException("Invalid email format: " + email);
        }
    }

    /**
     * Validates that the given name is not null or empty.
     *
     * @param name The name to validate.
     * @throws InvalidClientDataException if the name is null or empty.
     */
    public static void validateName(String name) throws InvalidClientDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidClientDataException("Client name cannot be empty.");
        }
    }

    /**
     * Validates the provided email string.
     *
     * @param email The email to validate.
     * @throws InvalidClientDataException if the email is null or empty.
     * @throws InvalidEmailException if the email doesn't match the required format.
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
     * Validates that the provided phone number is not null or empty.
     *
     * @param phone The phone number to validate.
     * @throws InvalidClientDataException if the phone number is null or empty.
     */
    public static void validatePhone(String phone) throws InvalidClientDataException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidClientDataException("Client phone cannot be empty.");
        }
    }

    /**
     * Validates the given identity number.
     *
     * This method removes all non-digit characters, pads with leading zeros to 9 digits if necessary,
     * and then uses a checksum algorithm to validate the identity number.
     *
     * @param identityNumber The identity number to validate.
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
     *
     * @param creditCardNumber The credit card number to validate.
     * @throws InvalidCreditCardNumberException if the credit card number is empty, non-numeric,
     *         or fails the Luhn checksum.
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
     * Validates that both first name and last name are not null or empty.
     *
     * @param firstName The client's first name.
     * @param lastName The client's last name.
     * @throws InvalidClientDataException if either first name or last name is empty.
     */
    public static void validateName(String firstName, String lastName) throws InvalidClientDataException {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidClientDataException("Client first name cannot be empty.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidClientDataException("Client last name cannot be empty.");
        }
    }

    /**
     * Validates the card CVV.
     *
     * @param cardCvv The card CVV to validate.
     * @throws InvalidClientDataException if the CVV is empty or does not consist of 3 or 4 digits.
     */
    public static void validateCvv(String cardCvv) throws InvalidClientDataException {
        if (cardCvv == null || cardCvv.trim().isEmpty()) {
            throw new InvalidClientDataException("Card CVV cannot be empty.");
        }
        if (!cardCvv.matches("\\d{3,4}")) {
            throw new InvalidClientDataException("Card CVV must be 3 or 4 digits.");
        }
    }

    /**
     * Validates the card expiry date components.
     *
     * @param month The expiry month (should be between 1 and 12).
     * @param year The expiry year (should not be in the past).
     * @throws InvalidClientDataException if the month is out of range or the year is in the past.
     */
    public static void validateCardExpiry(int month, int year) throws InvalidClientDataException {
        if (month < 1 || month > 12) {
            throw new InvalidClientDataException("Card expiry month must be between 1 and 12.");
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year < currentYear) {
            throw new InvalidClientDataException("Card expiry year cannot be in the past.");
        }
    }

    /**
     * Validates the driver's license number.
     *
     * @param driversLicenseNumber The driver's license number to validate.
     * @throws InvalidDriversLicenseNumberException if the driver's license number is empty,
     *         non-numeric, or does not contain between 7 and 9 digits.
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
     * Validates the drivers license input data.
     *
     * @param taudatZehudNumber The Teudat Zehut number (ID).
     * @param licenseNumber The driver's license number.
     * @param clientBirth The client's birth date.
     * @param dateIssue The date on which the license was issued.
     * @param dateExpir The expiry date of the license.
     * @throws IllegalArgumentException if any required data is null or if dates are inconsistent.
     */
    public static void validateDriversLicenseInput(String taudatZehudNumber, String licenseNumber,
                                                     LocalDate clientBirth, LocalDate dateIssue, LocalDate dateExpir) {
        if (taudatZehudNumber == null || taudatZehudNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Teudat Zehut number cannot be null or empty.");
        }
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("License number cannot be null or empty.");
        }
        if (clientBirth == null) {
            throw new IllegalArgumentException("Client birth date cannot be null.");
        }
        if (dateIssue == null) {
            throw new IllegalArgumentException("Issue date cannot be null.");
        }
        if (dateExpir == null) {
            throw new IllegalArgumentException("Expiry date cannot be null.");
        }
        if (dateIssue.isAfter(dateExpir)) {
            throw new IllegalArgumentException("Issue date cannot be after expiry date.");
        }
        if (clientBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Client birth date cannot be in the future.");
        }
    }

    /**
     * Validates the rental dates.
     *
     * This method ensures that both start and end dates are not null, that the start date
     * is before the end date, and that the start date is not in the past relative to the current time.
     *
     * @param startDate The rental start date.
     * @param endDate The rental end date.
     * @throws InvalidRentalDatesException if the dates are invalid.
     */
    public static void validateRentalDates(LocalDate startDate, LocalDate endDate) throws InvalidRentalDatesException {
        if (startDate == null || endDate == null) {
            throw new InvalidRentalDatesException("Rental dates cannot be null.");
        }
        if (startDate.isBefore(endDate)) {
            throw new InvalidRentalDatesException("The rental start date must be before the end date.");
        }
        LocalDate today = LocalDate.now();
        if (today.isBefore(startDate)) {
            throw new InvalidRentalDatesException("The rental start date cannot be in the past.");
        }
    }

    /**
     * Validates the availability of a car.
     *
     * @param car The car to check for availability.
     * @throws CarUnavailableException if the car is not found or is not available for rental.
     */
    public static void validateCarAvailability(Car car) throws CarUnavailableException {
        if (car == null) {
            throw new CarUnavailableException("Car not found.");
        }
        if (!car.isAvailable()) {
            throw new CarUnavailableException("Car is not available for rental.");
        }
    }

    /**
     * Validates that the shop name is not null or empty.
     *
     * @param name The shop name.
     * @throws InvalidShopDataException if the shop name is null or empty.
     */
    public static void validateShopName(String name) throws InvalidShopDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidShopDataException("Shop name cannot be null or empty.");
        }
    }

    /**
     * Validates that the shop city is not null or empty.
     *
     * @param city The shop city.
     * @throws InvalidShopDataException if the city is null or empty.
     */
    public static void validateShopCity(String city) throws InvalidShopDataException {
        if (city == null || city.trim().isEmpty()) {
            throw new InvalidShopDataException("City cannot be null or empty.");
        }
    }

    /**
     * Validates that the shop address is not null or empty.
     *
     * @param address The shop address.
     * @throws InvalidShopDataException if the address is null or empty.
     */
    public static void validateShopAddress(String address) throws InvalidShopDataException {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidShopDataException("Address cannot be null or empty.");
        }
    }
}