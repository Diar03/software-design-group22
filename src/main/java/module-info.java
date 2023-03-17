module com.example.softwaredesign {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.example.softwaredesign to javafx.fxml;
    exports com.example.softwaredesign;
}