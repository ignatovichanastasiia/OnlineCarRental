package application.services;

import application.exceptions.CarUnavailableException;
import models.Car;
import repositories.CarRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarService {
	private CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public Set<String> getCarBrands() {
		List<Car> cars = carRepository.getAllCars();
		return cars.stream().map(Car::getBrand).collect(Collectors.toSet());
	}

	public double getMinPrice() {
		List<Car> cars = carRepository.getAllCars();
		return cars.stream().mapToDouble(Car::getDailyPrice).min().orElse(0.0);
	}

	public double getMaxPrice() {
		List<Car> cars = carRepository.getAllCars();
		return cars.stream().mapToDouble(Car::getDailyPrice).max().orElse(0.0);
	}

	public List<Car> listAvailableCars() {
		return carRepository.getAllAvailableCars();
	}

	public Car getCarDetails(int carId) {
		return carRepository.getCarById(carId);
	}

	public void bookCar(int carId) {
		try {
			Car car = carRepository.getCarById(carId);
			ValidationService.validateCarAvailability(car);
			car.markAsRented();
			carRepository.updateCar(car);
			System.out.println("Автомобиль забронирован: " + car);
		} catch (CarUnavailableException e) {
			System.err.println("Ошибка бронирования автомобиля: " + e.getMessage());
		}
	}

	public void updateCarStatus(int carId, boolean isAvailable) {
		Car car = carRepository.getCarById(carId);
		if (car != null) {
			car.setAvailable(isAvailable);
			carRepository.updateCar(car);
			System.out.println("Статус автомобиля обновлён: " + car);
		} else {
			System.err.println("Автомобиль не найден (ID: " + carId + ")");
		}
	}
}
