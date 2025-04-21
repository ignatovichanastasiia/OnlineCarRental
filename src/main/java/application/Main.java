package application;
	
import application.GUI.EnterAuthForm;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			EnterAuthForm EAF = new EnterAuthForm();
			EAF.startEnterAuthForm(primaryStage);

		} catch(Exception e) {
			System.out.println("Problems with start-methods: "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


/*
CarSelectionForm csF = new CarSelectionForm();
ClientForm cliF = new ClientForm();
ConfirmationForm confF = new ConfirmationForm();
RentalSummaryForm rentSF = new RentalSummaryForm();
DriversLicenseForm drivLF = new DriversLicenseForm();
*/