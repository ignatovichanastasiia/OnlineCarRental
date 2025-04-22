package application.GUI;

import java.io.IOException;
import java.net.URL;

import application.controllers.AppContext;
import application.controllers.EnterAuthFormController;
import application.services.DriversLicenseService;
import application.services.RentalService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DriversLicenseForm {
	public void startDriversLicenseForm(Stage primaryStage, AppContext context) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/design_drivers_license_form.fxml"));
		    System.out.println(loader.toString());
		    
		    //AppContext context, RentalService rentalService, DriversLicenseService driversLicenseService
		    EnterAuthFormController controller = new EnterAuthFormController(
		    		context, context.getRentalService(),context.getDriversLicenseService
		    		

			Parent root = FXMLLoader.load(fxmlUrl);
			Scene scene = new Scene(root);
			primaryStage.setTitle("Car rent app");
			Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			System.out.println("Problem with window \"driver's license form\": " + e.getMessage());
		}
	}

}
//INIT WITH PARAMS
try {
	AppContext context = new AppContext(); // init
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/design_enter_auth_form.fxml"));
    System.out.println(loader.toString());
    EnterAuthFormController controller = new EnterAuthFormController(
    		context, 
    		context.getCarService(),
    		context.getClientService(),
    		context.getRentalService());
    loader.setController(controller);
    Parent root = loader.load(); 
    System.out.println("THIS loader loaded: " + loader.getController());
    System.out.println("Controller is: " + controller);
	Scene scene = new Scene(root);
	primaryStage.setTitle("Car rent app");
	Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
	primaryStage.getIcons().add(icon);
	primaryStage.setScene(scene);
	primaryStage.show();

} catch (IOException e) {
	System.out.println("Problem with window \"enter form\": " + e.getMessage());
	e.printStackTrace();
}
}