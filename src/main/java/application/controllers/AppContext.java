package application.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.repositories.CarRepository;
import application.repositories.ClientRepository;
import application.repositories.RentalRepository;
import application.repositories.ShopRepository;
import application.services.CarService;
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

	private final CarService carService;
	private final ClientService clientService;
	private final RentalService rentalService;
	private final ShopService shopService;
	private final ValidationService validationService;

	public AppContext() {
		try {
			// H2
			this.connection = DriverManager.getConnection("jdbc:h2:~/onlinecarrental", "sa", "");

			// repo
			this.carRepository = new CarRepository(connection);
			this.clientRepository = new ClientRepository(connection);
			this.rentalRepository = new RentalRepository(connection);
			this.shopRepository = new ShopRepository(connection);

			// services
			this.carService = new CarService(carRepository);
			this.clientService = new ClientService(clientRepository);
			this.rentalService = new RentalService(rentalRepository);
			this.shopService = new ShopService(shopRepository);
			this.validationService = new ValidationService();

		} catch (SQLException e) {
			throw new RuntimeException("Error init AppContext", e);
		}
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