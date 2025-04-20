package application.controllers;

import java.awt.Button;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import application.models.Car;
import application.services.CarService;
import application.services.ShopService;
import application.services.ValidationService;
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
import javafx.scene.control.TextField;

public class CarSelectionFormController implements Initializable {
	// Service classes
	private CarService carService;
	private ShopService shopService;

	public CarSelectionFormController(CarService carService, ShopService shopService) {
		this.carService = carService;
		this.shopService = shopService;
	}

	// Lists for Items
	private List<Car> researchedCarsByfirstBrand = new ArrayList<Car>();;
	private List<Car> researchedCarsBysecondBrand = new ArrayList<Car>();;
	private List<Car> researchedCarsBythirdBrand = new ArrayList<Car>();;
	private List<Car> allResearchedCarsByBrands = new ArrayList<Car>();
	private List<Car> allCars = carService.listAvailableCars();
	private List<String> citiesList = shopService.getAllShopsCitiesList();
	private List<String> pointsAll = shopService.getAllShopsNames();
	private List<String> cityPoints;
	private List<String> brands = carService.getBrandList();

	// service object
	private String brand;

	// client information
	private String city;
	private String point;
	private LocalDate localDateFrom;
	private LocalDate localDateTo;

	// filters
	@FXML
	private ChoiceBox<String> cities;

	@FXML
	private ChoiceBox<String> points;

	@FXML
	private DatePicker dateFrom;

	@FXML
	private Label dateFromLabel;

	@FXML
	private DatePicker dateTo;

	@FXML
	private Label dateToLabel;

	@FXML
	private ChoiceBox<String> drivingExperience;

	@FXML
	private ChoiceBox<String> brand1;

	@FXML
	private ChoiceBox<String> brand2;

	@FXML
	private ChoiceBox<String> brand3;

	@FXML
	private TextField minPrice;

	@FXML
	private Label minPriceLabel;

	@FXML
	private TextField maxPrice;

	@FXML
	private Label maxPriceLabel;

	// cars
	@FXML
	private ListView<Car> carsList;

	// extra services
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
		// prepare cities CBox
		cities = new ChoiceBox<String>();
		ObservableList<String> citiesItems = FXCollections.observableArrayList(citiesList);
		cities.setItems(citiesItems);
		cities.setOnAction(this::getCitiesChoice);
		// prepare points CBox
		points = new ChoiceBox<String>();
		ObservableList<String> pointsItems = FXCollections.observableArrayList(pointsAll);
		points.setItems(pointsItems);
		points.setOnAction(this::getPointsChoice);
		// prepare drivingExperience (drivingYears) CBox
		drivingExperience = new ChoiceBox<>();
		ObservableList<String> driveYears = FXCollections
				.observableArrayList(Arrays.asList("more than 2 years", "more than 3 years"));
		drivingExperience.setItems(driveYears);
		drivingExperience.setOnAction(this::getDrivingYearsChoice);
		// prepare brands CBox
		brand1 = new ChoiceBox<String>();
		brand2 = new ChoiceBox<String>();
		brand3 = new ChoiceBox<String>();
		List<String> brands1 = brands;
		brands1.addFirst("All brands");
		List<String> brands2 = brands;
		brands2.addFirst("None");
		ObservableList<String> brandFirstItems = FXCollections.observableArrayList(brands1);
		brand1.setItems(brandFirstItems);
		ObservableList<String> brandLastItems = FXCollections.observableArrayList(brands2);
		brand1.setItems(brandFirstItems);
		brand2.setItems(brandLastItems);
		brand3.setItems(brandLastItems);
		minPrice.setPromptText("Min price is " + carService.getMinPrice());
		maxPrice.setPromptText("Max price is " + carService.getMaxPrice());
		carsList = new ListView<>();
		carsList.setItems(FXCollections.observableArrayList(allCars));
	}

	public void getCitiesChoice(ActionEvent e) {
		// TODO
		System.out.println("CITY. There is no software logic here now, but in the future this choice will affect "
				+ "\nthe list of cars, since each point of sale has its own list of cars "
				+ "\n(points on diferent cities).");
		city = cities.getValue();
		List<String> filteredShops = shopService.getShopNamesByCity(city);
		ObservableList<String> pointsNewItems = FXCollections.observableArrayList(filteredShops);
		points.setItems(pointsNewItems);
	}

	public void getPointsChoice(ActionEvent e) {
		// TODO
		System.out.println("POINT. There is no software logic here now, but in the future this choice will affect "
				+ "\nthe list of cars, since each point of sale has its own list of cars.");
		point = points.getValue();
	}

	public void getDrivingYearsChoice(ActionEvent e) {
		System.out.println(
				"DRIVE EXSP. There is no software logic behind this at the moment, \nbut in the future this choice may affect the availability of certain classes of cars.");

	}

	public void getFirstBrandsForSearch(ActionEvent e) {
		if (!allResearchedCarsByBrands.isEmpty()) {
			if (!researchedCarsByfirstBrand.isEmpty()) {
				allResearchedCarsByBrands.removeAll(researchedCarsByfirstBrand);
			}
		}
		brand = brand1.getValue();
		if (brand.equalsIgnoreCase("All brands")) {
			allResearchedCarsByBrands = allCars;
			researchedCarsByfirstBrand = new ArrayList<Car>();
		}
//TODO		researchedCarsByfirstBrand = carService.getListCarByBrand(brand);
		allResearchedCarsByBrands.addAll(researchedCarsByfirstBrand);
		carsList.setItems(FXCollections.observableArrayList(allResearchedCarsByBrands));
	}

	public void getSecondBrandsForSearch(ActionEvent e) {
		if (!allResearchedCarsByBrands.isEmpty()) {
			if (!researchedCarsBysecondBrand.isEmpty()) {
				if (brand1.getValue().equalsIgnoreCase("All brands")) {
					return;
				}
				allResearchedCarsByBrands.removeAll(researchedCarsBysecondBrand);
			}
		}
		brand = brand2.getValue();
		if (brand.equalsIgnoreCase("none")) {

		}
//TODO		researchedCarsBysecondBrand = carService.getListCarByBrand(brand);
		allResearchedCarsByBrands.addAll(researchedCarsBysecondBrand);
		carsList.setItems(FXCollections.observableArrayList(allResearchedCarsByBrands));
	}

	public void getThirdBrandForSearch(ActionEvent e) {
		if (!allResearchedCarsByBrands.isEmpty()) {
			if (!researchedCarsBythirdBrand.isEmpty()) {
				if (brand1.getValue().equalsIgnoreCase("All brands")) {
					return;
				}
				allResearchedCarsByBrands.removeAll(researchedCarsBythirdBrand);
			}
		}
		brand = brand3.getValue();
//TODO		researchedCarsBythirdBrand = carService.getListCarByBrand(brand);
		allResearchedCarsByBrands.addAll(researchedCarsBythirdBrand);
		carsList.setItems(FXCollections.observableArrayList(allResearchedCarsByBrands));
	}

	public void getDateStartRental() {
		localDateFrom = dateFrom.getValue();
	}

	public void getDateEndRental() {
		// TODO
		System.out.println("Here we need logic with prof dates");
		localDateTo = dateTo.getValue();
		try {
			ValidationService.validateRentalDates(localDateFrom, localDateTo);
		} catch (Exception e) {
			String error = "Dates are not valid";
			System.out.println(error);
			dateFromLabel.setText(error);
			dateToLabel.setText(error);
			return;
		}
	}

	public void getMinPrice() {
		String value = minPrice.getText();
		try {
			double minPrice = Double.valueOf(value);
			List listCarByMinPrice = carService.getListCarByMinPrice(minPrice);
			allResearchedCarsByBrands.retainAll(listCarByMinPrice);
			carsList.setItems(FXCollections.observableArrayList(allResearchedCarsByBrands));
			
		} catch (Exception e) {
			String er = "Wrong price";
			System.out.println(er);
			minPriceLabel.setText(er);
		}
	}

	public void getMaxPrice() {
		String value = maxPrice.getText();
		try {
			double maxPrice = Double.valueOf(value);
			List listCarByMaxPrice = carService.getListCarByMaxPrice(maxPrice);
			allResearchedCarsByBrands.retainAll(listCarByMaxPrice);
			carsList.setItems(FXCollections.observableArrayList(allResearchedCarsByBrands));
		} catch (Exception e) {
			String er = "Wrong price";
			System.out.println(er);
			maxPriceLabel.setText(er);
		}
	}
}
