package application.services;

import models.Car;
import repositories.CarRepository;

import java.util.List;

public class CarService {
    private CarRepository carRepository;
    
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    
    public List<Car> listAvailableCars() {
        return carRepository.getAllAvailableCars();
    }
    
    public Car getCarDetails(int carId) {
        return carRepository.getCarById(carId);
    }
    
    public boolean bookCar(int carId) {
        Car car = carRepository.getCarById(carId);
        if (car != null && car.isAvailable()) {
            car.markAsRented();
            carRepository.updateCar(car);
            System.out.println("Автомобиль забронирован: " + car);
            return true;
        }
        System.out.println("Автомобиль недоступен или не найден (ID: " + carId + ")");
        return false;
    }
    
    public void updateCarStatus(int carId, boolean isAvailable) {
        Car car = carRepository.getCarById(carId);
        if (car != null) {
            car.setAvailable(isAvailable);
            carRepository.updateCar(car);
        }
    }
}
