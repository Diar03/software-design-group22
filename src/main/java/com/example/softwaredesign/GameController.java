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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;

public class GameController extends Screen implements Initializable {
    private Parent root;
    @FXML
    private ComboBox<String> games;

    private final String[] gamesArray = {"Riddle"};
    @FXML
    private ImageView environmentView;

    @FXML
    private ImageView creatureView;
    @FXML
    private Label currCoin;
    @FXML
    private ComboBox<Food> shop;

    @FXML
    private ProgressBar hungerBar;

    @FXML
    private ProgressBar healthBar;

    private ProgressBar flightBar;

    private ProgressBar photosensitivityBar;

    private ProgressBar shapeshiftBar;

    @FXML
    private ChoiceBox<Food> eatChoiceBox;

    @FXML
    private AnchorPane pane;

    private final Food[] shopItems = {Food.MEAT, Food.SALAD};

    private static final String STYLE_STR = "-fx-background-color: White; -fx-background-radius: 5px; -fx-label-padding: 0  2px;";

    public void displayCurrCoin(){
        currCoin.setText(""+engine.getCreature().getCoins());
    }

    public void displayInventory(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("INVENTORY");
        alert.setContentText("You have now... \n"+
                "Meat:  " + engine.getCreature().getInventory().get(Food.MEAT) + "\n" +
                "Salad:  " + engine.getCreature().getInventory().get(Food.SALAD) + "\n");
        alert.show();
    }
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
            Engine.getInstance().executor.shutdownNow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("riddleGameScreen.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            // Throw error
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

    };

    public void adaptScreenToCreature(){
        switch (engine.getCreature().getName()){
            case "Bird":
                Button flyButton = new Button("Fly");
                flyButton.setLayoutX(14);
                flyButton.setLayoutY(394);
                flyButton.setPrefHeight(32.0);
                flyButton.setPrefWidth(74.0);
                flyButton.setOnAction(this::fly);

                flightBar = new ProgressBar();
                flightBar.setLayoutX(468.0);
                flightBar.setLayoutY(94);
                flightBar.setPrefHeight(15.0);
                flightBar.setPrefWidth(158.0);
                flightBar.setStyle("-fx-accent: blue;");
                flightBar.setProgress(0.5);

                Label flightLabel = new Label();
                flightLabel.setLayoutX(527);
                flightLabel.setLayoutY(75);
                flightLabel.setText(" Flight ");
                flightLabel.setStyle(STYLE_STR);

                pane.getChildren().add(flyButton);
                pane.getChildren().add(flightBar);
                pane.getChildren().add(flightLabel);
                displayCurrCoin();
                break;
            case "Vampire":
                // Code for adding vampire nodes
                photosensitivityBar = new ProgressBar();
                photosensitivityBar.setLayoutX(468.0);
                photosensitivityBar.setLayoutY(94);
                photosensitivityBar.setPrefHeight(15.0);
                photosensitivityBar.setPrefWidth(158.0);
                photosensitivityBar.setStyle("-fx-accent: yellow;");
                photosensitivityBar.setProgress(0.5);


                Label photosensitivtyLabel = new Label();
                photosensitivtyLabel.setLayoutX(500);
                photosensitivtyLabel.setLayoutY(75);
                photosensitivtyLabel.setText(" Photosensitivity ");
                photosensitivtyLabel.setStyle(STYLE_STR);

                pane.getChildren().add(photosensitivityBar);
                pane.getChildren().add(photosensitivtyLabel);
                displayCurrCoin();
                break;
            case "Alien":
                Button shapeshiftButton = new Button("Shapeshift");
                shapeshiftButton.setLayoutX(14);
                shapeshiftButton.setLayoutY(394);
                shapeshiftButton.setPrefHeight(32.0);
                shapeshiftButton.setPrefWidth(74.0);
                shapeshiftButton.setOnAction(event -> ((Alien)engine.getCreature()).changeShape());

                shapeshiftBar = new ProgressBar();
                shapeshiftBar.setLayoutX(468.0);
                shapeshiftBar.setLayoutY(94);
                shapeshiftBar.setPrefHeight(15.0);
                shapeshiftBar.setPrefWidth(158.0);
                shapeshiftBar.setStyle("-fx-accent: green;");
                shapeshiftBar.setProgress(0.5);
                pane.getChildren().add(shapeshiftButton);
                shapeshiftButton.setOnAction(this::shapeshift);

                Label shapeshiftLabel = new Label();
                shapeshiftLabel.setLayoutX(510);
                shapeshiftLabel.setLayoutY(75);
                shapeshiftLabel.setText(" Shape-shift ");
                shapeshiftLabel.setStyle(STYLE_STR);


                pane.getChildren().add(shapeshiftBar);
                pane.getChildren().add(shapeshiftLabel);
                displayCurrCoin();
                break;
            default:
                // If your creature needs a specific bar/button, add a case statement here to the pane
                break;
        }
    }

    public void buyFood(){
        Food choice = shop.getValue();
        if(choice == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please choose an item to buy");
            alert.showAndWait();
            return;
        }

        engine.buyFood(choice);
        displayCurrCoin();
        displayInventory();

    }

    public void startBurning(){
        creatureView.setImage(((Vampire)engine.getCreature()).getBurningSprite());
    }
    public void stopBurning(){
        creatureView.setImage(engine.getCreature().getSprite());
    }

    public void updateBars(){
        double curHealth = engine.getCreature().getHealth().getPercentageLevel()/100.0;
        double curHunger = engine.getCreature().getHunger().getPercentageLevel()/100.0;
        healthBar.setProgress(curHealth);
        hungerBar.setProgress(curHunger);
        switch (engine.getCreature().getName()){
            case "Alien":
                double curShapeshift = ((Alien)engine.getCreature()).getShapeshift().getPercentageLevel()/100.0;
                shapeshiftBar.setProgress(curShapeshift);
                break;
            case "Bird":
                double curFlight = ((Bird)engine.getCreature()).getFlight().getPercentageLevel()/100.0;
                flightBar.setProgress(curFlight);

                break;
            case "Vampire":
                double curPhotosensitivity = ((Vampire)engine.getCreature()).getPhotosensitivity().getPercentageLevel()/100.0;
                photosensitivityBar.setProgress(curPhotosensitivity);
                break;
            default:
                // If a new creature uses a bar, update the bar/s associated to it here
                break;
        }
    }

    public void loadImages(){
        environmentView.setImage(engine.getEnvironment().getDaySprite());
        creatureView.setImage(engine.getCreature().getSprite());
    }

    public void setVampireBurning(){
        creatureView.setImage(((Vampire)engine.getCreature()).getBurningSprite());
    }

    public void eatButton(){
        Food choice = eatChoiceBox.getValue();
        if(choice == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please choose an item to eat");
            alert.showAndWait();
            return;
        }
        engine.getCreature().eat(choice);
        updateBars();
        displayInventory();
    }
    public void updateTime(){
        Environment curEnv = engine.getEnvironment();
        if(curEnv.getTimeOfDay().equals(Time.DAY)){
            environmentView.setImage(curEnv.getDaySprite());
        }else{
            environmentView.setImage(curEnv.getNightSprite());
        }
    }

    public void sleep(){
        if (engine.getCreature().sleep()) {
            updateBars();
            updateTime();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("The " + engine.getCreature().getName() + " slept, the vitals have been increased");
            alert.showAndWait();
        }
    }

    public void shapeshift(ActionEvent event) {
        if (((Alien)engine.getCreature()).changeShape()) {
            if (creatureView.getImage().equals(((Alien)engine.getCreature()).getShapeshiftSprite())) {
                creatureView.setImage(engine.getCreature().getSprite());
            }
            else {
                creatureView.setImage(((Alien)engine.getCreature()).getShapeshiftSprite());
            }
            updateBars();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("The " + engine.getCreature().getName() + " cannot shapeshift anymore");
            alert.showAndWait();
        }
    }

    public void fly(ActionEvent event) {
        Engine temp = Engine.getInstance();

        if( ((Bird) temp.getCreature()).fly() ){
            creatureView.setImage(((Bird)temp.getCreature()).getFlyingSprite());
            creatureView.setLayoutY(160);
        }else{
            stopFlight();
        }

    }

    public void stopFlight(){
        Engine temp = Engine.getInstance();
        creatureView.setImage(temp.getCreature().getSprite());
        creatureView.setLayoutY(260);
    }

}
