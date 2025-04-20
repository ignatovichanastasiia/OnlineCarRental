
package application.services;

import java.util.Date;

import application.exceptions.CarUnavailableException;
import application.exceptions.InvalidRentalDatesException;
import application.models.Car;
import application.models.Client;
import application.models.Rental;
import application.repositories.CarRepository;
import application.repositories.RentalRepository;

/**
 * The RentalService class provides business logic for managing Rental records.
 * It interacts with the RentalRepository and CarRepository to handle rental-related operations,
 * such as creating, updating, retrieving, and canceling rentals.
 */
public class RentalService {
    private RentalRepository rentalRepository; 
    private CarRepository carRepository;       

    /**
     * Constructs a new RentalService with the specified repositories.
     *
     * @param rentalRepository the repository for rental data.
     * @param carRepository    the repository for car data.
     */
    public RentalService(RentalRepository rentalRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
    }

    /**
     * Creates a new rental record for a client and car within a specified rental period.
     * <p>
     * This method performs the following steps:
     * <ul>
     *   <li>Validates the rental dates using {@code ValidationService.validateRentalDates}.</li>
     *   <li>Checks if the car is available using {@code ValidationService.validateCarAvailability}.</li>
     *   <li>Creates a new {@code Rental} object and saves it to the repository.</li>
     *   <li>Marks the car as rented and updates its status in the CarRepository.</li>
     * </ul>
     *
     * @param client    the Client who is renting the car.
     * @param car       the Car to be rented.
     * @param startDate the start date of the rental period.
     * @param endDate   the end date of the rental period.
     */
    public void createRentalRecord(Client client, Car car, Date startDate, Date endDate) {
        try {
            // Validate rental dates and car availability.
            ValidationService.validateRentalDates(startDate, endDate);
            ValidationService.validateCarAvailability(car);

            // Create a new rental record.
            Rental rental = new Rental(client, car, startDate, endDate);
            rentalRepository.save(rental);

            // Update the car's status as rented.
            car.markAsRented();
            carRepository.updateCar(car);

            System.out.println("Rental record created: " + rental);
        } catch (InvalidRentalDatesException | CarUnavailableException e) {
            System.err.println("Error creating rental record: " + e.getMessage());
        }
    }

    /**
     * Retrieves the details of a specific rental record by its ID.
     *
     * @param rentalId the unique identifier of the rental.
     * @return the Rental object matching the given ID, or null if not found.
     */
    public Rental getRentalDetails(int rentalId) {
        return rentalRepository.getRentalById(rentalId);
    }

    /**
     * Updates an existing rental record in the repository.
     * <p>
     * This method replaces the existing rental record with the updated one provided.
     *
     * @param rental the updated Rental object.
     */
    public void updateRental(Rental rental) {
        rentalRepository.updateRental(rental);
        System.out.println("Rental record updated: " + rental);
    }

    /**
     * Cancels a rental record by its ID and updates the status of the associated car.
     * <p>
     * This method performs the following steps:
     * <ul>
     *   <li>Retrieves the rental record by its ID.</li>
     *   <li>Marks the associated car as available in the CarRepository.</li>
     *   <li>Deletes the rental record from the RentalRepository.</li>
     * </ul>
     *
     * @param rentalId the unique identifier of the rental to cancel.
     */
    public void cancelRental(int rentalId) {
        Rental rental = rentalRepository.getRentalById(rentalId);
        if (rental != null) {
            Car car = rental.getCar();
            car.markAsAvailable();
            carRepository.updateCar(car);
            rentalRepository.deleteRental(rentalId);
            System.out.println("Rental canceled: " + rental);
        } else {
            System.err.println("Rental record not found (ID: " + rentalId + ")");
        }
    }
}