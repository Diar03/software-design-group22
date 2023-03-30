package com.example.softwaredesign;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        // Create engine instance
        Screen.setStage(stage);
        Screen.displayMenu();

    }

    public static void main(String[] args) {
        launch(args);
    }
}