module gui.zoofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens gui to javafx.fxml;
    exports gui;
}