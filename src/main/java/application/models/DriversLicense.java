package application.models;

import java.io.Serializable;
import java.time.LocalDate;

import application.exceptions.InvalidClientDataException;

/**
 * Represents a driver's license with client identification,
 * license number, birth date, category, issue date, and expiry date.
 */
public class DriversLicense implements Serializable {
    private static final long serialVersionUID = 1L;

    private String taudatZehut;
    private String licenseNumber;
    private LocalDate birthDate;
    private char category;
    private LocalDate issueDate;
    private LocalDate expiryDate;

    /**
     * Constructs a new DriversLicense with the provided details.
     *
     * @param taudatZehut   the client's Teudat Zehut number.
     * @param licenseNumber the driver's license number.
     * @param birthDate     the client's birth date.
     * @param issueDate     the license issue date.
     * @param expiryDate    the license expiry date.
     * @throws InvalidClientDataException if any validation fails.
     */
    public DriversLicense(String taudatZehut, String licenseNumber, LocalDate birthDate, LocalDate issueDate, LocalDate expiryDate)
            throws InvalidClientDataException {
        setTaudatZehut(taudatZehut);
        setLicenseNumber(licenseNumber);
        setBirthDate(birthDate);
        setCategory('B');
        setIssueDate(issueDate);
        setExpiryDate(expiryDate);
    }

    public String getTaudatZehut() {
        return taudatZehut;
    }

    public void setTaudatZehut(String taudatZehut) throws InvalidClientDataException {
        if (taudatZehut == null || taudatZehut.trim().isEmpty()) {
            throw new InvalidClientDataException("Teudat Zehut number cannot be null or empty.");
        }
        this.taudatZehut = taudatZehut;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) throws InvalidClientDataException {
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new InvalidClientDataException("License number cannot be null or empty.");
        }
        this.licenseNumber = licenseNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws InvalidClientDataException {
        if (birthDate == null) {
            throw new InvalidClientDataException("Birth date cannot be null.");
        }
        if (birthDate.isAfter(LocalDate.now())) {
            throw new InvalidClientDataException("Birth date cannot be in the future.");
        }
        this.birthDate = birthDate;
    }

    public char getCategory() {
        return category;
    }

    /**
     * Sets the license category.
     * 
     * @param category the license category.
     * @throws InvalidClientDataException if the category is not a letter.
     */
    public void setCategory(char category) throws InvalidClientDataException {
        if (!Character.isLetter(category)) {
            throw new InvalidClientDataException("License category must be a letter.");
        }
        this.category = category;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) throws InvalidClientDataException {
        if (issueDate == null) {
            throw new InvalidClientDataException("Issue date cannot be null.");
        }
        this.issueDate = issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) throws InvalidClientDataException {
        if (expiryDate == null) {
            throw new InvalidClientDataException("Expiry date cannot be null.");
        }
        if (this.issueDate != null && expiryDate.isBefore(this.issueDate)) {
            throw new InvalidClientDataException("Expiry date cannot be before issue date.");
        }
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "DriversLicense [taudatZehut=" + taudatZehut 
                + ", licenseNumber=" + licenseNumber 
                + ", birthDate=" + birthDate 
                + ", category=" + category 
                + ", issueDate=" + issueDate 
                + ", expiryDate=" + expiryDate + "]";
    }
}