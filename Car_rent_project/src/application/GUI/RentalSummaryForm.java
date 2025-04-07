package application.GUI;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RentalSummaryForm {
	public void startRentalSummaryForm(Stage primaryStage) {
		try {
			URL fxmlUrl = getClass().getResource("design_rental_summary_form.fxml");
			System.out.println("FXML URL: " + fxmlUrl);
			Parent root = FXMLLoader.load(fxmlUrl);
			Scene scene = new Scene(root);
			primaryStage.setTitle("Car rent app");
			Image icon = new Image(getClass().getResourceAsStream("icon.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			System.out.println("Problem with window \"rental summary form\": " + e.getMessage());
		}
	}

}
