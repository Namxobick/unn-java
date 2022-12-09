package gui;

import StorageSystem.JsonReader;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.Animal;
import logic.Cage;
import logic.Zoo;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class ZoInspectionGUIController {

    private List<ImageView> _imageViews;

    private int _index;

    private AlertCreator _alertCreator;

    @FXML
    private Button _exitButton;

    @FXML
    private ImageView _image1;

    @FXML
    private ImageView _image2;

    @FXML
    private ImageView _image3;

    @FXML
    private ImageView _image4;

    @FXML
    private Button _nextButton;

    @FXML
    private Button _previousButton;

    private Zoo _zoo;

    @FXML
    void WithdrawAlertZooInfo(MouseEvent event) {
        String infoZooString = "";
        List<Object> infoZoo = _zoo.GetZooInfo();
        if (Objects.equals(infoZoo.get(2), new HashSet<>())) {
            _alertCreator.CreateAlert(Alert.AlertType.INFORMATION, "INFO", "Zoo is empty");
        }
        else {
            infoZooString = "Number of herbivores: " + infoZoo.get(0) + '\n';
            infoZooString += "Number of predators: " + infoZoo.get(1) + '\n';;
            infoZooString += "Unique animals: " + infoZoo.get(2);
            _alertCreator.CreateAlert(Alert.AlertType.INFORMATION, "INFO", infoZooString);
       }

    }

    @FXML
    void WithdrawAlertAnimalInfo(MouseEvent event) throws Exception {
        String animalInfo;
        EventTarget target = event.getTarget();
        if (_image1.equals(target)) {
            animalInfo = _zoo.GetCage(_index).GetAnimal(0).toString();
        } else if (_image2.equals(target)) {
            animalInfo = _zoo.GetCage(_index).GetAnimal(1).toString();

        } else if (_image3.equals(target)) {
            animalInfo = _zoo.GetCage(_index).GetAnimal(2).toString();

        } else if (_image4.equals(target)) {
            animalInfo = _zoo.GetCage(_index).GetAnimal(3).toString();

        } else {
            throw new IllegalStateException("Unexpected value: " + event.getTarget());
        }

        _alertCreator.CreateAlert(Alert.AlertType.INFORMATION, "INFO", animalInfo);

    }

    @FXML
    void WithdrawNextCage(MouseEvent event) {
        _index += 1;
        SetDisableAllImageView(false);

        Cage cage;

        try {
            cage = _zoo.GetCage(_index);
        } catch (Exception e) {
            _nextButton.setDisable(true);
            return;
        }

        try{
            _zoo.GetCage(_index + 1);
        }
        catch (Exception e)
        {
            _nextButton.setDisable(true);
        }

        try{
            _zoo.GetCage(_index - 1);
            _previousButton.setDisable(false);
        }
        catch (Exception e)
        {
            _previousButton.setDisable(true);
        }

        SetImage(cage);
    }

    @FXML
    void WithdrawPreviousCage(MouseEvent event) {
        _index -= 1;
        SetDisableAllImageView(false);

        Cage cage;
        try {
            cage = _zoo.GetCage(_index);
        } catch (Exception e) {
            _previousButton.setDisable(true);
            return;
        }

        try{
            _zoo.GetCage(_index - 1);
        }
        catch (Exception e)
        {
            _previousButton.setDisable(true);
        }

        try{
            _zoo.GetCage(_index + 1);
            _nextButton.setDisable(false);
        }
        catch (Exception e) {
            _nextButton.setDisable(true);
        }

        SetImage(cage);
    }

    @FXML
    void Exit(MouseEvent event) {
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
    private void initialize() {
        JsonReader jsonReader = new JsonReader();
        _zoo = jsonReader.ReadZoo();
        _index = 0;
        _alertCreator = new AlertCreator();
        _imageViews = Arrays.asList(_image1, _image2, _image3, _image4);
        _previousButton.setDisable(true);
        Cage cage;
        try {
            cage = _zoo.GetCage(_index);
        } catch (Exception e) {
            _nextButton.setDisable(true);
            SetDisableAllImageView(true);
            return;
        }

        try{
            _zoo.GetCage(_index + 1);
        }
        catch (Exception e)
        {
            _nextButton.setDisable(true);
        }

        SetImage(cage);

    }

    private void SetDisableAllImageView(boolean bool) {
        for (ImageView imageView: _imageViews) {
            imageView.setDisable(bool);
        }
    }

    private void SetImage(Cage cage) {
        for (int i = 0; i < 4; i++) {
            Animal animal;
            try {
                animal = cage.GetAnimal(i);
            } catch (Exception e) {
                _imageViews.get(i).setImage(null);
                _imageViews.get(i).setDisable(true);
                continue;
            }
            File file;
            String path = "src/main/resources/image/animals/" + animal.GetName() + ".jpg";
            file = new File(path);
            if (!isFileExists(file))
            {
                path = "src/main/resources/image/animals/" + animal.GetType() + ".jpg";
                file = new File(path);
            }
            if (!isFileExists(file))
            {
                path = "src/main/resources/image/question.jpg";
                file = new File(path);
            }

            Image image = new Image(file.toURI().toString());
            _imageViews.get(i).setImage(image);
        }
    }

    private static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }
}