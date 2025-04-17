package application.services;

import java.util.List;

import application.models.Car;

public class CarService {
//    private CarRepository carRepository;

//    public CarService(CarRepository carRepository) {
//        this.carRepository = carRepository;
//    }

    public List<Car> listAvailableCars() {
//        return carRepository.getAllAvailableCars();
    	return null;
    }

    public Car getCarDetails(int carId) {
//        return carRepository.getCarById(carId);
    	return null;
    }

    public void bookCar(int carId) {
//        try {
//            Car car = carRepository.getCarById(carId);
//            ValidationService.validateCarAvailability(car);
//            car.markAsRented();
//            carRepository.updateCar(car);
//            System.out.println("Автомобиль забронирован: " + car);
//        } catch (CarUnavailableException e) {
//            System.err.println("Ошибка бронирования автомобиля: " + e.getMessage());
//        }
    }

    public void updateCarStatus(int carId, boolean isAvailable) {
//        Car car = carRepository.getCarById(carId);
//        if (car != null) {
//            car.setAvailable(isAvailable);
//            carRepository.updateCar(car);
//            System.out.println("Статус автомобиля обновлён: " + car);
//        } else {
//            System.err.println("Автомобиль не найден (ID: " + carId + ")");
//        }
    }

	public List<String> getBrandList() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMinPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMaxPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Car> getListCarByMinPrice(double minPrice) {
		// TODO Auto-generated method stub
		return null;		
	}

	public List<Car> getListCarByMaxPrice(double maxPrice) {
		// TODO Auto-generated method stub
		return null;	
	}
}

