package application.models;

import java.io.Serializable;

import application.exceptions.InvalidShopDataException;
import application.services.ValidationService;

/**
 * Represents a shop with a name, city, and address.
 */
public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String city;
    private String address;
    
    /**
     * Constructs a new Shop with the given parameters.
     *
     * @param name    the shop name.
     * @param city    the shop city.
     * @param address the shop address.
     * @throws InvalidShopDataException if any parameter is null or empty.
     */
    public Shop(String name, String city, String address) throws InvalidShopDataException {
        ValidationService.validateShopName(name);
        ValidationService.validateShopCity(city);
        ValidationService.validateShopAddress(address);
        
        this.name = name;
        this.city = city;
        this.address = address;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) throws InvalidShopDataException {
        ValidationService.validateShopName(name);
        this.name = name;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) throws InvalidShopDataException {
        ValidationService.validateShopCity(city);
        this.city = city;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) throws InvalidShopDataException {
        ValidationService.validateShopAddress(address);
        this.address = address;
    }
    
    @Override
    public String toString() {
        return "Shop [name=" + name + ", city=" + city + ", address=" + address + "]";
    }
}