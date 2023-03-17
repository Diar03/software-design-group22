package com.example.softwaredesign;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Vivarium game!");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> exitGame(stage));
    }
    public void exitGame(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exiting the game!!! ");
        alert.setHeaderText("*ALERT* Quitting Vivarium game");
        alert.setContentText("Are you sure you want to quit “Vivarium” game??? ");
        if(alert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}