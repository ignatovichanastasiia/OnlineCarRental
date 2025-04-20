
package application.models;

import java.io.Serializable;

/**
 * The Car class represents a car model with details such as brand, model name,
 * year, daily rental price, availability status, and an associated image URL or
 * path. It serializes the car information and generates a unique identifier for
 * each car in the format "Ca-001", "Ca-002", etc., using the first two letters
 * of the class name.
 */
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	// Static counter to generate unique IDs for each Car instance.
	private static int counter = 1;

	// Unique identifier in the format "Ca-001", "Ca-002", etc.
	private String id;

	// The brand of the car.
	private String brand;

	// The model of the car.
	private String model;

	// The year the car was manufactured.
	private int year;

	// The daily rental price of the car.
	private double dailyPrice;

	// Indicates whether the car is available for rental.
	private boolean isAvailable;

	// A String representing the URL or path of an image for the car.
	private String image;

	/**
	 * Constructs a new Car with the specified details.
	 *
	 * @param brand      the brand of the car
	 * @param model      the model of the car
	 * @param year       the manufacturing year of the car
	 * @param dailyPrice the daily rental price of the car
	 * @param image      the URL or file path of the car's image
	 */
	public Car(String brand, String model, int year, double dailyPrice, String image) {
		String className = getClass().getSimpleName();
		String prefix = className.length() >= 2 ? className.substring(0, 2) : className;
		// Generate the unique identifier in the format "Ca-001", "Ca-002", etc.
		this.id = String.format("%s-%03d", prefix, counter++);
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.dailyPrice = dailyPrice;
		this.isAvailable = true; // By default, a new car is available.
		this.image = image;
	}

	/**
	 * Returns the unique identifier of the car.
	 *
	 * @return the car's ID as a String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the brand of the car.
	 *
	 * @return the car brand as a String
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the brand of the car.
	 *
	 * @param brand the car brand to be set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Returns the model of the car.
	 *
	 * @return the car model as a String
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the model of the car.
	 *
	 * @param model the car model to be set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Returns the manufacturing year of the car.
	 *
	 * @return the car's manufacturing year as an int
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the manufacturing year of the car.
	 *
	 * @param year the manufacturing year to be set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Returns the daily rental price of the car.
	 *
	 * @return the daily rental price as a double
	 */
	public double getDailyPrice() {
		return dailyPrice;
	}

	/**
	 * Sets the daily rental price of the car.
	 *
	 * @param dailyPrice the daily rental price to be set
	 */
	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	/**
	 * Returns whether the car is currently available for rental.
	 *
	 * @return true if the car is available, false otherwise
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

	/**
	 * Sets the availability status of the car.
	 *
	 * @param isAvailable true to mark the car as available, false to mark it as
	 *                    unavailable
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * Marks the car as rented (i.e., not available).
	 */
	public void markAsRented() {
		this.isAvailable = false;
	}

	/**
	 * Marks the car as available for rental.
	 */
	public void markAsAvailable() {
		this.isAvailable = true;
	}

	/**
	 * Returns the image path or URL of the car.
	 *
	 * @return the image as a String
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the image path or URL of the car.
	 *
	 * @param image the image (URL or file path) to be set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Returns a string representation of the car object, which includes its id,
	 * brand, model, year, daily rental price, availability status, and image.
	 *
	 * @return a String representing the car details
	 */
	@Override
	public String toString() {
		return "Car{" + "id='" + id + '\'' + ", brand='" + brand + '\'' + ", model='" + model + '\'' + ", year=" + year
				+ ", dailyPrice=" + dailyPrice + ", isAvailable=" + isAvailable + ", image='" + image + '\'' + '}';
	}
}
