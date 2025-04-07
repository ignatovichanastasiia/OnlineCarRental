package application.GUI;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientForm {
	public void startClientForm(Stage primaryStage) {
		try {
			URL fxmlUrl = getClass().getResource("design_client_form.fxml");
			System.out.println("FXML URL: " + fxmlUrl);
			Parent root = FXMLLoader.load(fxmlUrl);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			System.out.println("Problem with window \"client form\": " + e.getMessage());
		}
	}

}
