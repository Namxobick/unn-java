package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ZooSettingGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZooGUI.class.getResource("ZooSetting.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 625, 400);
        stage.setMaxWidth(650);
        stage.setTitle("ZooSetting");

        stage.setScene(scene);
        stage.show();
    }
}