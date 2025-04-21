package application.services;

import java.time.LocalDate;
import application.exceptions.InvalidClientDataException;
import application.models.DriversLicense;
import application.repositories.DriversLicenseRepository;

/**
 * DriversLicenseService provides CRUD operations for DriversLicense objects.
 * It delegates input validation to ValidationService.
 */
public class DriversLicenseService {

    private DriversLicenseRepository licenseRepository;

    public DriversLicenseService(DriversLicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    /**
     * Creates a new driver's license after validating input data.
     *
     * @param taudatZehut   the client's Teudat Zehut (ID) number.
     * @param licenseNumber the driver's license number.
     * @param birthDate     the client's birth date.
     * @param issueDate     the license issue date.
     * @param expiryDate    the license expiry date.
     * @return the newly created DriversLicense object.
     * @throws InvalidClientDataException if any validation fails.
     */
    public DriversLicense createDriversLicense(String taudatZehut, String licenseNumber, LocalDate birthDate,
                                                 LocalDate issueDate, LocalDate expiryDate)
            throws InvalidClientDataException {
        // Delegate input validation to ValidationService.
        ValidationService.validateDriversLicenseInput(taudatZehut, licenseNumber, birthDate, issueDate, expiryDate);

        DriversLicense license = new DriversLicense(taudatZehut, licenseNumber, birthDate, issueDate, expiryDate);
        licenseRepository.addLicense(license);
        return license;
    }

    public DriversLicense getDriversLicenseByNumber(String licenseNumber) {
        return licenseRepository.getLicenseByNumber(licenseNumber);
    }

    public DriversLicense getDriversLicenseByIDNumber(String clientIDNumber) {
        return licenseRepository.getLicenseByIDNumber(clientIDNumber);
    }

    /**
     * Updates an existing driver's license.
     *
     * @param license the DriversLicense object with updated data.
     * @return true if update was successful; false otherwise.
     * @throws InvalidClientDataException if validation of new data fails.
     */
    public boolean updateDriversLicense(DriversLicense license) throws InvalidClientDataException {
        // Validate updated fields via ValidationService.
        ValidationService.validateDriversLicenseInput(
            license.getTaudatZehut(),
            license.getLicenseNumber(),
            license.getBirthDate(),
            license.getIssueDate(),
            license.getExpiryDate()
        );
        return licenseRepository.updateLicense(license);
    }

    public boolean deleteDriversLicense(String licenseNumber) {
        return licenseRepository.deleteLicense(licenseNumber);
    }
}