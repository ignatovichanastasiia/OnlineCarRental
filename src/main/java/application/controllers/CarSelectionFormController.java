package application.controllers;

import java.awt.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import application.models.Car;
import application.services.CarService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CarSelectionFormController implements Initializable{
	private CarService carService;
	
	private List<Car> researchedCarsByfirstBrand = new ArrayList<Car>();;
	private List<Car> researchedCarsBysecondBrand = new ArrayList<Car>();;
	private List<Car> researchedCarsBythirdBrand = new ArrayList<Car>();;
	private List<Car> allResearchedCarsByBrands = new ArrayList<Car>();
	private List<Car> allCars = carService.listAvailableCars();
	
	//hardcode lists with dates for test 
	private static List<String> citiesList = Arrays.asList("Cities","Rishon LeZion", "Tel Aviv", "Ramat Gan");
	private static List<String> pointsAll = Arrays.asList("All");
	private static List<String> pointsRL = Arrays.asList("All shops RL", "Shop 1", "Shop 2", "Shop3");
	private static List<String> pointsTA = Arrays.asList("All shops TA","Shop 4", "Shop 5");
	private static List<String> pointsRG = Arrays.asList("All shops RG","Shop 6", "Shop 7", "Shop8");
	private static List<String> brands = Arrays.asList("Reno","Toyota","Tesla");

	//filters
	@FXML 
	private ChoiceBox<String> cities;
	
	@FXML 
	private ChoiceBox<String> points;
	
	@FXML 
	private DatePicker dateFrom;
	
	@FXML
	private DatePicker dateTo;
	
	@FXML
	private ChoiceBox<String> drivingExperience;
	
	@FXML
	private ChoiceBox<String> brand1;
	
	@FXML
	private ChoiceBox<String> brand2;
	
	@FXML
	private ChoiceBox<String> brand3; 
	
	@FXML
	private Label minPrice;
	
	@FXML
	private Label maxnPrice;
	
	//cars
	@FXML
	private ListView carsList;
	
	//extra services
	@FXML
	private String pickUpAddress;
	
	@FXML
	private CheckBox insurance;
	
	@FXML
	private CheckBox GPS;
	
	@FXML
	private CheckBox child;
	
	@FXML
	private CheckBox moreDriver;
	
	@FXML
	private CheckBox wifi;
	
	@FXML
	private CheckBox crossborder;
	
	@FXML
	private CheckBox tank;
	
	@FXML
	private Button complete;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//prepare cities CBox
		cities = new ChoiceBox<String>();
		ObservableList<String> citiesItems = FXCollections.observableArrayList(citiesList);
		cities.setItems(citiesItems);
		cities.setOnAction(this::getCitiesChoice);
		//prepare points CBox
		points = new ChoiceBox<String>();
		ObservableList<String> pointsItems = FXCollections.observableArrayList(pointsAll);
		points.setItems(pointsItems);
		points.setOnAction(this::getPointsChoice);
		//prepare drivingExperience (drivingYears) CBox
		drivingExperience = new ChoiceBox<>();
		ObservableList<String> driveYears = FXCollections.observableArrayList(Arrays.asList("more than 2 years","more than 3 years"));
		drivingExperience.setItems(driveYears);
		drivingExperience.setOnAction(this::getDrivingYearsChoice);
		//prepare brands CBox
		brand1 = new ChoiceBox<String>();
		brand2 = new ChoiceBox<String>();
		brand3 = new ChoiceBox<String>();
		List<String> brands1 = brands;
		brands1.addFirst("All brands");
		List<String> brands2 = brands;
		brands2.addFirst(" + brand");
		ObservableList<String> brandFirstItems = FXCollections.observableArrayList(brands1);
		brand1.setItems(brandFirstItems);
		ObservableList<String> brandLastItems = FXCollections.observableArrayList(brands2);
		brand1.setItems(brandFirstItems);
		brand2.setItems(brandLastItems);
		brand3.setItems(brandLastItems);
		carsList = new ListView<>();
		carsList.setItems(FXCollections.observableArrayList(allCars));
	}
	
	public void getCitiesChoice(ActionEvent e) {
		System.out.println("There is no software logic here now, but in the future this choice will affect \nthe list of cars, since each point of sale has its own list of cars (points on diferent cities).");
	}
	
	public void getPointsChoice(ActionEvent e) {
		System.out.println("There is no software logic here now, but in the future this choice will affect \nthe list of cars, since each point of sale has its own list of cars.");
	}
	
	public void getDrivingYearsChoice(ActionEvent e) {
		System.out.println("There is no software logic behind this at the moment, \nbut in the future this choice may affect the availability of certain classes of cars.");
	}
	
	public void getFirstBrandsForSearch(ActionEvent e) {
		if(!allResearchedCarsByBrands.isEmpty()) {
			if(!researchedCarsByfirstBrand.isEmpty()) {
				allResearchedCarsByBrands.removeAll(researchedCarsByfirstBrand);
			}
		}
		String brand = brand1.getValue();
//TODO		researchedCarsByfirstBrand = carService.getListCarByBrand(brand);
		allResearchedCarsByBrands.addAll(researchedCarsByfirstBrand);
	}
	
	public void getSecondBrandsForSearch(ActionEvent e) {
		if(!allResearchedCarsByBrands.isEmpty()) {
			if(!researchedCarsBysecondBrand.isEmpty()) {
				allResearchedCarsByBrands.removeAll(researchedCarsBysecondBrand);
			}
		}
		String brand = brand2.getValue();
//TODO		researchedCarsBysecondBrand = carService.getListCarByBrand(brand);
		allResearchedCarsByBrands.addAll(researchedCarsBysecondBrand);
	}
	
	public void getThirdBrandForSearch(ActionEvent e) {
		if(!allResearchedCarsByBrands.isEmpty()) {
			if(!researchedCarsBythirdBrand.isEmpty()) {
				allResearchedCarsByBrands.removeAll(researchedCarsBythirdBrand);
			}
		}
		String brand = brand3.getValue();
//TODO		researchedCarsBythirdBrand = carService.getListCarByBrand(brand);
		allResearchedCarsByBrands.addAll(researchedCarsBythirdBrand);
	}
	
	public void getDateStartRental() {
		
	}
	
	public void getDateEndRental() {
		
	}
}
