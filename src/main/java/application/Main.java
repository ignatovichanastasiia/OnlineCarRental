package application;
	
import application.GUI.CarSelectionForm;
import application.GUI.ClientForm;
import application.GUI.ConfirmationForm;
import application.GUI.DriversLicenseForm;
import application.GUI.RentalSummaryForm;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			CarSelectionForm csF = new CarSelectionForm();
			ClientForm cliF = new ClientForm();
			ConfirmationForm confF = new ConfirmationForm();
			RentalSummaryForm rentSF = new RentalSummaryForm();
			DriversLicenseForm drivLF = new DriversLicenseForm();
			csF.startCarSelectionForm(primaryStage);
//			cliF.startClientForm(primaryStage); 
//			confF.startConfirmationForm(primaryStage);
//			rentSF.startRentalSummaryForm(primaryStage); 
		} catch(Exception e) {
			System.out.println("Problems with start-methods: "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
