package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.GUI.ClientForm;
import application.services.DriversLicenseService;
import application.services.RentalService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DriversLicenseFormController implements Initializable {
	private AppContext context;
	private RentalService rentalService;
	private DriversLicenseService driversLicenseService;
	private boolean validCategory;

	public DriversLicenseFormController(AppContext context, RentalService rentalService,
			DriversLicenseService driversLicenseService) {
		this.context = context;
		this.rentalService = rentalService;
		this.driversLicenseService = driversLicenseService;
	}

	@FXML
	private Label maindriverLabel;
	@FXML
	private TextField taudatZehud;
	@FXML
	private DatePicker birth;
	@FXML
	private TextField licenseNumber;
	@FXML
	private CheckBox checkBoxCategory;
	@FXML
	private DatePicker issueDate;
	@FXML
	private DatePicker expirationDate;
	@FXML
	private Button addLicense;
	@FXML
	Label llicenseCheckLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addLicense.setOnAction(eve -> {
			System.out.println("Click to addButton!");
			addLicenseButtonClick(eve);
		});

	}

	public void checkCategory(ActionEvent e) {
		validCategory = checkBoxCategory.isSelected();
	}

	public void addLicenseButtonClick(ActionEvent e) {
		if (!validCategory) {
			llicenseCheckLabel.setText("License is not valid");
			llicenseCheckLabel.setStyle("-fx-text-fill: red;");
			return;
		}
		String taudatZehudNumber = taudatZehud.getText();
		LocalDate clientBirth = birth.getValue();
		String strLicenseNumber = licenseNumber.getText();
		LocalDate dateIssue = issueDate.getValue();
		LocalDate dateExpir = expirationDate.getValue();
		try {
			driversLicenseService.createDriversLicense(taudatZehudNumber, strLicenseNumber, clientBirth, dateIssue,
					dateExpir);
			rentalService.getCurrentRental().getClient().setDriversLicenseNumber(strLicenseNumber);
		} catch (Exception ex) {
			System.out.println("Problem with license validation, data is not valid: " + ex.getMessage());
			llicenseCheckLabel.setText("License is not valid");
			llicenseCheckLabel.setStyle("-fx-text-fill: red;");
			return;
		}
		
		//здесь нужно закрыть окно прав и открыть карточку
		Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		new ClientForm().startClientForm(currentStage, context);
	}

	// public DriversLicense(String numberTZ, String id, LocalDate dateBirth,
	// LocalDate issue,
	// LocalDate expiration)
}
