package application.GUI;

import java.io.IOException;

import application.controllers.AppContext;
import application.controllers.CarSelectionFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EnterAuthForm {

	public void startClientForm(Stage primaryStage) {
		try {
			
			
			AppContext context = new AppContext(); // init
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("/design_enter_auth_form.fxml"));
		    loader.setControllerFactory(param -> {
		        if (param == CarSelectionFormController.class) {
		            return new CarSelectionFormController(context.getCarService());
		        }
		        try {
		            return param.getDeclaredConstructor().newInstance();
		        } catch (Exception e) {
		            throw new RuntimeException(e);
		        }
		    });

		    Parent root = loader.load(); 
			Scene scene = new Scene(root);
			primaryStage.setTitle("Car rent app");
			Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			System.out.println("Problem with window \"enter form\": " + e.getMessage());
		}
	}

}