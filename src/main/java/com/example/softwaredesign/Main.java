package com.example.softwaredesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends Application {
    ScheduledExecutorService executor;
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Scene scene;
        Parent root;
        // Create engine instance
        Engine engine = Engine.getInstance();

        // Load the initial scene
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("scene1.fxml"));
        root = loader.load();
        engine.setScreenController(loader.getController());

        // Make and show the scene
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            engine.exitGame(stage);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}