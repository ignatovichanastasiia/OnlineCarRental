package application.services;

import java.util.List;
import java.util.stream.Collectors;

import application.models.Shop;
import application.repositories.ShopRepository;

public class ShopService {
	private ShopRepository shopRepository;

	public ShopService(ShopRepository shopRepository) {
		// TODO Auto-generated constructor stub
	}

	public List<String> getAllShopsCitiesList() {
		return shopRepository.getShops().stream().map(Shop::getCity).collect(Collectors.toList());
	}

	public List<String> getAllShopsNames() {
		return shopRepository.getShops().stream().map(Shop::getName).collect(Collectors.toList());
	}

	public List<String> getShopNamesByCity(String city) {
		return shopRepository.getShops().stream().filter(t -> t.getCity().equalsIgnoreCase(city)).map(Shop::getName)
				.collect(Collectors.toList());
	}
}
