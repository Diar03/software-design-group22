package com.example.softwaredesign;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Screen {
    static Stage stage;
    static Scene scene;
    static Parent root;

    static GameController gameController;
    protected static Engine engine = Engine.getInstance();

    public static void setStage(Stage givenStage){
        stage = givenStage;
    }

    public static void startBurning(){
       gameController.getCreatureView().setImage(((Vampire)engine.getCreature()).getBurningSprite());
    }
    public static void stopBurning(){
       gameController.getCreatureView().setImage(engine.getCreature().getSprite());
    }

    public static void stopFlight(){
        Engine temp = Engine.getInstance();
        gameController.getCreatureView().setImage(temp.getCreature().getSprite());
        gameController.getCreatureView().setLayoutY(260);
    }

    public static void closeWindow(){
        stage.close();
    }

    public static void displayMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("scene1.fxml"));
        root = loader.load();
        Engine engine = Engine.getInstance();

        // Make and show the scene
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            engine.exitGame();
        });
    }

    public static void displayGameScreen() throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gameScreen.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        gameController.adaptScreenToCreature();
        gameController.loadImages();
        gameController.updateBars();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        engine.initSchedulers(gameController);
    }

    public static void displayRiddle() throws IOException{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("riddleGameScreen.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
