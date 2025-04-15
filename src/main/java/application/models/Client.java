package application.models;

public class Client implements Serializable {
	private static final long serialVersionUID = 1L; // Версия класса для сериализации
	
    private static int ID_COUNTER = 0;
    private int id;
    private String name;
    private String identityNumber;
    private String driversLicenseNumber;
    private String creditCardNumber;
    private String email;
    private String phone;
    
    public Client(String name, String identityNumber, String driversLicenseNumber,
                  String creditCardNumber, String email, String phone) {
        this.id = getClass().getSimpleName().charAt(0)
				+ "-" + "0".repeat(nullsNumber(idCounter)) 
				+ ID_COUNTER++;
        this.name = name;
        this.identityNumber = identityNumber;
        this.driversLicenseNumber = driversLicenseNumber;
        this.creditCardNumber = creditCardNumber;
        this.email = email;
        this.phone = phone;
    }
    
    // Геттеры и сеттеры
    public int getId() { 
    	return id; 
    	}
    
    public String getName() { 
    	return name; 
    	}
    
    public void setName(String name) { 
    	this.name = name; 
    	}
    
    public String getIdentityNumber() { 
    	return identityNumber; 
    	}
    
    public void setIdentityNumber(String identityNumber) { 
    	this.identityNumber = identityNumber; 
    	}
    
    public String getDriversLicenseNumber() { 
    	return driversLicenseNumber; 
    	}
    
    public void setDriversLicenseNumber(String driversLicenseNumber) { 
    	this.driversLicenseNumber = driversLicenseNumber; 
    	}
    
    public String getCreditCardNumber() { 
    	return creditCardNumber; 
    	}
    
    public void setCreditCardNumber(String creditCardNumber) { 
    	this.creditCardNumber = creditCardNumber; 
    	}
    
    public String getEmail() { 
    	return email; 
    	}
    
    public void setEmail(String email) { 
    	this.email = email; 
    	}
    
    public String getPhone() { 
    	return phone; 
    	}
    
    public void setPhone(String phone) { 
    	this.phone = phone; 
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
        return "Client{" + "id=" + id + ", name=" + name + ", email=" + email + "}";
    }
    
}