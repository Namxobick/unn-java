package gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class AnimalGUIController {

    @FXML
    private ChoiceBox<String> _powerChoiceBox;

    @FXML
    private void initialize(){
        _powerChoiceBox.setItems(FXCollections.observableArrayList("Predatory", "Herbivores"));
        _powerChoiceBox.setStyle("-fx-font-size: 12; -fx-font-family: calibre; -fx-text-alignment: center;");
        Label lbl = new Label();
        _powerChoiceBox.setOnAction(event -> lbl.setText(_powerChoiceBox.getValue()));
    }
}