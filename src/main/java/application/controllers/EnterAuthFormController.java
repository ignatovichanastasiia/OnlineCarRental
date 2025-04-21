package application.controllers;

import application.GUI.CarSelectionForm;
import application.models.Client;
import application.models.Rental;
import application.services.CarService;
import application.services.ClientService;
import application.services.RentalService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EnterAuthFormController {
	private CarService carService;
	private ClientService clientService;
	private RentalService rentalService;
	private AppContext context;
	
	@FXML
	Label leftEnterLabel;
	
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
	
	
	public EnterAuthFormController(AppContext context, CarService carService, ClientService clientService, RentalService rentalService) {
		this.context = context;
		this.carService = carService;
		this.clientService = clientService;
		this.rentalService = rentalService;
	}
	
	public void takeEnterForm(ActionEvent e){
		String clientLogin = loginField.getText();
		String clientPassword = passwordField.getText();
		if(clientLogin.isBlank()||clientPassword.isBlank()) {
			String clientFirstName = firstNameField.getText();
			String clientFamilyName = familyNameField.getText();
			String clientPhone = phoneNumberField.getText();
			String clientEmail = emailField.getText();
			//String name, String email, String phone
			String clientName = clientFirstName+" "+clientFamilyName;
			try {
			Client client = new Client(clientName,clientEmail,clientPhone); //TODO ПРОВЕРИТЬ КЛИЕНТА С КОНСТРУКТОРОМ
			Rental rental = new Rental(client); //TODO РЕНТАЛ С КОНСТРУКТОРОМ КЛИЕНТ
			rentalService.setRental(rental); //TODO do this setter
			}catch(Exception exep) {
				System.out.println("The data is not valid: "+exep.getMessage());
				inputInfoLabel.setText("The data is not valid");
				return;
			}
			
	        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        new CarSelectionForm().startCarSelectionForm(currentStage, context);
			
			
		}
		
	}

}
