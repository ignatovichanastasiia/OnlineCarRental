package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class GUIController implements Initializable {

    @FXML private Accordion accordion;
    @FXML private TitledPane carsPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        accordion.setExpandedPane(carsPane);
    }
}