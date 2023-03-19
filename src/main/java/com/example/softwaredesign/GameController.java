package com.example.softwaredesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
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
    private ChoiceBox<Food> eatChoiceBox;

    private Food[] shopItems = {Food.MEAT, Food.SALAD};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        shop.getItems().addAll(shopItems);
        eatChoiceBox.getItems().addAll(shopItems);
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
