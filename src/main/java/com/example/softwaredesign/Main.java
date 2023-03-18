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
        Parent root = loader.load();
        Controller controller = loader.getController();
        if(controller.selectedEnvironment == null){
            System.out.println("NULLLLL");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.show();
        /*while (true){
            if(controller.selectedCreature != null && controller.selectedEnvironment != null){
                System.out.println("Creature: " + controller.selectedCreature + " Environment: " + controller.selectedEnvironment);
            }
        }*/
    }

    public void startGame(String environment, String creature) {
        selectedEnvironment = environment;
        selectedCreature = creature;
        System.out.println("Selected " + environment + " with creature " + creature);
        //latch.countDown(); // signal the waiting thread to continue
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