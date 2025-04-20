package application.repositories;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import application.models.Car;

/**
 * The CarRepository class provides CRUD operations for managing Car objects.
 * It supports adding, retrieving, updating, and deleting cars from an in-memory list.
 * Additionally, it provides various query methods (e.g., filtering by brand or price)
 * and methods to persist the car list to a file and load it back.
 */
public class CarRepository implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // The list that holds Car objects.
    private List<Car> carList = new ArrayList<>();
    
    
//    CONSTRUCTOR W Connection
    public CarRepository(Connection connection) {
		// TODO Auto-generated constructor stub
	}

    /**
     * Saves a new Car object to the repository.
     *
     * @param car the Car object to be saved.
     */
    public void save(Car car) {
        carList.add(car);
    }
    
    /**
     * Retrieves a Car object by its unique identifier.
     *
     * @param id the unique identifier of the Car.
     * @return the Car with the matching id, or null if not found.
     */
    public Car getCarById(int id) {
//        for (Car car : carList) {
//            if (car.getId() == id)
//                return car;
//        }
        return null;
    }
    
    /**
     * Retrieves all available cars from the repository.
     *
     * @return a list of Car objects that are available for rental.
     */
    public List<Car> getAllAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : carList) {
            if (car.isAvailable())
                availableCars.add(car);
        }
        return availableCars;
    }
    
    /**
     * Updates an existing Car in the repository.
     *
     * @param updatedCar the Car object containing updated information.
     */
    public void updateCar(Car updatedCar) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getId() == updatedCar.getId()) {
                carList.set(i, updatedCar);
                break;
            }
        }
    }
    
    /**
     * Deletes a Car from the repository using its unique identifier.
     *
     * @param id the unique identifier of the Car to be deleted.
     */
    public void deleteCar(int id) {
//        carList.removeIf(car -> car.getId() == id);
    }
    
    /**
     * Retrieves a list of all unique car brands present in the repository.
     *
     * @return a list of unique car brands.
     */
    public List<String> getBrandList() {
        return carList.stream()
                      .map(Car::getBrand)
                      .distinct()
                      .collect(Collectors.toList());
    }
    
    /**
     * Retrieves a list of Car objects that match the specified brand.
     *
     * @param brand the brand of the car.
     * @return a list of Car objects with the specified brand.
     */
    public List<Car> getListCarByBrand(String brand) {
        return carList.stream()
                      .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                      .collect(Collectors.toList());
    }
    
    /**
     * Retrieves a list of Car objects with a daily rental price lower than the specified maximum price.
     *
     * @param maxPrice the maximum daily rental price.
     * @return a list of Car objects with a daily price less than maxPrice.
     */
    public List<Car> getListCarByMaxPrice(double maxPrice) {
        return carList.stream()
                      .filter(car -> car.getDailyPrice() < maxPrice)
                      .collect(Collectors.toList());
    }
    
    /**
     * Retrieves the maximum daily rental price among all cars in the repository.
     *
     * @return the maximum daily rental price, or 0.0 if the repository is empty.
     */
    public double getMaxPrice() {
        return carList.stream()
                      .mapToDouble(Car::getDailyPrice)
                      .max()
                      .orElse(0.0);
    }
    
    /**
     * Retrieves the minimum daily rental price among all cars in the repository.
     *
     * @return the minimum daily rental price, or 0.0 if the repository is empty.
     */
    public double getMinPrice() {
        return carList.stream()
                      .mapToDouble(Car::getDailyPrice)
                      .min()
                      .orElse(0.0);
    }
    
    /**
     * Saves the current list of cars to a file.
     *
     * @param filename the file path where the car list will be saved.
     */
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(carList);
            System.out.println("Car data successfully saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving car data: " + e.getMessage());
        }
    }
    
    /**
     * Loads a list of cars from a file into the repository.
     *
     * @param filename the file path from which the car list will be loaded.
     */
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            carList = (List<Car>) ois.readObject();
            System.out.println("Car data successfully loaded from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading car data: " + e.getMessage());
        }
    }
    
    /**
     * Retrieves the complete list of cars stored in the repository.
     *
     * @return a list of all Car objects in the repository.
     */
    public List<Car> getAllCars() {
        return carList;
    }
}
