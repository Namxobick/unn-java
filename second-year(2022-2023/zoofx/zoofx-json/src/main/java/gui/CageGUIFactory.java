package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import logic.Animal;
import logic.AnimalPool;
import logic.Zoo;

import java.util.ArrayList;
import java.util.Arrays;

public class CageGUIFactory {

    private Zoo _zoo;

    public CageGUIFactory(Zoo zoo){
        _zoo = zoo;
    }

    private void LoadAnimalsInfoInGrid(AnimalPool animals, GridPane cageGrid, int i) throws Exception {
        for (int j = 0; j < animals.GetSize(); j++) {
            Animal animal = _zoo.GetCage(i).GetAnimal(j);
            GridPane animalGrid = (GridPane) cageGrid.getChildren().get(j + 1);

            ArrayList<String> animalInfo = new ArrayList<>(Arrays.asList(animal.GetType(), animal.GetFamily(),
                    animal.GetName(), animal.GetVoice(), animal.GetTypePowerSupply().toString()));
            new AnimalInfoGridController().SetAnimalInfo(animalInfo, animalGrid);
        }
    }

    private void LoadCagesInAccordion(ArrayList<AnchorPane> cages, Accordion accordion, int i) {
        Label index = (Label) cages.get(i).getChildren().get(3);
        index.setText(Integer.toString(i));
        LoadCageInAccordion(cages.get(i), accordion);
    }

    public void LoadCageInAccordion(AnchorPane cage, Accordion accordion){
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefHeight(accordion.getHeight());
        scroll.prefWidth(accordion.getWidth());
        TitledPane t = new TitledPane();
        t.setText("cage");
        t.setContent(scroll);
        scroll.setContent(cage);
        accordion.getPanes().add(t);
    }

    public void LoadCagesToGUI(Accordion accordion) throws Exception {
        ArrayList<FXMLLoader> fxmlLoadersCage = new ArrayList<>();
        ArrayList<AnchorPane> cages = new ArrayList<>();

        for (int i = 0; i < _zoo.GetCages().size(); i++) {
            fxmlLoadersCage.add(new FXMLLoader(ZooSettingGUI.class.getResource("Cage.fxml")));
            cages.add(fxmlLoadersCage.get(i).load());

            GridPane cageGrid = (GridPane) cages.get(i).getChildren().get(0);

            AnimalPool animals;
            try {
                animals = _zoo.GetCage(i).GetAnimals();
            } catch (Exception e) {
                new AnimalInfoGridController().SetAnimalInfo(cageGrid);
                LoadCagesInAccordion(cages, accordion, i);
                continue;
            }

            LoadAnimalsInfoInGrid(animals, cageGrid, i);
            LoadCagesInAccordion(cages, accordion, i);
        }
    }
}
