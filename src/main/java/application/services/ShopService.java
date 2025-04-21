package application.services;

import application.models.Shop;
import application.repositories.ShopRepository;
import application.exceptions.InvalidShopDataException;

/**
 * ShopService provides CRUD operations for Shop objects.
 */
public class ShopService {

    private ShopRepository shopRepository;
    
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
        ValidationService.validateShopName(name);
        ValidationService.validateShopCity(city);
        ValidationService.validateShopAddress(address);
        
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
     * Updates an existing shop.
     *
     * @param shop the Shop object with updated data.
     * @return true if update was successful; false otherwise.
     * @throws InvalidShopDataException if validation fails.
     */
    public boolean updateShop(Shop shop) throws InvalidShopDataException {
        ValidationService.validateShopName(shop.getName());
        ValidationService.validateShopCity(shop.getCity());
        ValidationService.validateShopAddress(shop.getAddress());
        
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
}