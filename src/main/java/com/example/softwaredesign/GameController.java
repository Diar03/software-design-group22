package com.example.softwaredesign;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;

public class GameController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Main main;
    @FXML
    private ComboBox<String> games;

    private String[] gamesArray = {"Riddle"};
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
        games.getItems().addAll(gamesArray);
        games.setOnAction(onItemSelected);
        shop.getItems().addAll(shopItems);
        eatChoiceBox.getItems().addAll(shopItems);
        hungerBar.setStyle("-fx-accent: #c4591b;");
        healthBar.setStyle("-fx-accent: red;");
    }
    EventHandler<ActionEvent> onItemSelected = event -> {
        System.out.println("Hello");
        try {
            //CONTINUE HERE
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("riddleGameScreen.fxml"));
            root = loader.load();
            RiddleController riddleController = loader.getController();
            scene = new Scene(root);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            //throws IOException;
        }

    };

    public void adaptScreenToCreature(){
        switch (main.getEngine().getCreature().getName()){
            case "Bird":
                flyButton = new Button("Fly");
                flyButton.setLayoutX(14);
                flyButton.setLayoutY(394);
                flyButton.setPrefHeight(32.0);
                flyButton.setPrefWidth(74.0);
                flyButton.setOnAction(event -> {
                    ((Bird)main.getEngine().getCreature()).fly();
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
                    ((Alien)main.getEngine().getCreature()).changeShape();
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
        double curHealth = main.getEngine().getCreature().getHealth().getPercentageLevel()/100.0;
        double curHunger = main.getEngine().getCreature().getHunger().getPercentageLevel()/100.0;
        healthBar.setProgress(curHealth);
        hungerBar.setProgress(curHunger);
        switch (main.getEngine().getCreature().getName()){
            case "Alien":
                double curShapeshift = ((Alien)main.getEngine().getCreature()).getShapeshift().getPercentageLevel()/100.0;
                shapeshiftBar.setProgress(curShapeshift);
                break;
            case "Bird":
                double curFlight = ((Bird)main.getEngine().getCreature()).getFlight().getPercentageLevel()/100.0;
                flightBar.setProgress(curFlight);
                break;
            case "Vampire":
                double curPhotosensitivity = ((Vampire)main.getEngine().getCreature()).getPhotosensitivity().getPercentageLevel()/100.0;
                photosensitivityBar.setProgress(curPhotosensitivity);
                break;
        }
    }

    public void loadImages(){
        environmentView.setImage(main.getEngine().getEnvironment().getDaySprite());
        creatureView.setImage(main.getEngine().getCreature().getSprite());
    }
    public void eatButton(){
        Food choice = eatChoiceBox.getValue();
        if(choice == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please choose an item to eat");
            alert.showAndWait();
            return;
        }
        main.getEngine().getCreature().eat(choice);
        updateBars();
    }
    public void updateTime(){
        Environment curEnv = main.getEngine().getEnvironment();
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
