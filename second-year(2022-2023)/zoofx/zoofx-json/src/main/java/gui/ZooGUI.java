package gui;

import StorageSystem.JsonWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Test;

import java.io.IOException;

public class ZooGUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        JsonWriter jsonWriter = new JsonWriter();
//        jsonWriter.WriteZoo(new Test().Test1());

        FXMLLoader fxmlLoader = new FXMLLoader(ZooGUI.class.getResource("Zoo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("ZOO");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}