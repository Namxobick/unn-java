package gui;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.*;

public class AnimalInfoGridController {
    Map<String, String> _animalInfo;

    private void AddAnimalInfo(ArrayList<String> animalInfo, GridPane cageGrid) {
        for (int i = 0; i < 4; i++) {
            GridPane animal = (GridPane) cageGrid.getChildren().get(i + 1);

            AddAnimalInfo(animalInfo, animal, true);
        }
    }

    private void AddAnimalInfo(ArrayList<String> animalInfo, GridPane animalGrid, boolean bool) {
        for (int j = 0; j < 4; j++)
            AddTextTextField((TextField) animalGrid.getChildren().get(j + 1), animalInfo.get(j));
        SetValueStringChoiceBox((ChoiceBox<String>) animalGrid.getChildren().get(9), animalInfo.get(4));
    }

    private void AddAnimalInfoInMap (String key, TextField text) {
        String str = text.getText();
        _animalInfo.put(key, str);
    }

    private void AddAnimalInfoInMap (String key, ChoiceBox<String> typePowerSupplyCB) {
        String typePowerSupply = typePowerSupplyCB.getValue();
        _animalInfo.put(key, typePowerSupply);
    }

    private void AddAnimalInfoInMap(GridPane animal, List<String> keys) {
        int count = 1;
        for (String key: keys) {
            AddAnimalInfoInMap(key, (TextField) animal.getChildren().get(count));
            count++;
        }
    }

    private void AddTextTextField(TextField textField, String text) {
        if (text == null)
            textField.clear();
        else
            textField.setText(text);
    }

    private void SetValueStringChoiceBox(ChoiceBox<String> choiceBox, String text) {
        choiceBox.setValue(Objects.requireNonNullElse(text, ""));
    }

    public void SetAnimalInfo(GridPane cageGrid) {
        ArrayList<String> animalInfo = new ArrayList<>(Arrays.asList(null, null, null, null, null));
        AddAnimalInfo(animalInfo, cageGrid);
    }

    public void SetAnimalInfo(ArrayList<String> animalInfo, GridPane animalGrid) {
        AddAnimalInfo(animalInfo, animalGrid, true);
    }

    public Map<String, String> GetAnimalInfo(GridPane animal) {
        _animalInfo = new HashMap<>();
        AddAnimalInfoInMap(animal, Arrays.asList("Type","Family","Name","Voice"));
        AddAnimalInfoInMap("TypePowerSupply", (ChoiceBox<String>) animal.getChildren().get(9));
        return _animalInfo;
    }

}