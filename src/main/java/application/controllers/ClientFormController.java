package application.controllers;

import java.net.URL;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import application.GUI.ConfirmationForm;
import application.exceptions.InvalidClientDataException;
import application.exceptions.InvalidCreditCardNumberException;
import application.exceptions.InvalidEmailException;
import application.services.CardService;
import application.services.RentalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//all ok
public class ClientFormController implements Initializable {
	private AppContext context;
	private CardService cardService;
	private RentalService rentalService;
	private int intCardMonth;
	private int intCardYear;

	public ClientFormController(AppContext context, CardService cardService, RentalService rentalService) {
		this.context = context;
		this.cardService = cardService;
		this.rentalService = rentalService;
	}

	@FXML
	private Label cardInsert;
	
	@FXML 
	private Button addCard;
	
	@FXML
	private TextField firstName;
	
	@FXML
	private TextField lastName;
	
	@FXML
	private TextField emailFPart;
	
	@FXML
	private TextField emailSPart;
	
	@FXML
	private TextField phoneRegion;
	
	@FXML
	private TextField phoneCode;
	
	@FXML
	private TextField phoneNumber;
	
	@FXML
	private TextField cardNumber;
	
	@FXML
	private ChoiceBox<Integer> cardMonth;
	
	@FXML
	private ChoiceBox<Integer> cardYear;
	
	@FXML
	private TextField cvv;
	
	@FXML
	private TextField cardholder;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	    ObservableList<Integer> months = FXCollections.observableArrayList(
	        Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
	    cardMonth.setItems(months);
	    cardMonth.setOnAction(this::getCardMonth);

	    int currentYear = Year.now().getValue();
	    List<Integer> years = IntStream.rangeClosed(currentYear - 10, currentYear + 10)
	                                   .boxed()
	                                   .collect(Collectors.toList());

	    ObservableList<Integer> yearsBox = FXCollections.observableArrayList(years);
	    cardYear.setItems(yearsBox);
	    cardYear.setOnAction(this::getCardYear);
	    addCard.setOnAction(event -> {
	        System.out.println("Addcard button click!");
	        addCard(event);
	    });
	}
	
	public void getCardMonth(ActionEvent e) {
		int intCardMonth = (int)cardMonth.getValue();
	}
	
	public void getCardYear(ActionEvent e) {
		int intCardYear = (int)cardYear.getValue();
	}
	
	public void addCard(ActionEvent e) {
		String clientFirstName = firstName.getText().trim();
		String clientLastName = lastName.getText().trim();
		if(clientFirstName.isBlank()||clientLastName.isBlank()) {
			cardInsert.setText("Wrong name");
			cardInsert.setStyle("-fx-text-fill: red;");
			return;
		}
		String strCardNumber = cardNumber.getText();
		String email1 = emailFPart.getText().trim();
		String email2 = emailSPart.getText().trim();
		if(email1.isBlank()||email2.isBlank()) {
			cardInsert.setText("Wrong e-mail");
			cardInsert.setStyle("-fx-text-fill: red;");
			return;
		}
		String clientEmail = email1+"@"+email2;
		String clientPhone = phoneRegion.getText().trim()+phoneCode.getText().trim()+phoneNumber.getText().trim();
		if(clientPhone.isBlank()||!clientPhone.matches("\\d{12}")) {
			cardInsert.setText("Wrong phon number");
			cardInsert.setStyle("-fx-text-fill: red;");
			return;
		}
		String cardCvv = cvv.getText();
		String cardHolder = cardholder.getText();
		
		try {
			cardService.addCard(strCardNumber,clientFirstName,clientLastName,clientEmail, clientPhone,cardCvv,intCardMonth,intCardYear,cardHolder,rentalService.getCurrentRental().getClient().getId());
			rentalService.getCurrentRental().getClient().setCreditCardNumber(strCardNumber);
			context.getRentalRepository().updateRental(rentalService.getCurrentRental());
		} catch (InvalidCreditCardNumberException | InvalidEmailException | InvalidClientDataException ex) {
			ex.printStackTrace();
			cardInsert.setText("Datas are not valid");
			cardInsert.setStyle("-fx-text-fill: red;");
			return;
		}
		
		//Закрываем окно карты, открываем договор!!! 
		//new window
		Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		new ConfirmationForm().startConfirmationForm(currentStage, context);
			
	}
	
}
//Card(String number, String firstName, String lastName, String email, String phone,
//String cvv, int month, int year, String cardholder, String clientAppID) {
