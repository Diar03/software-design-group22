package com.example.softwaredesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private Parent root;
    private Scene scene;
    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

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

    public void initSchedulers(GameController controller){
        Runnable VitalUpdater = new Runnable() {
            public void run() {
                System.out.println("Vitals being updated");
                engine.getCreature().update();
                controller.updateBars();

            }
        };
        Runnable TimeUpdater = new Runnable() {
            public void run() {
                engine.getEnvironment().setNextTimeOfDay();
                controller.updateTime();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(VitalUpdater, 5, 10, TimeUnit.SECONDS);
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
        exe.scheduleAtFixedRate(TimeUpdater, 20, 30, TimeUnit.SECONDS);
    }


    public void buyFood(Food item){
        switch (item){
            case SALAD:
                if(engine.getCreature().getCoins() < 20){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Not enough coins to buy this item");
                    alert.showAndWait();
                    return;
                }else{
                    engine.getCreature().deccreaseCoins(20);
                }
                break;
            case MEAT:
                if(engine.getCreature().getCoins() < 25){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Not enough coins to buy this item");
                    alert.showAndWait();
                    return;
                }else{
                    engine.getCreature().deccreaseCoins(25);
                }
                break;
        }

        Integer currentVal = engine.getCreature().getInventory().get(item);
        if(currentVal == null){
            engine.getCreature().getInventory().put(item, 1);
        }else{
            engine.getCreature().getInventory().put(item, currentVal + 1);
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