package application.repositories;

import models.Rental;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class RentalRepository {
    private List<Rental> rentalList = new ArrayList<>();

    // CRUD-методы
    public void save(Rental rental) {
        rentalList.add(rental);
    }
    
    public Rental getRentalById(int id) {
        for (Rental rental : rentalList) {
            if (rental.getId() == id)
                return rental;
        }
        return null;
    }
    
    public void updateRental(Rental updatedRental) {
        for (int i = 0; i < rentalList.size(); i++) {
            if (rentalList.get(i).getId() == updatedRental.getId()) {
                rentalList.set(i, updatedRental);
                break;
            }
        }
    }
    
    public void deleteRental(int id) {
        rentalList.removeIf(rental -> rental.getId() == id);
    }
    
    // Сохранение списока аренд в файл
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(rentalList);
            System.out.println("Данные аренды успешно сохранены в файл: " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных аренды: " + e.getMessage());
        }
    }
    
    // Загрузка списока аренд из файла
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            rentalList = (List<Rental>) ois.readObject();
            System.out.println("Данные аренды успешно загружены из файла: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке данных аренды: " + e.getMessage());
        }
    }
    
    public List<Rental> getAllRentals() {
        return rentalList;
    }
}
