package application.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import application.exceptions.CarUnavailableException;
import application.models.Car;
import application.repositories.CarRepository;

/**
 * The CarService class provides business operations related to Car objects.
 * It interacts with the CarRepository to retrieve and update car details,
 * and offers methods to get unique car brands, price range details, and to manage
 * car booking and status updates.
 */
public class CarService {
    private CarRepository carRepository;

    /**
     * Constructs a new CarService using the specified repository.
     *
     * @param carRepository the repository for car data operations.
     */
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Retrieves a set of unique car brands from the repository.
     *
     * @return a Set of Strings representing unique car brands.
     */
    public Set<String> getCarBrands() {
        List<Car> cars = carRepository.getAllCars();
        return cars.stream()
                   .map(Car::getBrand)
                   .collect(Collectors.toSet());
    }

    /**
     * Retrieves the minimum daily rental price among all cars.
     *
     * @return the lowest daily rental price as a double, or 0.0 if no cars exist.
     */
    public double getMinPrice() {
        List<Car> cars = carRepository.getAllCars();
        return cars.stream()
                   .mapToDouble(Car::getDailyPrice)
                   .min()
                   .orElse(0.0);
    }

    /**
     * Retrieves the maximum daily rental price among all cars.
     *
     * @return the highest daily rental price as a double, or 0.0 if no cars exist.
     */
    public double getMaxPrice() {
        List<Car> cars = carRepository.getAllCars();
        return cars.stream()
                   .mapToDouble(Car::getDailyPrice)
                   .max()
                   .orElse(0.0);
    }

    /**
     * Retrieves a list of all available cars from the repository.
     *
     * @return a List of Car objects that are currently available for rental.
     */
    public List<Car> listAvailableCars() {
        return carRepository.getAllAvailableCars();
    }

    /**
     * Retrieves detailed information of a specific car by its identifier.
     *
     * @param carId the unique identifier of the car.
     * @return the Car object with the given id, or null if the car is not found.
     */
    public Car getCarDetails(int carId) {
        return carRepository.getCarById(carId);
    }

    /**
     * Books a car by validating its availability and marking it as rented.
     * <p>
     * This method first checks if the car is available using the validation service.
     * If the car is unavailable, a CarUnavailableException is thrown, and the error is handled.
     *
     * @param carId the unique identifier of the car to be booked.
     */
    public void bookCar(int carId) {
        try {
            Car car = carRepository.getCarById(carId);
            // Validate that the car is available for rental.
            ValidationService.validateCarAvailability(car);
            // Mark the car as rented and update the repository.
            car.markAsRented();
            carRepository.updateCar(car);
            System.out.println("Car successfully booked: " + car);
        } catch (CarUnavailableException e) {
            System.err.println("Error booking car: " + e.getMessage());
        }
    }

    /**
     * Updates the availability status of a specific car.
     *
     * @param carId       the unique identifier of the car.
     * @param isAvailable the new availability status (true if available, false if not).
     */
    public void updateCarStatus(int carId, boolean isAvailable) {
        Car car = carRepository.getCarById(carId);
        if (car != null) {
            car.setAvailable(isAvailable);
            carRepository.updateCar(car);
            System.out.println("Car status updated: " + car);
        } else {
            System.err.println("Car not found (ID: " + carId + ")");
        }
    }
}