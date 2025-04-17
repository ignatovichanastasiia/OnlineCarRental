package application.repositories;

import java.util.ArrayList;
import java.util.List;

import application.models.Shop;

//ЗАГЛУШКА!! 

//TODO

public class ShopRepository {
	private List<Shop> shops;
	
	public ShopRepository(){
	shops = new ArrayList<>();
	//String name, String city, String address
	shops.add(new Shop("SellMotorsTelAviv","Tel-Aviv","Gde-to Tam,50"));
	shops.add(new Shop("SellRehavTelAviv","Tel-Aviv","Gde-to Tam,30"));
	shops.add(new Shop("SellMotorsRamatGan","Ramat Gan","Gde-to Tam,55"));
	shops.add(new Shop("SellRehavRamatGan","Ramat Gan","Gde-to Tam,58"));
	shops.add(new Shop("SellMotorsRishonLezion","Rishon Lezion","Gde-to Tam,166"));
	shops.add(new Shop("SellRehavRishonLezion","Rishon Lezion","Gde-to Tam,80"));
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}
	
}
