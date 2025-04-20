package application.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.repositories.CarRepository;
import application.repositories.CardRepository;
import application.repositories.ClientRepository;
import application.repositories.RentalRepository;
import application.repositories.ShopRepository;
import application.services.CarService;
import application.services.CardService;
import application.services.ClientService;
import application.services.RentalService;
import application.services.ShopService;
import application.services.ValidationService;

public class AppContext {

	private final Connection connection;

	private final CarRepository carRepository;
	private final ClientRepository clientRepository;
	private final RentalRepository rentalRepository;
	private final ShopRepository shopRepository;
	private final CardRepository cardRepository;

	private final CarService carService;
	private final ClientService clientService;
	private final RentalService rentalService;
	private final ShopService shopService;
	private final ValidationService validationService;
	private final CardService cardService;

	public AppContext() {
		try {
			// H2
			this.connection = DriverManager.getConnection("jdbc:h2:~/onlinecarrental", "sa", "");

			// repo
			this.carRepository = new CarRepository(connection);
			this.clientRepository = new ClientRepository(connection);
			this.rentalRepository = new RentalRepository(connection);
			this.shopRepository = new ShopRepository(connection);
			this.cardRepository = new CardRepository();

			// services
			this.carService = new CarService(carRepository);
			this.clientService = new ClientService(clientRepository);
			this.rentalService = new RentalService(rentalRepository,carRepository);
			this.shopService = new ShopService(shopRepository);
			this.validationService = new ValidationService();
			this.cardService = new CardService();
			
		} catch (SQLException e) {
			throw new RuntimeException("Error init AppContext", e);
		}
	}

	public Connection getConnection() {
		return connection;
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


	public void close() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println("Error exiting data base: " + e.getMessage());
		}
	}
}