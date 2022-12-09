package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ZooGUIController {

    @FXML
    private Button _settingButton;

    @FXML
    void Setting(MouseEvent event) {
        ZooSettingGUI zooSettingGUI = new ZooSettingGUI();
        _settingButton.getScene().getWindow().hide();

        try {
            zooSettingGUI.start(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void Walk(MouseEvent event) {
        ZoInspectionGUI zoInspectionGUI = new ZoInspectionGUI();
        _settingButton.getScene().getWindow().hide();

        try {
            zoInspectionGUI.start(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}