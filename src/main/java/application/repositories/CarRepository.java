package application.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import application.models.Car;

/**
 * The CarRepository class provides CRUD operations for managing Car objects. It
 * supports adding, retrieving, updating, and deleting cars from an in-memory
 * list. Additionally, it provides various query methods (e.g., filtering by
 * brand or price) and methods to persist the car list to a file and load it
 * back.
 */
public class CarRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String DIR_PATH = "ser_location";
	private static final String FILE_NAME = "cars.ser";

	private final File dir = new File(DIR_PATH);
	private final File file = new File(dir, FILE_NAME);

	private List<Car> carList;

	public CarRepository() {
		ensureStorageExists();
		carList = loadCarsFromFile();
		if (carList == null) {
			carList = new ArrayList<>();
			carList =  createCarPool();
		}
	}

	public static List<Car> createCarPool() {
		List<Car> cars = new ArrayList<>();
		String[] brands = { "Toyota", "Ford", "BMW", "Mercedes", "Hyundai" };
		String[] models = { "Corolla", "Focus", "X5", "C-Class", "Elantra" };
		Random rand = new Random();

		for (int i = 0; i < 20; i++) {
			String brand = brands[i % brands.length];
			String model = models[i % models.length];
			int year = 2015 + rand.nextInt(10); // от 2015 до 2024
			double price = 100 + rand.nextDouble() * 150; // от 100 до ~250
			Car car = new Car(brand, model + " " + (i + 1), year, Math.round(price * 100.0) / 100.0);
			cars.add(car);
		}

		return cars;
	}

	private void ensureStorageExists() {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
					oos.writeObject(new ArrayList<Car>());
				}
			} catch (IOException e) {
				System.err.println("Error creating initial file: " + e.getMessage());
			}
		}
	}

	public void save(Car car) {
		carList.add(car);
		saveToFile();
	}

	public Car getCarById(int id) {
		return carList.stream().filter(car -> String.valueOf(id).equals(car.getId())).findFirst().orElse(null);
	}

	public List<Car> getAllAvailableCars() {
		return carList.stream().filter(Car::isAvailable).collect(Collectors.toList());
	}

	public void updateCar(Car updatedCar) {
		for (int i = 0; i < carList.size(); i++) {
			if (carList.get(i).getId().equals(updatedCar.getId())) {
				carList.set(i, updatedCar);
				saveToFile();
				return;
			}
		}
	}

	public void deleteCar(int id) {
		carList.removeIf(car -> String.valueOf(id).equals(car.getId()));
		saveToFile();
	}

	public List<Car> getListCarByBrand(String brand) {
		return carList.stream().filter(car -> car.getBrand().equalsIgnoreCase(brand)).collect(Collectors.toList());
	}

	public List<Car> getListCarByMaxPrice(double maxPrice) {
		return carList.stream().filter(car -> car.getDailyPrice() < maxPrice).collect(Collectors.toList());
	}

	public double getMaxPrice() {
		return carList.stream().mapToDouble(Car::getDailyPrice).max().orElse(0.0);
	}

	public List<Car> getListCarByMinPrice(double minPrice) {
		return carList.stream().filter(car -> car.getDailyPrice() > minPrice).collect(Collectors.toList());
	}

	public double getMinPrice() {
		return carList.stream().mapToDouble(Car::getDailyPrice).min().orElse(0.0);
	}

	public List<String> getBrandList() {
		return carList.stream().map(Car::getBrand).distinct().collect(Collectors.toList());
	}

	private void saveToFile() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(carList);
		} catch (IOException e) {
			System.err.println("Error saving car data: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private List<Car> loadCarsFromFile() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return (List<Car>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error loading car data: " + e.getMessage());
			return null;
		}
	}

	public List<Car> getAllCars() {
		return carList;
	}
}
