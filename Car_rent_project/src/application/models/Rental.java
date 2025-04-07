package application.models;

import java.util.Date;

public class Rental implements Serializable {
	private static final long serialVersionUID = 1L; // Версия класса для сериализации
	
    private static int counter = 0;
    private int id;
    private Client client;
    private Car car;
    private Date rentalStartDate;
    private Date rentalEndDate;
    private String pickUpLocation;
    private String deliveryLocation;
    
    public Rental(Client client, Car car, Date rentalStartDate, Date rentalEndDate,
                  String pickUpLocation, String deliveryLocation) {
        this.id = getClass().getSimpleName().charAt(0)
				+ "-" + "0".repeat(nullsNumber(idCounter)) 
				+ ID_COUNTER++;
        this.client = client;
        this.car = car;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.pickUpLocation = pickUpLocation;
        this.deliveryLocation = deliveryLocation;
    }
    
    // Геттеры и сеттеры
    public int getId() { 
    	return id; 
    	}
    
    public Client getClient() { 
    	return client; 
    	}
    
    public void setClient(Client client) { 
    	this.client = client; 
    	}
    
    public Car getCar() { 
    	return car; 
    	}
    
    public void setCar(Car car) { 
    	this.car = car; 
    	}
    
    public Date getRentalStartDate() { 
    	return rentalStartDate; 
    	}
    
    public void setRentalStartDate(Date rentalStartDate) { 
    	this.rentalStartDate = rentalStartDate; 
    	}
    
    public Date getRentalEndDate() { 
    	return rentalEndDate; 
    	}
    
    public void setRentalEndDate(Date rentalEndDate) { 
    	this.rentalEndDate = rentalEndDate; 
    	}
    
    public String getPickUpLocation() { 
    	return pickUpLocation; 
    	}
    
    public void setPickUpLocation(String pickUpLocation) { 
    	this.pickUpLocation = pickUpLocation; 
    	}
    
    public String getDeliveryLocation() { 
    	return deliveryLocation; 
    	}
    
    public void setDeliveryLocation(String deliveryLocation) { 
    	this.deliveryLocation = deliveryLocation; 
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
        return "Rental{" +
               "id=" + id +
               ", client=" + client.getName() +
               ", car=" + car.getMake() + " " + car.getModel() +
               ", rentalStartDate=" + rentalStartDate +
               ", rentalEndDate=" + rentalEndDate +
               "}";
    }
}
