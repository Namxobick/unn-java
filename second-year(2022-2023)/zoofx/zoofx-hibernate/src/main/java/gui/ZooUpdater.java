package gui;

import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import logic.*;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class ZooUpdater {
    AlertCreator alertCreator = new AlertCreator();

    private boolean ContainsNulls(Collection<String> collection) {
        if(collection != null)
        {
            for(String a : collection)
                if(Objects.equals(a, "") || a == null) return true;
        }

        return false;
    }

    private Animal CreateAnimal(Map<String, String> animalInfo){
        Animal anim = null;
        if (!ContainsNulls(animalInfo.values())) {
            anim = switch (animalInfo.get("TypePowerSupply")) {
                case "Herbivores" -> new Herbivores(animalInfo.get("Name"), animalInfo.get("Family"), animalInfo.get("Type"), animalInfo.get("Voice"));
                case "Predatory" -> new Predatory(animalInfo.get("Name"), animalInfo.get("Family"), animalInfo.get("Type"), animalInfo.get("Voice"));
                default -> null;
            };
        }
        return anim;
    }

    private boolean TryPutAnimalInCage(Cage cage, Animal animal) {
        if (animal != null)
            try{
                cage.SetAnimal(animal);
            } catch (Exception e) {
                alertCreator.CreateAlert(Alert.AlertType.ERROR, "ERROR", e.toString());
                return false;
            }
        return true;
    }

    public int TrySavedAnimalsInCage(GridPane cagesGrid, Cage cage, int savedAnimalCounts) {
        for (int i = 0; i < 4; i++) {
            GridPane animalGrid = (GridPane) cagesGrid.getChildren().get(i + 1);
            Map<String, String> animalInfo = new AnimalInfoGridController().GetAnimalInfo(animalGrid);
            Animal animal = CreateAnimal(animalInfo);
            if (!TryPutAnimalInCage(cage, animal))
                return -1;

            if (animal != null)
                savedAnimalCounts++;
        }
        return savedAnimalCounts;
    }

    public Zoo TryClearCage(int index, Zoo zoo) {
        try {
            zoo.GetCage(index).ClearCage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return zoo;
    }

    public Zoo TryPutCageInZoo(Cage cage, int index, Zoo zoo) {
        AnimalPool animals;
        try{
            animals = cage.GetAnimals();
        }catch (Exception e)
        {
            return zoo;
        }

        for (Animal animal: animals) {
            if (animal != null) {
                try {
                    zoo.GetCage(index).SetAnimal(animal);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return zoo;
    }

}