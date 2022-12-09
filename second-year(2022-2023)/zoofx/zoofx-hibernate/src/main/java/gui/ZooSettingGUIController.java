package gui;

import storageSystem.DBLoader;
import storageSystem.DBSaver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import logic.*;
import logic.Event.MyEvent;
import logic.Event.MyEventListeners;


import java.io.IOException;

public class ZooSettingGUIController {

    public static MyEventListeners myEventListeners = new MyEventListeners();
    private Zoo _zoo = new Zoo();


    private final DBSaver DBSaver = new DBSaver();
    private final DBLoader DBLoader = new DBLoader();


    private CageGUIFactory cageGUIFactory;

    @FXML
    private Accordion _accordion;

    @FXML
    private Button _exitButton;


    @FXML
    void AddCage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZooSettingGUI.class.getResource("Cage.fxml"));
        AnchorPane cage = fxmlLoader.load();
        cageGUIFactory.LoadCageInAccordion(cage, _accordion);
        Label index = (Label) cage.getChildren().get(3);
        index.setText(Integer.toString(_zoo.GetCages().size()));
        _zoo.AddCage(new Cage());

    }

    @FXML
    void Exit(MouseEvent event) {
        DBSaver.SaveZoo(_zoo);

        myEventListeners.clearEventListener();

        ZooGUI zooGUI = new ZooGUI();
        Stage stage = (Stage) _exitButton.getScene().getWindow();
        stage.close();

        try {
            zooGUI.start(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void initialize() throws Exception {
       _zoo = DBLoader.LoadZoo();
//        Test t = new Test();
//        _zoo = t.Test1();
        cageGUIFactory = new CageGUIFactory(_zoo);
        cageGUIFactory.LoadCagesToGUI(_accordion);

        myEventListeners.addEventListener(event -> {
            if (event.GetType() == MyEvent.Type.CageRemoval) {
                _zoo.DeleteCage(Integer.parseInt(event.GetAdditionalInfo()));
                _accordion.getPanes().remove(Integer.parseInt(event.GetAdditionalInfo()));
                UpdateCageNumber();
            } else if (event.GetType() == MyEvent.Type.ZooConservation) {
                ZooUpdater zooUpdater = new ZooUpdater();
                _zoo = zooUpdater.TryClearCage(Integer.parseInt(event.GetAdditionalInfo()), _zoo);
                _zoo = zooUpdater.TryPutCageInZoo(event.GetCage(), Integer.parseInt(event.GetAdditionalInfo()), _zoo);
            }
        });
    }

    private void UpdateCageNumber() {
        int index = 0;
        for (TitledPane cage: _accordion.getPanes()) {
            Label label = (Label) (((AnchorPane)((ScrollPane) cage.getContent()).getContent()).getChildren()).get(3);
            label.setText(String.valueOf(index));
            index++;
        }
    }
}