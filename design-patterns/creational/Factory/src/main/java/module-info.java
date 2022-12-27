module com.example.factory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.factory to javafx.fxml;
}