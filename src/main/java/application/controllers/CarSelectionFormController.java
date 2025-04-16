package application.controllers;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CarSelectionFormController {

	//hardcode lists with dates for test 
	private static List<String> citiesList = Arrays.asList("Cities","Rishon LeZion", "Tel Aviv", "Ramat Gan");
	private static List<String> pointsAll = Arrays.asList("All");
	private static List<String> pointsRL = Arrays.asList("All shops RL", "Shop 1", "Shop 2", "Shop3");
	private static List<String> pointsTA = Arrays.asList("All shops TA","Shop 4", "Shop 5");
	private static List<String> pointsRG = Arrays.asList("All shops RG","Shop 6", "Shop 7", "Shop8");
	private static List<String> brands = Arrays.asList("Reno","Toyota","Tesla");

	//filters
	@FXML 
	private ChoiceBox cities;
	
	@FXML 
	private ChoiceBox points;
	
	@FXML 
	private DatePicker dateFrom;
	
	@FXML
	private DatePicker dateTo;
	
	@FXML
	private ChoiceBox drivingExperience;
	
	@FXML
	private ChoiceBox brand1;
	
	@FXML
	private ChoiceBox brand2;
	
	@FXML
	private ChoiceBox brand3; 
	
	@FXML
	private Label minPrice;
	
	@FXML
	private Label maxnPrice;
	
	//cars
	@FXML
	private ListView cars;
	
	//
	
	@FXML
	private void initialize() {
		cities = new ChoiceBox<>();
		ObservableList<String> citiesItems = FXCollections.observableArrayList(citiesList);
		cities.setItems(citiesItems);
		points = new ChoiceBox<>();
		ObservableList<String> pointsItems = FXCollections.observableArrayList(pointsAll);
		drivingExperience = new ChoiceBox<>();
		ObservableList<String> driveYears = FXCollections.observableArrayList(Arrays.asList("more than 2 years","more than 3 years"));
		brand1 = new ChoiceBox<>();
		List<String> brands1 = brands;
		brands1.addFirst("All brands");
		List<String> brands2 = brands;
		brands2.addFirst(" + brand");
		ObservableList<String> brandFirstItems = FXCollections.observableArrayList(brands1);
		brand1.setItems(brandFirstItems);
		ObservableList<String> brandLastItems = FXCollections.observableArrayList(brands2);
		brand2.setItems(brandLastItems);
		brand3.setItems(brandLastItems);
		cars = new ListView<>();
		cars.setItems(FXCollections.observableArrayList("Wait cars..."));
	}
	
}
