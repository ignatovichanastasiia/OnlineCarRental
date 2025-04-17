package application.services;

import java.time.LocalDate;

import application.exceptions.InvalidClientDataException;
import application.exceptions.InvalidRentalDatesException;
import application.models.Client;

public class ValidationService {

    public static void validateClientData(Client client) throws InvalidClientDataException {
        // Базовая проверка – поля не должны быть пустыми
        if (client.getName() == null || client.getName().isEmpty()) {
            throw new InvalidClientDataException("Имя клиента не может быть пустым.");
        }
        if (client.getEmail() == null || client.getEmail().isEmpty()) {
            throw new InvalidClientDataException("Email клиента не может быть пустым.");
        }
        // Можно добавить дополнительную проверку, например, формат email
        if (!client.getEmail().contains("@")) {
            throw new InvalidClientDataException("Некорректный формат email.");
        }
    }

    //TODO
    public static void validateRentalDates(LocalDate localDateFrom, LocalDate localDateTo) throws InvalidRentalDatesException {
        // Проверяем, что даты не null и дата начала раньше даты окончания
        if (localDateFrom == null || localDateTo == null) {
            throw new InvalidRentalDatesException("Даты аренды не могут быть null.");
        }
        if (localDateTo.isBefore(localDateFrom)) {
            throw new InvalidRentalDatesException("Дата начала аренды должна быть раньше даты окончания.");
        }
        // Дополнительно можно проверить, чтобы даты не были в прошлом
        if (localDateFrom.isBefore(LocalDate.now())) {
            throw new InvalidRentalDatesException("Дата начала аренды не может быть в прошлом.");
        }
    }

//    public static void validateCarAvailability(Car car) throws CarUnavailableException {
//        if (car == null) {
//            throw new CarUnavailableException("Автомобиль не найден.");
//        }
//        if (!car.isAvailable()) {
//            throw new CarUnavailableException("Автомобиль недоступен для аренды.");
//        }
//    }
}
