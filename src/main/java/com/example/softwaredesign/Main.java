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
import java.util.concurrent.CountDownLatch;

public class Main extends Application {
    Parent root;
    public Scene scene;


    private Engine engine;

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

    public void startGame(String environment, String creature) throws IOException {

        switch (environment){
            case "Forest":
                engine.setEnvironment(new Environment("Forest", 4, Time.MORNING));
                break;
            case "Antarctica":
                engine.setEnvironment(new Environment("Antarctica", 2, Time.MORNING));
                break;
            case "Desert":
                engine.setEnvironment(new Environment("Desert", 10, Time.MORNING));
                break;
            default:
                System.err.println("Wrong value passed to start game. Please exit and restart game");
        }

        switch (creature){
            case "Vampire":
                engine.setCreature(new Vampire());
                break;
            case "Bird":
                engine.setCreature(new Bird());
                break;
            case "Alien":
                engine.setCreature(new Alien());
                break;
            default:
                System.err.println("Wrong value passed to start game. Please exit and restart game");
        }

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