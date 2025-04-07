package application.services;

import models.Client;
import models.Car;

import java.util.Date;

public class ValidationService {
    
    public static boolean validateClientData(Client client) {
        // Базовая проверка – поля не должны быть пустыми
        if (client.getName() == null || client.getName().isEmpty()) return false;
        if (client.getEmail() == null || client.getEmail().isEmpty()) return false;
        // Можно добавить дополнительную проверку, например, формат email
        return true;
    }
    
    public static boolean validateRentalDates(Date startDate, Date endDate) {
        // Проверяем, что даты не null и дата начала раньше даты окончания
        if (startDate == null || endDate == null) return false;
        if (!startDate.before(endDate)) return false;
        // Дополнительно можно проверить, чтобы даты не были в прошлом
        return true;
    }
    
    public static boolean validateCarAvailability(Car car) {
        return car != null && car.isAvailable();
    }
}
