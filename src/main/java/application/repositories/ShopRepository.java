package application.repositories;

import application.models.Shop;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing Shop objects.
 * Maintains an internal list and supports serialization/deserialization.
 */
public class ShopRepository {
    private static final String SHOPS = "shops.dat";
    private List<Shop> shopList;
    
    public ShopRepository() {
        shopList = loadShopsFromFile();
        if (shopList == null) {
            shopList = new ArrayList<>();
        }
    }
    
    public List<Shop> getAllShops() {
        return shopList;
    }
    
    public Shop getShopByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Shop name for search cannot be null or empty.");
        }
        return shopList.stream()
                .filter(shop -> shop.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    public void addShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Cannot add a null shop.");
        }
        shopList.add(shop);
        saveShopsToFile();
    }
    
    public boolean updateShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Cannot update a null shop.");
        }
        for (int i = 0; i < shopList.size(); i++) {
            if (shopList.get(i).getName().equals(shop.getName())) { 
                shopList.set(i, shop);
                saveShopsToFile();
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteShop(String name) {
        boolean removed = shopList.removeIf(shop -> shop.getName().equals(name));
        if (removed) {
            saveShopsToFile();
        }
        return removed;
    }
    
    private void saveShopsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SHOPS))) {
            oos.writeObject(shopList);
        } catch (IOException e) {
            System.err.println("Error saving shops: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    private List<Shop> loadShopsFromFile() {
        File file = new File(SHOPS);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Shop>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading shops: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}