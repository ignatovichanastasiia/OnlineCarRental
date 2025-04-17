package application.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import application.models.Shop;
import application.repositories.ShopRepository;

public class ShopService {
	private ShopRepository shopRepository;
	
	
	private static List<String> citiesList = Arrays.asList("Cities","Rishon LeZion", "Tel Aviv", "Ramat Gan");
	private static List<String> pointsAll = Arrays.asList("All");
	private static List<String> pointsRL = Arrays.asList("All shops RL", "Shop 1", "Shop 2", "Shop3");
	private static List<String> pointsTA = Arrays.asList("All shops TA","Shop 4", "Shop 5");
	private static List<String> pointsRG = Arrays.asList("All shops RG","Shop 6", "Shop 7", "Shop8");
	
	public List<String> getAllShopsCitiesList(){
		
		return shopRepository.getShops().stream().map(Shop::getCity).collect(Collectors.toList());
	}
	
	public List<String> getAllShopsNames(){
		return shopRepository.getShops().stream().map(Shop::getName).collect(Collectors.toList());
	}
	
	public List<String> getShopNamesByCity
	
	
}
