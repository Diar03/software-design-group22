package com.example.softwaredesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.action.Action;

import java.io.FileInputStream;
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

    private Food[] shopItems = {Food.MEAT, Food.SALAD};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        shop.getItems().addAll(shopItems);
        shop.setOnAction(this::buyFood);
    }

    public void buyFood(ActionEvent event){
        Food choice = shop.getValue();
        main.buyFood(choice);
    }

    public void loadImages(String env, String creature){

        environmentView.setImage(main.engine.getEnvironment().sprite);
        creatureView.setImage(main.engine.getCreature().sprite);

    }
    public void eat(){
        System.out.println("Eating");
    }

    public void sleep(){
        System.out.println("Sleeping");
    }
    public void setMain(Main theMainInstance){
        this.main = theMainInstance;
    }
}
