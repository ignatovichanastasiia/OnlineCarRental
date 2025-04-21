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
	public void startCarSelectionForm(Stage stage, AppContext context) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/design_car_selection_form.fxml"));

			loader.setControllerFactory(param -> {
				if (param == CarSelectionFormController.class) {
					return new CarSelectionFormController(context, context.getCarService(), context.getShopService(),
							context.getRentalService());
				}
				try {
					return param.getDeclaredConstructor().newInstance();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});

			Parent root = loader.load();
			Scene scene = new Scene(root);
			stage.setTitle("Car rent app");
			Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
			stage.getIcons().add(icon);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			System.err.println("Error opening CarSelectionForm: " + e.getMessage());
		}
	}
}

//ИНИТ С ПЕРЕМЕННЫМИ!!!
