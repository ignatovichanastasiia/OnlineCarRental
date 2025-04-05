package application.GUI;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CarSelectionForm {

	public void startCarSelectionForm(Stage primaryStage) {
		try {
			URL fxmlUrl = getClass().getResource("design_car_selection_form.fxml");
			System.out.println("FXML URL: " + fxmlUrl);
			Parent root = FXMLLoader.load(fxmlUrl);
			Scene scene = new Scene(root);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			System.out.println("Problem with loader or scene: " + e.getMessage());
		}
	}
}
