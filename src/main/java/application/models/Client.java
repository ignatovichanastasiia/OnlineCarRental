
package application.models;

import java.io.Serializable;

import application.exceptions.InvalidClientDataException;
import application.exceptions.InvalidCreditCardNumberException;
import application.exceptions.InvalidDriversLicenseNumberException;
import application.exceptions.InvalidEmailException;
import application.exceptions.InvalidIdentityNumberException;
import application.services.ValidationService;

/**
 * The Client class represents a customer with personal details, including name,
 * identity number, driver's license number, credit card number, email, and phone.
 * Each client is assigned a unique identifier in the format "Cl-001", "Cl-002", etc.,
 * using the first two letters of the class name.
 */
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    // Unique counter for client IDs
    private static int counter = 1;
    private String id;
    private String name;
    private String identityNumber;
    private String driversLicenseNumber;
    private String creditCardNumber;
    private String email;
    private String phone;

    /**
     * Constructs a new Client with the specified details.
     *
     * @param name  the full name of the client.
     * @param email the client's email address.
     * @param phone the client's phone number.
     * @throws InvalidEmailException if the email format is invalid.
     * @throws InvalidClientDataException if any required client data is invalid.
     */
    public Client(String name, String email, String phone)
            throws InvalidClientDataException, InvalidEmailException {
        // Generate unique client ID using the first two letters of the class name and a counter.
        String className = getClass().getSimpleName();
        String prefix = className.length() >= 2 ? className.substring(0, 2) : className;
        this.id = String.format("%s-%03d", prefix, counter++);

        // Validate and set fields that are required in the constructor.
        ValidationService.validateName(name);
        ValidationService.validateEmail(email);
        ValidationService.validatePhone(phone);

        this.name = name;
        this.email = email;
        this.phone = phone;

        // These fields will be set via their setters and validated individually.
        this.identityNumber = null;
        this.driversLicenseNumber = null;
        this.creditCardNumber = null;
    }

    /**
     * Returns the unique identifier of the client.
     *
     * @return the client's ID as a String.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the full name of the client.
     *
     * @return the client's full name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the client.
     *
     * @param name the new full name to be set.
     * @throws InvalidClientDataException if the name is invalid.
     */
    public void setName(String name) throws InvalidClientDataException {
        ValidationService.validateName(name);
        this.name = name;
    }

    /**
     * Returns the client's identity number.
     *
     * @return the identity number as a String.
     */
    public String getIdentityNumber() {
        return identityNumber;
    }

 /**
  * Sets the client's identity number after validating it.
  *
  * @param identityNumber the identity number to be set.
  * @throws InvalidIdentityNumberException if the identity number is invalid.
  */
 public void setIdentityNumber(String identityNumber) throws InvalidIdentityNumberException {
     ValidationService.validateIdentityNumber(identityNumber);
     this.identityNumber = identityNumber;
 }

    /**
     * Returns the client's driver's license number.
     *
     * @return the driver's license number as a String.
     */
    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

 /**
  * Sets the client's driver's license number after validating it.
  *
  * @param driversLicenseNumber the driver's license number to be set.
  * @throws InvalidDriversLicenseNumberException if the driver's license number is invalid.
  */
 public void setDriversLicenseNumber(String driversLicenseNumber) throws InvalidDriversLicenseNumberException {
     ValidationService.validateDriversLicenseNumber(driversLicenseNumber);
     this.driversLicenseNumber = driversLicenseNumber;
 }

    /**
     * Returns the client's credit card number.
     *
     * @return the credit card number as a String.
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

 /**
  * Sets the client's credit card number after validating it.
  *
  * @param creditCardNumber the credit card number to be set.
  * @throws InvalidCreditCardNumberException if the credit card number is invalid.
  */
 public void setCreditCardNumber(String creditCardNumber) throws InvalidCreditCardNumberException {
     ValidationService.validateCreditCardNumber(creditCardNumber);
     this.creditCardNumber = creditCardNumber;
 }

    /**
     * Returns the client's email address.
     *
     * @return the email address as a String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the client's email after validating it.
     *
     * @param email the email address to set.
     * @throws InvalidEmailException if the email format is invalid.
     * @throws InvalidClientDataException if the email is empty.
     */
    public void setEmail(String email) throws InvalidClientDataException, InvalidEmailException {
        ValidationService.validateEmail(email);
        this.email = email;
    }

    /**
     * Returns the client's phone number.
     *
     * @return the phone number as a String.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the client's phone number after validating it.
     *
     * @param phone the phone number to be set.
     * @throws InvalidClientDataException if the phone number is invalid.
     */
    public void setPhone(String phone) throws InvalidClientDataException {
        ValidationService.validatePhone(phone);
        this.phone = phone;
    }

    /**
     * Returns a string representation of this client, including the client's ID, name, and email.
     *
     * @return a string representing the client's key details.
     */
    @Override
    public String toString() {
        return "Client{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
