package com.example.softwaredesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Main extends Application {
    private String selectedEnvironment;
    private String selectedCreature;
    private CountDownLatch latch;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("scene1.fxml"));
        System.out.println("Stage about to be shown");
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setMain(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            exitGame(stage);
        });
    }

    public void startGame(String environment, String creature) {
        selectedEnvironment = environment;
        selectedCreature = creature;
        System.out.println("Selected " + environment + " with creature " + creature);
        //latch.countDown(); // signal the waiting thread to continue
    }

    public void darian() {
        System.out.println("Is a bitch");
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