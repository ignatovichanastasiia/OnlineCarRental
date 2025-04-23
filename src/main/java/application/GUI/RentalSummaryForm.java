package application.GUI;

import java.io.IOException;

import application.controllers.AppContext;
import application.controllers.RentalSummaryFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RentalSummaryForm {
	// startRentalSummaryForm(currentStage, context)
	public void startRentalSummaryForm(Stage primaryStage, AppContext context) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/design_rental_summary_form.fxml"));
			System.out.println(loader.toString());
			RentalSummaryFormController controller = new RentalSummaryFormController(context);
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
			System.out.println("Problem with window \"rental summary form\": " + e.getMessage());
		}
	}

}
