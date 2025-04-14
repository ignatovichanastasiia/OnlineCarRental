package application.services;

import application.exceptions.InvalidRentalDatesException;
import application.exceptions.CarUnavailableException;
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

    public void createRentalRecord(Client client, Car car, Date startDate, Date endDate) {
        try {
            // Валидируем даты аренды и доступность автомобиля
            ValidationService.validateRentalDates(startDate, endDate);
            ValidationService.validateCarAvailability(car);

            // Создаём запись об аренде
            Rental rental = new Rental(client, car, startDate, endDate);
            rentalRepository.save(rental);

            // Обновляем статус автомобиля
            car.markAsRented();
            carRepository.updateCar(car);

            System.out.println("Запись аренды создана: " + rental);
        } catch (InvalidRentalDatesException | CarUnavailableException e) {
            System.err.println("Ошибка создания записи аренды: " + e.getMessage());
        }
    }

    public Rental getRentalDetails(int rentalId) {
        return rentalRepository.getRentalById(rentalId);
    }

    public void updateRental(Rental rental) {
        rentalRepository.updateRental(rental);
        System.out.println("Запись аренды обновлена: " + rental);
    }

    public void cancelRental(int rentalId) {
        Rental rental = rentalRepository.getRentalById(rentalId);
        if (rental != null) {
            Car car = rental.getCar();
            car.markAsAvailable();
            carRepository.updateCar(car);
            rentalRepository.deleteRental(rentalId);
            System.out.println("Аренда отменена: " + rental);
        } else {
            System.err.println("Запись аренды не найдена (ID: " + rentalId + ")");
        }
    }
}