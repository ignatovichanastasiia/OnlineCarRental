package application.services;

import application.models.Shop;
import application.repositories.ShopRepository;
import application.exceptions.InvalidShopDataException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ShopService provides CRUD operations for Shop objects.
 */
public class ShopService {

    private ShopRepository shopRepository;
    
    /**
     * Constructs a new ShopService with the provided ShopRepository.
     *
     * @param shopRepository the repository responsible for shop persistence.
     */
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }
    
    /**
     * Creates a new shop after validating the input data.
     *
     * @param name    the shop name.
     * @param city    the shop city.
     * @param address the shop address.
     * @return the newly created Shop.
     * @throws InvalidShopDataException if validation fails.
     */
    public Shop createShop(String name, String city, String address) throws InvalidShopDataException {
        // Validate input data using ValidationService
        ValidationService.validateShopName(name);
        ValidationService.validateShopCity(city);
        ValidationService.validateShopAddress(address);
        
        // Create a new Shop and persist it
        Shop shop = new Shop(name, city, address);
        shopRepository.addShop(shop);
        return shop;
    }
    
    /**
     * Retrieves a shop by its name.
     *
     * @param name the shop name.
     * @return the Shop if found; otherwise, null.
     */
    public Shop getShopByName(String name) {
        return shopRepository.getShopByName(name);
    }
    
    /**
     * Updates an existing shop after validating the new data.
     *
     * @param shop the Shop object with updated data.
     * @return true if update was successful; false otherwise.
     * @throws InvalidShopDataException if validation fails.
     */
    public boolean updateShop(Shop shop) throws InvalidShopDataException {
        // Validate updated shop data
        ValidationService.validateShopName(shop.getName());
        ValidationService.validateShopCity(shop.getCity());
        ValidationService.validateShopAddress(shop.getAddress());
        
        // Delegate the update to the repository
        return shopRepository.updateShop(shop);
    }
    
    /**
     * Deletes a shop by its name.
     *
     * @param name the shop name.
     * @return true if deletion was successful; false otherwise.
     */
    public boolean deleteShop(String name) {
        return shopRepository.deleteShop(name);
    }
    
    /**
     * Retrieves a distinct list of cities from all shops.
     *
     * @return List<String> - список уникальных городов магазинов.
     */
    public List<String> getAllShopsCitiesList() {
        return shopRepository.getAllShops()
                             .stream()
                             .map(Shop::getCity)
                             .distinct()
                             .collect(Collectors.toList());
    }
    
    /**
     * Retrieves a list of names for all shops.
     *
     * @return List<String> - список названий магазинов.
     */
    public List<String> getAllShopsNames() {
        return shopRepository.getAllShops()
                             .stream()
                             .map(Shop::getName)
                             .collect(Collectors.toList());
    }
    
    /**
     * Retrieves a list of shop names for a specific city.
     *
     * @param city The city for which to filter shop names.
     * @return List<String> - список названий магазинов из указанного города.
     */
    public List<String> getShopNamesByCity(String city) {
        return shopRepository.getAllShops()
                             .stream()
                             .filter(shop -> shop.getCity().equals(city))
                             .map(Shop::getName)
                             .collect(Collectors.toList());
    }
}