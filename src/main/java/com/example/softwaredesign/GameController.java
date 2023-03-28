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

    private Button flyButton;

    private Button shapeshiftButton;

    private Label flightLabel;
    private Label photosensitivtyLabel;
    private Label shapeshiftLabel;

    @FXML
    private ChoiceBox<Food> eatChoiceBox;

    @FXML
    private AnchorPane pane;

    private Food[] shopItems = {Food.MEAT, Food.SALAD};

    public void displayCurrCoin(){
        currCoin.setText(""+main.getEngine().getCreature().getCoins());
    }

    public void displayInventory(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("INVENTORY");
        alert.setContentText("You have now... \n"+
                "Meat:  " + main.getEngine().getCreature().getInventory().get(Food.MEAT) + "\n" +
                "Salad:  " + main.getEngine().getCreature().getInventory().get(Food.SALAD) + "\n");
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
        System.out.println("Hello");
        try {
            main.executor.shutdownNow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("riddleGameScreen.fxml"));
            root = loader.load();
            RiddleController riddleController = loader.getController();
            riddleController.setMain(main);
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
                flyButton.setOnAction(this::fly);

                flightBar = new ProgressBar();
                flightBar.setLayoutX(468.0);
                flightBar.setLayoutY(94);
                flightBar.setPrefHeight(15.0);
                flightBar.setPrefWidth(158.0);
                flightBar.setStyle("-fx-accent: blue;");
                flightBar.setProgress(0.5);

                flightLabel = new Label();
                flightLabel.setLayoutX(527);
                flightLabel.setLayoutY(75);
                flightLabel.setText(" Flight ");
                flightLabel.setStyle("-fx-background-color: White; -fx-background-radius: 5px; -fx-label-padding: 0  2px;");

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


                photosensitivtyLabel = new Label();
                photosensitivtyLabel.setLayoutX(500);
                photosensitivtyLabel.setLayoutY(75);
                photosensitivtyLabel.setText(" Photosensitivity ");
                photosensitivtyLabel.setStyle("-fx-background-color: White; -fx-background-radius: 5px; -fx-label-padding: 0  2px;");

                pane.getChildren().add(photosensitivityBar);
                pane.getChildren().add(photosensitivtyLabel);
                displayCurrCoin();
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
                shapeshiftBar.setLayoutY(94);
                shapeshiftBar.setPrefHeight(15.0);
                shapeshiftBar.setPrefWidth(158.0);
                shapeshiftBar.setStyle("-fx-accent: green;");
                shapeshiftBar.setProgress(0.5);
                pane.getChildren().add(shapeshiftButton);
                shapeshiftButton.setOnAction(this::shapeshift);

                shapeshiftLabel = new Label();
                shapeshiftLabel.setLayoutX(510);
                shapeshiftLabel.setLayoutY(75);
                shapeshiftLabel.setText(" Shape-shift ");
                shapeshiftLabel.setStyle("-fx-background-color: White; -fx-background-radius: 5px; -fx-label-padding: 0  2px;");


                pane.getChildren().add(shapeshiftBar);
                pane.getChildren().add(shapeshiftLabel);
                displayCurrCoin();
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
        displayCurrCoin();
        displayInventory();

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

    public void setVampireBurning(){
        creatureView.setImage(((Vampire)main.getEngine().getCreature()).getBurningSprite());

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
        displayInventory();
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
        if (main.getEngine().getCreature().sleep()) {
            updateBars();
            updateTime();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("The " + main.getEngine().getCreature().getName() + " slept, the vitals have been increased");
            alert.showAndWait();
        }
        System.out.println("Sleeping");
    }

    public void shapeshift(ActionEvent event) {
        if (((Alien)main.getEngine().getCreature()).changeShape()) {
            if (creatureView.getImage().equals(((Alien)main.getEngine().getCreature()).getShapeshiftSprite())) {
                creatureView.setImage(((Alien)main.getEngine().getCreature()).getSprite());
            }
            else {
                creatureView.setImage(((Alien)main.getEngine().getCreature()).getShapeshiftSprite());
            }
            updateBars();
            // creatureView.setImage(((Alien)main.getEngine().getCreature()).getShapeshiftSprite());
            System.out.println("SHAPESHIFT");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("The " + main.getEngine().getCreature().getName() + " cannot shapeshift anymore");
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

    public Stage getStage() {
        return stage;
    }

    public void setMain(Main theMainInstance){
        this.main = theMainInstance;
    }
}
