package application.GUI;

import java.io.IOException;

import application.controllers.AppContext;
import application.controllers.CarSelectionFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CarSelectionForm {
	public void startCarSelectionForm(Stage primaryStage, AppContext context) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/design_car_selection_form.fxml"));
			System.out.println(loader.toString());
			System.out.println("App context: "+context.toString());
		    CarSelectionFormController controller = new CarSelectionFormController(context);
		    System.out.println("Controller is: " + controller);
		    loader.setController(controller);
		    Parent root = loader.load(); 
		    System.out.println("THIS loader loaded: " + loader.getController());
		    
			Scene scene = new Scene(root);
			primaryStage.setTitle("Car rent app");
			Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			System.err.println("Error opening CarSelectionForm: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
