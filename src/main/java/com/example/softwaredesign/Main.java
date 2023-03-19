package com.example.softwaredesign;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    Parent root;
    public Scene scene;
    public Engine engine;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        // Create engine instance
        engine = new Engine();

        // Load the initial scene
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("scene1.fxml"));
        root = loader.load();
        Controller controller = loader.getController();

        // Pass reference of main to the controller
        controller.setMain(this);

        // Make and show the scene
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            exitGame(stage);
        });
    }


    public void buyFood(Food item){
        switch (item){
            case SALAD:
                if(engine.creature.coins < 20){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Not enough coins to buy this item");
                    alert.showAndWait();
                    return;
                }else{
                    engine.creature.coins -= 20;
                }
                break;
            case MEAT:
                if(engine.creature.coins < 25){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Not enough coins to buy this item");
                    alert.showAndWait();
                    return;
                }else{
                    engine.creature.coins -= 25;
                }
                break;
        }

        Integer currentVal = engine.creature.inventory.get(item);
        if(currentVal == null){
            engine.creature.inventory.put(item, 1);
        }else{
            engine.creature.inventory.put(item, currentVal + 1);
        }
        System.out.println(engine.creature.inventory);
    }

    public void eatFood(Food item){

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