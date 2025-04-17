package application.models;

public class Car implements Serializable {
	private static final long serialVersionUID = 1L; // Версия класса для сериализации

    private static int counter = 0;
    private int id;
    private String brand;
    private String model;
    private int year;
    private double dailyPrice;
    private boolean isAvailable;
    private String image;
    
    public Car(String brand, String model, int year, double dailyPrice) {
        this.id = getClass().getSimpleName().charAt(0)
				+ "-" + "0".repeat(nullsNumber(idCounter)) 
				+ ID_COUNTER++;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyPrice = dailyPrice;
        this.isAvailable = true; 
        this.image = image;
    }
    
    // Геттеры и сеттеры
    public int getId() { 
    	return id; 
    }

    public String getBrand() {
    	return brand; 
    }
    
    public void setBrand(String brand) { 
    	this.brand = brand; 
    }
    
    public String getModel() { 
    	return model; 
    }
    
    public void setModel(String model) { 
    	this.model = model; 
    }
    
    public int getYear() { 
    	return year; 
    }
    
    public void setYear(int year) { 
    	this.year = year; 
    }
    
    public double getDailyPrice() { 
    	return dailyPrice; 
    }
    
    public void setDailyPrice(double dailyPrice) { 
    	this.dailyPrice = dailyPrice; 
    }
    
    public boolean isAvailable() { 
    	return isAvailable; 
    }
    
    public void setAvailable(boolean isAvailable) { 
    	this.isAvailable = isAvailable; 
    }
    
    public void markAsRented() {
        this.isAvailable = false;
    }
    
    public void markAsAvailable() {
        this.isAvailable = true;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    private int nullsNumber(int number) {
        if (number < 10) {
            return 2;
        } else if (number < 100) {
            return 1;
        } else {
            return 0;
        }
        
    
    @Override
    public String toString() {
        return "Car{" + 
        		"id=" + id + 
        		", brand=" + brand + 
        		", model=" + model +
        		", year=" + year + 
        		", dailyPrice=" + dailyPrice + 
        		", isAvailable=" + isAvailable + 
        		", image='" + image + '\'' +
        		'}';
    }
}