package application.services;

import java.time.LocalDate;

import application.exceptions.CarUnavailableException;
import application.exceptions.InvalidClientDataException;
import application.exceptions.InvalidRentalDatesException;
import application.models.Car;
import application.models.Client;

/**
 * The ValidationService class provides static methods for validating various inputs in the application.
 * It includes validation for client data, rental dates (using LocalDate), and car availability.
 */
public class ValidationService {

    /**
     * Validates the client data to ensure that required fields are filled correctly.
     * <p>
     * This method checks that:
     * <ul>
     *   <li>The client's name is not null or empty.</li>
     *   <li>The client's email is not null or empty.</li>
     *   <li>The client's email contains an '@' symbol (basic email format check).</li>
     * </ul>
     * 
     * @param client the Client object whose data is to be validated.
     * @throws InvalidClientDataException if the client name or email is missing or in an invalid format.
     */
    public static void validateClientData(Client client) throws InvalidClientDataException {
        // Check that the client's name is not null or empty.
        if (client.getName() == null || client.getName().isEmpty()) {
            throw new InvalidClientDataException("Client name cannot be empty.");
        }
        // Check that the client's email is not null or empty.
        if (client.getEmail() == null || client.getEmail().isEmpty()) {
            throw new InvalidClientDataException("Client email cannot be empty.");
        }
        // Additional check: email should contain '@' symbol.
        if (!client.getEmail().contains("@")) {
            throw new InvalidClientDataException("Invalid email format.");
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