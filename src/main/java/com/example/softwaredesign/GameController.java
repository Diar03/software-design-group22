package com.example.softwaredesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private Main main;

    @FXML
    private ImageView environmentView;

    @FXML
    private ImageView creatureView;

    @FXML
    private ComboBox<Food> shop;

    @FXML
    private ProgressBar hungerBar;

    @FXML
    private ProgressBar healthBar;

    private ProgressBar flightBar;

    private ProgressBar photosensitivityBar;

    private ProgressBar shapeshiftBar;

    private Button flyButton;

    private Button shapeshiftButton;


    @FXML
    private ChoiceBox<Food> eatChoiceBox;

    @FXML
    private AnchorPane pane;

    private Food[] shopItems = {Food.MEAT, Food.SALAD};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        shop.getItems().addAll(shopItems);
        eatChoiceBox.getItems().addAll(shopItems);
        hungerBar.setStyle("-fx-accent: #c4591b;");
        healthBar.setStyle("-fx-accent: red;");
    }

    public void adaptScreenToCreature(){
        switch (main.engine.creature.name){
            case "Bird":
                flyButton = new Button("Fly");
                flyButton.setLayoutX(14);
                flyButton.setLayoutY(394);
                flyButton.setPrefHeight(32.0);
                flyButton.setPrefWidth(74.0);
                flyButton.setOnAction(event -> {
                    ((Bird)main.engine.creature).fly();
                });

                flightBar = new ProgressBar();
                flightBar.setLayoutX(468.0);
                flightBar.setLayoutY(75);
                flightBar.setPrefHeight(25.0);
                flightBar.setPrefWidth(158.0);
                flightBar.setStyle("-fx-accent: blue;");
                flightBar.setProgress(0.5);

                pane.getChildren().add(flyButton);
                pane.getChildren().add(flightBar);
                break;
            case "Vampire":
                // Code for adding vampire nodes
                photosensitivityBar = new ProgressBar();
                photosensitivityBar.setLayoutX(468.0);
                photosensitivityBar.setLayoutY(75);
                photosensitivityBar.setPrefHeight(25.0);
                photosensitivityBar.setPrefWidth(158.0);
                photosensitivityBar.setStyle("-fx-accent: yellow;");
                photosensitivityBar.setProgress(0.5);
                pane.getChildren().add(photosensitivityBar);
                break;
            case "Alien":
                shapeshiftButton = new Button("Shapeshift");
                shapeshiftButton.setLayoutX(14);
                shapeshiftButton.setLayoutY(394);
                shapeshiftButton.setPrefHeight(32.0);
                shapeshiftButton.setPrefWidth(74.0);
                shapeshiftButton.setOnAction(event -> {
                    ((Alien)main.engine.creature).changeShape();
                });

                shapeshiftBar = new ProgressBar();
                shapeshiftBar.setLayoutX(468.0);
                shapeshiftBar.setLayoutY(75);
                shapeshiftBar.setPrefHeight(25.0);
                shapeshiftBar.setPrefWidth(158.0);
                shapeshiftBar.setStyle("-fx-accent: green;");
                shapeshiftBar.setProgress(0.5);
                pane.getChildren().add(shapeshiftButton);
                pane.getChildren().add(shapeshiftBar);
                break;
        }
    }

    public void buyFood(ActionEvent event){
        Food choice = shop.getValue();
        if(choice == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please choose an item to buy");
            alert.showAndWait();
            return;
        }

        main.buyFood(choice);
    }

    public void updateBars(){
        double curHealth = main.engine.creature.health.getPercentageLevel()/100.0;
        double curHunger = main.engine.creature.hunger.getPercentageLevel()/100.0;
        healthBar.setProgress(curHealth);
        hungerBar.setProgress(curHunger);
        switch (main.engine.creature.name){

            case "Alien":
                double curShapeshift = ((Alien)main.engine.creature).shapeshift.getPercentageLevel()/100.0;
                shapeshiftBar.setProgress(curShapeshift);
                break;
            case "Bird":
                double curFlight = ((Bird)main.engine.creature).flight.getPercentageLevel()/100.0;
                flightBar.setProgress(curFlight);
                break;
            case "Vampire":
                double curPhotosensitivity = ((Vampire)main.engine.creature).photosensitivity.getPercentageLevel()/100.0;
                photosensitivityBar.setProgress(curPhotosensitivity);
                break;
        }
    }

    public void loadImages(){
        environmentView.setImage(main.engine.getEnvironment().getDaySprite());
        creatureView.setImage(main.engine.getCreature().sprite);
    }
    public void eatButton(){
        Food choice = eatChoiceBox.getValue();
        if(choice == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please choose an item to eat");
            alert.showAndWait();
            return;
        }
        main.engine.creature.eat(choice);
        updateBars();
    }
    public void updateTime(){
        Environment curEnv = main.engine.environment;
        if(curEnv.getTimeOfDay().equals(Time.DAY)){
            environmentView.setImage(curEnv.getDaySprite());
        }else{
            environmentView.setImage(curEnv.getNightSprite());
        }
    }

    public void sleep(){
        System.out.println("Sleeping");
    }
    public void setMain(Main theMainInstance){
        this.main = theMainInstance;
    }
}
