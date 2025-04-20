package application.controllers;

import java.awt.event.ActionEvent;

import application.services.CarService;
import application.services.ClientService;
import application.services.RentalService;
import javafx.fxml.FXML;

public class EnterAuthFormController {
	private CarService carService;
	private ClientService clientService;
	private RentalService rentalService;
	
	@FXML
	Lable leftEnterLabel;
	
	@FXML
	TextField loginField;
	
	@FXML
	TextField passwordField;
	
	@FXML
	Label inputInfoLabel;
	
	@FXML
	TextField firstNameField;
	
	@FXML
	TextField familyNameField;
	
	@FXML
	TextField phoneNumberField;
	
	@FXML
	TextField emailField;
	
	@FXML
	Button enterButton;
	
	
	public EnterAuthFormController(CarService carService, ClientService clientService, RentalService rentalService) {
		this.carService = carService;
		this.clientService = clientService;
		this.rentalService = rentalService;
	}
	
	public void takeEnterForm(ActionEvent e){
		
		
	}

}
