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
        switch (main.engine.creature.name){
            case "Bird":
                // Code for adding bird nodes
                break;
            case "Vampire":
                // Code for adding vampire nodes
                break;
            case "Alien":
                // Code for adding alien nodes
                break;
        }

        /*Button smth = new Button();
        smth.setLayoutX(14);
        smth.setLayoutY(394);
        smth.setText("Fly");
        smth.setOnAction(e->{
            System.out.println("Fly");
        });
        pane.getChildren().add(smth);*/
        shop.getItems().addAll(shopItems);
        eatChoiceBox.getItems().addAll(shopItems);
        hungerBar.setStyle("-fx-accent: #c4591b;");
        healthBar.setStyle("-fx-accent: red;");
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


    }

    public void loadImages(){
        environmentView.setImage(main.engine.getEnvironment().sprite);
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
    }

    public void sleep(){
        System.out.println("Sleeping");
    }
    public void setMain(Main theMainInstance){
        this.main = theMainInstance;
    }
}
