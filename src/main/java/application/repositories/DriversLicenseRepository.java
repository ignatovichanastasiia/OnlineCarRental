package application.repositories;

import application.models.DriversLicense;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing DriversLicense objects.
 * Maintains an internal list and supports serialization/deserialization.
 */
public class DriversLicenseRepository {
    private static final String LICENSES = "driversLicenses.dat";
    private List<DriversLicense> licenseList;

    public DriversLicenseRepository() {
        licenseList = loadLicensesFromFile();
        if (licenseList == null) {
            licenseList = new ArrayList<>();
        }
    }

    public List<DriversLicense> getAllLicenses() {
        return licenseList;
    }

    public DriversLicense getLicenseByNumber(String licenseNumber) {
        return licenseList.stream()
            .filter(license -> license.getLicenseNumber().equals(licenseNumber))
            .findFirst()
            .orElse(null);
    }

    public DriversLicense getLicenseByIDNumber(String clientIDNumber) {
        return licenseList.stream()
            .filter(license -> license.getTaudatZehut().equals(clientIDNumber))
            .findFirst()
            .orElse(null);
    }

    public void addLicense(DriversLicense license) {
        licenseList.add(license);
        saveLicensesToFile();
    }

    public boolean updateLicense(DriversLicense updatedLicense) {
        for (int i = 0; i < licenseList.size(); i++) {
            if (licenseList.get(i).getLicenseNumber().equals(updatedLicense.getLicenseNumber())) {
                licenseList.set(i, updatedLicense);
                saveLicensesToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteLicense(String licenseNumber) {
        boolean removed = licenseList.removeIf(license -> license.getLicenseNumber().equals(licenseNumber));
        if (removed) {
            saveLicensesToFile();
        }
        return removed;
    }

    private void saveLicensesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LICENSES))) {
            oos.writeObject(licenseList);
        } catch (IOException e) {
            System.err.println("Error saving licenses: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<DriversLicense> loadLicensesFromFile() {
        File file = new File(LICENSES);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<DriversLicense>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading licenses: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}