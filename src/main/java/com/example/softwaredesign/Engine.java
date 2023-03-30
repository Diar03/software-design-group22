package com.example.softwaredesign;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Engine {
    private Creature creature;
    ScheduledExecutorService executor;
    private Environment environment;
    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    private static Engine instance = null;

    private Engine() {}
    public static Engine getInstance(){
        if (instance == null){
            instance = new Engine();
        }
        return instance;
    }

    public void initSchedulers(GameController controller){
        executor = Executors.newScheduledThreadPool(2);
        Runnable vitalUpdater = () -> {
            if(!getCreature().update()){
                Platform.runLater(this::resetGame); // wrapped in Platform#runLater
            }
            controller.updateBars();

        };
        Runnable timeUpdater = () -> {
            getEnvironment().setNextTimeOfDay();
            creature.updateExtension();
            controller.updateTime();
        };
        executor.scheduleAtFixedRate(vitalUpdater, 5, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(timeUpdater, 30, 30, TimeUnit.SECONDS);
    }


    public void buyFood(Food item){
        switch (item){
            case SALAD:
                if(getCreature().getCoins() < 20){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Not enough coins to buy this item");
                    alert.showAndWait();
                    return;
                }else{
                    getCreature().decreaseCoins(20);
                }
                break;
            case MEAT:
                if(getCreature().getCoins() < 25){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Not enough coins to buy this item");
                    alert.showAndWait();
                    return;
                }else{
                    getCreature().decreaseCoins(25);
                }
                break;
            default:
                // Add here any foods from the shop
                break;
        }

        Integer currentVal = getCreature().getInventory().get(item);
        if(currentVal == null){
            getCreature().getInventory().put(item, 1);
        }else{
            getCreature().getInventory().put(item, currentVal + 1);
        }

    }


    public void resetGame(){

        executor.shutdownNow();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Game over!");
        alert.setHeaderText("Game over!");
        alert.setContentText("Your creature has died");
        alert.show();
        Screen.closeWindow();
    }
    public void exitGame(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exiting the game!!! ");
        alert.setHeaderText("*ALERT* Quitting Vivarium game");
        alert.setContentText("Are you sure you want to quit “Vivarium” game??? ");
        Optional<ButtonType> optional = alert.showAndWait();
        if(optional.isPresent() && optional.get() == ButtonType.OK){
            if(executor != null) {
                executor.shutdown();
            }
            Screen.closeWindow();
        }
    }
}
