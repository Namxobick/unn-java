package gui;

import StorageSystem.JsonReader;
import StorageSystem.JsonWriter;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import logic.*;
import logic.Event.MyEvent;

import java.io.IOException;
import java.util.*;


public class CageGUIController {

    private final AlertCreator alertCreator = new AlertCreator();
    private final ZooUpdater zooUpdater = new ZooUpdater();

    @FXML
    private GridPane _cagesGrid;

    @FXML
    private Label _index;

    @FXML
    void Delete(MouseEvent event) {
        ZooSettingGUIController.myEventListeners.notifyEventListeners(
                new MyEvent(ZooSettingGUIController.myEventListeners, MyEvent.Type.CageRemoval, _index.getText()));
    }

    @FXML
    void ClearCage(MouseEvent event) {
        new AnimalInfoGridController().SetAnimalInfo(_cagesGrid);
    }

    @FXML
    void Save(MouseEvent event) {
        int savedAnimalCounts = 0;
        Cage cage = new Cage();
        savedAnimalCounts = zooUpdater.TrySavedAnimalsInCage(_cagesGrid, cage, savedAnimalCounts);
        if(savedAnimalCounts == -1)
            return;
        alertCreator.CreateAlert(Alert.AlertType.INFORMATION, "Information", "%x animals were put in a cage".formatted(savedAnimalCounts));
        ZooSettingGUIController.myEventListeners.notifyEventListeners(
                new MyEvent(ZooSettingGUIController.myEventListeners, MyEvent.Type.SaveZoo, _index.getText(), cage));

    }

    @FXML
    private void initialize() {
        ArrayList<FXMLLoader> fxmlLoaders = new ArrayList<>();
        ArrayList<GridPane> animalGrids = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fxmlLoaders.add(new FXMLLoader(CageGUI.class.getResource("Animal.fxml")));
            try{
                animalGrids.add(fxmlLoaders.get(i).load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            _cagesGrid.add(animalGrids.get(i), i / 2, i % 2);
        }
    }
}