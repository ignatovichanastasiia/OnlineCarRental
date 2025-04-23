package application.GUI;

import java.io.IOException;

import application.controllers.AppContext;
import application.controllers.EnterAuthFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EnterAuthForm {

	public void startEnterAuthForm(Stage primaryStage,AppContext context)  {

	    for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
	        System.out.println(ste);
	    }
		try {
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

}
