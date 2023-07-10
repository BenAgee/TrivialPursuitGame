module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.example.TrivialPursuitGame to javafx.fxml;
    exports com.example.TrivialPursuitGame;
}