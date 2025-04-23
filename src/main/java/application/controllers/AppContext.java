package application.controllers;

import application.repositories.CarRepository;
import application.repositories.CardRepository;
import application.repositories.ClientRepository;
import application.repositories.DriversLicenseRepository;
import application.repositories.RentalRepository;
import application.repositories.ShopRepository;
import application.services.CarService;
import application.services.CardService;
import application.services.ClientService;
import application.services.DriversLicenseService;
import application.services.RentalService;
import application.services.ShopService;
import application.services.ValidationService;

public class AppContext {

	private CarRepository carRepository;
	private ClientRepository clientRepository;
	private RentalRepository rentalRepository;
	private ShopRepository shopRepository;
	private CardRepository cardRepository;
	private DriversLicenseRepository driversLicenseRepository;

	private CarService carService;
	private ClientService clientService;
	private RentalService rentalService;
	private ShopService shopService;
	private ValidationService validationService;
	private CardService cardService;
	private DriversLicenseService driversLicenseService;

	public AppContext() {
		// repo
		this.carRepository = new CarRepository();
		this.clientRepository = new ClientRepository();
		this.rentalRepository = new RentalRepository();
		this.shopRepository = new ShopRepository();
		this.cardRepository = new CardRepository();
		this.driversLicenseRepository = new DriversLicenseRepository();

		// services
		this.carService = new CarService(carRepository);
		this.clientService = new ClientService(clientRepository);
		this.rentalService = new RentalService(rentalRepository, carRepository);
		this.shopService = new ShopService(shopRepository);
		this.validationService = new ValidationService();
		this.cardService = new CardService(cardRepository);
		this.driversLicenseService = new DriversLicenseService(driversLicenseRepository);
	}

	public CarRepository getCarRepository() {
		return carRepository;
	}

	public ClientRepository getClientRepository() {
		return clientRepository;
	}

	public RentalRepository getRentalRepository() {
		return rentalRepository;
	}

	public ShopRepository getShopRepository() {
		return shopRepository;
	}

	public CardRepository getCardRepository() {
		return cardRepository;
	}

	public CarService getCarService() {
		return carService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public RentalService getRentalService() {
		return rentalService;
	}

	public ShopService getShopService() {
		return shopService;
	}

	public ValidationService getValidationService() {
		return validationService;
	}

	public CardService getCardService() {
		return cardService;
	}

	public DriversLicenseRepository getDriversLicenseRepository() {
		return driversLicenseRepository;
	}

	public DriversLicenseService getDriversLicenseService() {
		return driversLicenseService;
	}
}