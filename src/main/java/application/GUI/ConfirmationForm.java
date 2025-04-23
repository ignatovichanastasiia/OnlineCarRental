package application.GUI;

import java.io.IOException;

import application.controllers.AppContext;
import application.controllers.ConfirmationFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ConfirmationForm {

	public void startConfirmationForm(Stage primaryStage, AppContext context) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/design_confirmation_form.fxml"));
			System.out.println(loader.toString());
			ConfirmationFormController controller = new ConfirmationFormController(context);

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
			System.out.println("Problem with window \"confirmation form\": " + e.getMessage());
		}
	}

}
