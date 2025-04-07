package application.services;

import models.Client;
import models.Car;
import models.Rental;
import repositories.RentalRepository;
import repositories.CarRepository;

import java.util.Date;

public class RentalService {
    private RentalRepository rentalRepository;
    private CarRepository carRepository;
       
    public RentalService(RentalRepository rentalRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
    }
    
    public void createRentalRecord(Client client, Car car, Date startDate, Date endDate,
                                   String pickUpLocation, String deliveryLocation) {
        // Валидируем даты аренды
        if (!ValidationService.validateRentalDates(startDate, endDate)) {
            System.out.println("Неверные даты аренды!");
            return;
        }
        
        // Проверяем доступность автомобиля
        if (!ValidationService.validateCarAvailability(car)) {
            System.out.println("Автомобиль недоступен для аренды.");
            return;
        }
        
        // Если автомобиль уже забронирован, прекращаем создание аренды
        if (!car.isAvailable()) {
            System.out.println("Автомобиль уже забронирован.");
            return;
        }
        
        // Создаём запись об аренде
        Rental rental = new Rental(client, car, startDate, endDate, pickUpLocation, deliveryLocation);
        rentalRepository.save(rental);
        // Обновляем статус автомобиля на занятый
        car.markAsRented();
        carRepository.updateCar(car);
        System.out.println("Запись аренды создана: " + rental);
    }
    
    public Rental getRentalDetails(int rentalId) {
        return rentalRepository.getRentalById(rentalId);
    }
    
    public void updateRental(Rental rental) {
        rentalRepository.updateRental(rental);
    }
    
    public void cancelRental(int rentalId) {
        Rental rental = rentalRepository.getRentalById(rentalId);
        if (rental != null) {
            // Отменяем аренду – возвращаем автомобиль в доступное состояние
            Car car = rental.getCar();
            car.markAsAvailable();
            carRepository.updateCar(car);
            rentalRepository.deleteRental(rentalId);
            System.out.println("Аренда отменена: " + rental);
        } else {
            System.out.println("Запись аренды не найдена (ID: " + rentalId + ")");
        }
    }
}