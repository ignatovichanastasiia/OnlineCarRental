package application.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * The Rental class represents a rental transaction that ties a Client with a Car over a specified period.
 * It includes details such as the rental start and end dates, pick-up location, and delivery location.
 * Each rental is assigned a unique identifier in the format "Re-001", "Re-002", etc., 
 * using the first two letters of the class name.
 */
public class Rental implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int counter = 1;
    private String id;
    private Client client;
    private Car car;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    //ВНИМАНИЕ! Здесь в стринг пик ап локэйшн вся информация о доп сервисе!
    private String pickUpLocation;
    private String shop;
    
    /**
     * Constructs a new Rental with the specified details.
     *
     * @param client           the client renting the car
     */
    public Rental(Client client) {
        // Generate a unique ID using the first two letters of the class name (e.g., "Rental" -> "Re")
        // and a counter formatted as a three-digit number.
        String className = getClass().getSimpleName();
        String prefix = className.length() >= 2 ? className.substring(0, 2) : className;
        this.id = String.format("%s-%03d", prefix, counter++);
        
        this.client = client;
        this.car = null;
        this.rentalStartDate = null;
        this.rentalEndDate = null;
        this.pickUpLocation = null;
        this.shop = null;
    }
    
    /**
     * Returns the unique identifier of the rental.
     *
     * @return the rental's ID as a String.
     */
    public String getId() {
        return id;
    }
    
    /**
     * Returns the client associated with this rental.
     *
     * @return the Client object.
     */
    public Client getClient() {
        return client;
    }
    
    /**
     * Sets the client for this rental.
     *
     * @param client the Client object to set.
     */
    public void setClient(Client client) {
        this.client = client;
    }
    
    /**
     * Returns the car that is being rented.
     *
     * @return the Car object.
     */
    public Car getCar() {
        return car;
    }
    
    /**
     * Sets the car for this rental.
     *
     * @param car the Car object to set.
     */
    public void setCar(Car car) {
        this.car = car;
    }
    
    /**
     * Returns the start date of the rental period.
     *
     * @return the rental start Date.
     */
    public Date getRentalStartDate() {
        return rentalStartDate;
    }
    
    /**
     * Sets the start date of the rental period.
     *
     * @param rentalStartDate the rental start Date to set.
     */
    public void setRentalStartDate(LocalDate rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }
    
    /**
     * Returns the end date of the rental period.
     *
     * @return the rental end Date.
     */
    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }
    
    /**
     * Sets the end date of the rental period.
     *
     * @param rentalEndDate the rental end Date to set.
     */
    public void setRentalEndDate(LocalDate rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }
    
    /**
     * Returns the pick-up location for the rental.
     *
     * @return the pick-up location as a String.
     */
    public String getPickUpLocation() {
        return pickUpLocation;
    }
    
    /**
     * Sets the pick-up location for the rental.
     *
     * @param pickUpLocation the pick-up location to set.
     */
    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }
    
    /**
     * Returns the delivery location for the rental.
     *
     * @return the delivery location as a String.
     */
    public String getShop() {
        return shop;
    }
    
    /**
     * Sets the delivery location for the rental.
     *
     * @param shop the delivery location to set.
     */
    public void setShop(String shop) {
        this.shop = shop;
    }

    /**
     * Returns a string representation of the Rental instance,
     * including its unique ID, client name, car details, and rental period.
     *
     * @return a String summarizing the rental details.
     */

    @Override
    public String toString() {
        return "Rental{" +
               "id='" + id + '\'' +
               ", client=" + (client != null ? client.getName() : "null") +
               ", car=" + (car != null ? car.getBrand() + " " + car.getModel() : "null") +
               ", rentalStartDate=" + rentalStartDate +
               ", rentalEndDate=" + rentalEndDate +
               ", pickUpLocation='" + pickUpLocation + '\'' +
               ", shop='" + shop + '\'' +
               '}';
    }
}
