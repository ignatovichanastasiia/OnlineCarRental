package application.services;

public class ValidationService {
//
//    public static void validateClientData(Client client) throws InvalidClientDataException {
//        // Базовая проверка – поля не должны быть пустыми
//        if (client.getName() == null || client.getName().isEmpty()) {
//            throw new InvalidClientDataException("Имя клиента не может быть пустым.");
//        }
//        if (client.getEmail() == null || client.getEmail().isEmpty()) {
//            throw new InvalidClientDataException("Email клиента не может быть пустым.");
//        }
//        // Можно добавить дополнительную проверку, например, формат email
//        if (!client.getEmail().contains("@")) {
//            throw new InvalidClientDataException("Некорректный формат email.");
//        }
//    }
//
//    public static void validateRentalDates(Date startDate, Date endDate) throws InvalidRentalDatesException {
//        // Проверяем, что даты не null и дата начала раньше даты окончания
//        if (startDate == null || endDate == null) {
//            throw new InvalidRentalDatesException("Даты аренды не могут быть null.");
//        }
//        if (!startDate.before(endDate)) {
//            throw new InvalidRentalDatesException("Дата начала аренды должна быть раньше даты окончания.");
//        }
//        // Дополнительно можно проверить, чтобы даты не были в прошлом
//        if (startDate.before(new Date())) {
//            throw new InvalidRentalDatesException("Дата начала аренды не может быть в прошлом.");
//        }
//    }
//
//    public static void validateCarAvailability(Car car) throws CarUnavailableException {
//        if (car == null) {
//            throw new CarUnavailableException("Автомобиль не найден.");
//        }
//        if (!car.isAvailable()) {
//            throw new CarUnavailableException("Автомобиль недоступен для аренды.");
//        }
//    }
}
