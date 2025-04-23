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

import application.models.Rental;

/**
 * Handles CRUD operations and serialization for Rental objects.
 */
public class RentalRepository implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final File dir = new File("ser_location");
    private static final File file = new File(dir, "rental.ser");

    private List<Rental> rentalList;

    public RentalRepository() {
        if (!dir.exists()) dir.mkdirs();

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                rentalList = (List<Rental>) ois.readObject();
                System.out.println("Rental data loaded from file.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading rental data: " + e.getMessage());
                rentalList = new ArrayList<>();
            }
        } else {
            rentalList = new ArrayList<>();
        }
    }

    public void save(Rental rental) {
        rentalList.add(rental);
        saveToFile();
    }

    public void updateRental(Rental updatedRental) {
        for (int i = 0; i < rentalList.size(); i++) {
            if (rentalList.get(i).getId().equals(updatedRental.getId())) {
                rentalList.set(i, updatedRental);
                saveToFile();
                return;
            }
        }
    }

    public Rental getRentalById(int id) {
        for (Rental rental : rentalList) {
            if (rental.getId().equals(String.valueOf(id))) {
                return rental;
            }
        }
        return null;
    }

    public void deleteRental(int id) {
        boolean removed = rentalList.removeIf(rental -> rental.getId().equals(String.valueOf(id)));
        if (removed) {
            System.out.println("Rental with id " + id + " deleted.");
            saveToFile();
        } else {
            System.out.println("Rental with id " + id + " not found.");
        }
    }

    public List<Rental> getAllRentals() {
        return rentalList;
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(rentalList);
            System.out.println("Rental data saved.");
        } catch (IOException e) {
            System.err.println("Error saving rental data: " + e.getMessage());
        }
    }
}