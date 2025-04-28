package application.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import application.models.Shop;

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
        shopList = generateShops();
    }
    
    public static List<Shop> generateShops() {
        List<Shop> shops = new ArrayList<>();

       try {
        shops.add(new Shop("RentCenter TLV", "Tel Aviv", "Dizengoff 100"));
        shops.add(new Shop("AutoRent North", "Haifa", "Herzl 45"));
        shops.add(new Shop("Beachside Cars", "Bat Yam", "Ben Gurion 3"));
        shops.add(new Shop("JeruDrive", "Jerusalem", "Jaffa 20"));
        shops.add(new Shop("BeerSheva Wheels", "Be'er Sheva", "Derech Eilat 77"));
        shops.add(new Shop("Ashdod Auto", "Ashdod", "HaTmarim 12"));
        shops.add(new Shop("DriveIt Netanya", "Netanya", "Sderot Ben Tzvi 10"));
        shops.add(new Shop("CarSpot Petah Tikva", "Petah Tikva", "Em Hamoshavot 88"));
        shops.add(new Shop("Krayot Cars", "Kiryat Motzkin", "Hahistadrut 15"));
        shops.add(new Shop("Modi'in AutoHub", "Modi'in", "Hahashmonaim 5"));
       }catch(Exception e) {
    	   System.out.println("Не прошли Ванену валидацию");
    	   System.out.println("Shop or shops are not valid"+e.getMessage());
       }

        return shops;
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