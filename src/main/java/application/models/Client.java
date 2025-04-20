package application.models;

import java.io.Serializable;

/**
 * The Client class represents a customer with personal details, including name,
 * identity number, driver's license number, credit card number, email, and phone.
 * Each client is assigned a unique identifier in the format "Cl-001", "Cl-002", etc.,
 * using the first two letters of the class name.
 */
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

//    CH

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
     * @param name                 the full name of the client

     * @param email                the client's email address
     * @param phone                the client's phone number
     */
    
    //MY CH
    public Client(String name, String email, String phone) {

        String className = getClass().getSimpleName();
        String prefix = className.length() >= 2 ? className.substring(0, 2) : className;
        this.id = String.format("%s-%03d", prefix, counter++);
        this.name = name;
        this.identityNumber = null;
        this.driversLicenseNumber = null;
        this.creditCardNumber = null;
        this.email = email;
        this.phone = phone;
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
     */
    public void setName(String name) {
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
     * Sets the client's identity number.
     *
     * @param identityNumber the identity number to be set.
     */
    public void setIdentityNumber(String identityNumber) {
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
     * Sets the client's driver's license number.
     *
     * @param driversLicenseNumber the driver's license number to be set.
     */
    public void setDriversLicenseNumber(String driversLicenseNumber) {
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
     * Sets the client's credit card number.
     *
     * @param creditCardNumber the credit card number to be set.
     */
    public void setCreditCardNumber(String creditCardNumber) {
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
     * Sets the client's email address.
     *
     * @param email the email address to be set.
     */
    public void setEmail(String email) {
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
     * Sets the client's phone number.
     *
     * @param phone the phone number to be set.
     */
    public void setPhone(String phone) {
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