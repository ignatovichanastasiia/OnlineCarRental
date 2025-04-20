package application.controllers;

import java.net.URL;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import application.services.CardService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClientFormController implements Initializable {
	private CardService cardService;
	private int intCardMonth;
	private int intCardYear;

	public ClientFormController(CardService cardService) {
		this.cardService = cardService;
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
	}
	
	public void getCardMonth(ActionEvent e) {
		int intCardMonth = (int)cardMonth.getValue();
	}
	
	public void getCardYear(ActionEvent e) {
		int intCardYear = (int)cardYear.getValue();
	}
	
	public void addCard() {
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
		//Card(String number, String firstName, String lastName, String email, String phone,
		//String cvv, int month, int year, String cardholder)
		cardService.addCard(strCardNumber,clientFirstName,clientLastName,clientEmail, clientPhone,cardCvv,intCardMonth,intCardYear,cardHolder);
		//закрыть окно 
			
	}
	
}
