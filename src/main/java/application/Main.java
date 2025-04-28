package application;
	
import application.GUI.EnterAuthForm;
import application.controllers.AppContext;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AppContext context = new AppContext();
			//standard work from here
			EnterAuthForm EAF = new EnterAuthForm();
			EAF.startEnterAuthForm(primaryStage, context);
			
			//TEST
//			ClientForm CF = new ClientForm();
//			CF.startClientForm(primaryStage, context);
			
//			ConfirmationForm CF = new ConfirmationForm();
//			CF.startConfirmationForm(primaryStage, context);
			
//			RentalSummaryForm ren = new RentalSummaryForm();
//			ren.startRentalSummaryForm(primaryStage, context);
						
//			Client testClient = new Client("Anat Ign","sol@df.ru","678900156789");
//			Rental testRental = new Rental(testClient);
//			context.getRentalService().setCurrentRental(testRental);
			

		} catch(Exception e) {
			System.out.println("Problems with start-methods: "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
