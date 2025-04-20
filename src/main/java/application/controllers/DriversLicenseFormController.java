package application.controllers;

import java.awt.ActiveEvent;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

import application.services.DriversLicenseService;
import application.services.RentalService;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DriversLicenseFormController  {
	private RentalService rentalService;
	private DriversLicenseService driversLicenseService;
	private boolean validCategory;
	
	public DriversLicenseFormController(RentalService rentalService, DriversLicenseService driversLicenseService) {
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
	private Button addButton;
	@FXML
	Label llicenseCheckLabel;
	
	
	public void checkCategory(ActionEvent e) {
		boolean validCategory = checkBoxCategory.isSelected();
	}
	
	public void addLicenseButtonClick(ActiveEvent e) {
		if(!validCategory) {
			llicenseCheckLabel.setText("License is not valid");
			llicenseCheckLabel.setStyle("-fx-text-fill: red;");
			return;
		}
		String taudatZehudNumber = taudatZehud.getText();
		LocalDate clientBirth = birth.getValue();
		String strLicenseNumber = licenseNumber.getText();
		LocalDate dateIssue = issueDate.getValue();
		LocalDate dateExpir = expirationDate.getValue();
		driversLicenseService.addDriversLicense(taudatZehudNumber,strLicenseNumber,clientBirth,dateIssue,dateExpir);
		
	}
	
	//	public DriversLicense(String numberTZ, String id, LocalDate dateBirth, LocalDate issue,
	LocalDate expiration)
}
