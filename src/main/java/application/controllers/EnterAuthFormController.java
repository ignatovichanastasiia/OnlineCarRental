package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.GUI.CarSelectionForm;
import application.models.Client;
import application.models.Rental;
import application.services.CarService;
import application.services.ClientService;
import application.services.RentalService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EnterAuthFormController implements Initializable {
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

	public EnterAuthFormController(AppContext context, CarService carService, ClientService clientService,
			RentalService rentalService) {
		System.out.println("EnterAuthFormController CREATED");
		this.context = context;
		this.carService = carService;
		this.clientService = clientService;
		this.rentalService = rentalService;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Init working");
		enterButton.setOnAction(this::takeEnterForm);
	}

	@FXML
	public void takeEnterForm(ActionEvent e) {
		System.out.println("Button working");

		String clientLogin = loginField.getText();
		String clientPassword = passwordField.getText();
		if (clientLogin.isBlank() || clientPassword.isBlank()) {

			if (!firstNameField.getText().isBlank() && !familyNameField.getText().isBlank()
					&& !phoneNumberField.getText().isBlank() && !emailField.getText().isBlank()) {

				String clientFirstName = firstNameField.getText();
				String clientFamilyName = familyNameField.getText();
				String clientPhone = phoneNumberField.getText();
				String clientEmail = emailField.getText();
				// String name, String email, String phone
				String clientName = clientFirstName + " " + clientFamilyName;
				try {
					Client client = new Client(clientName, clientEmail, clientPhone);
					Rental rental = new Rental(client);
					rentalService.setCurrentRental(rental);
				} catch (Exception exep) {
					System.out.println("The data is not valid: " + exep.getMessage());
					inputInfoLabel.setText("The data is not valid");
					return;
				}
			} else {
				inputInfoLabel.setText("Enter your info for start");
				inputInfoLabel.setStyle("-fx-text-fill: red;");
				return;
			}
			if (!clientLogin.isBlank() && !clientPassword.isBlank()) {
				System.out.println("Here logic for work with login and password");
				inputInfoLabel.setText("Enter your info for start");
				inputInfoLabel.setStyle("-fx-text-fill: red;");
				loginField.clear();
				passwordField.clear();
				return;
			}

			//new window
			Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			new CarSelectionForm().startCarSelectionForm(currentStage, context);
		}

	}

}
