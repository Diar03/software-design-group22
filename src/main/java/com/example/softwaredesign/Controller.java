package com.example.softwaredesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class Controller {
    @FXML
    private Label welcomeText;
    @FXML
    private Button startButton;
    @FXML
    private RadioButton forestButton, antarcticaButton, desertButton, vampireButton, birdButton, alienButton;


    public String selectedEnvironment;
    public String selectedCreature;

    public void forestSelected(ActionEvent e){
        selectedEnvironment = "Forest";
    }

    public void antarcticaSelected(ActionEvent e){
        selectedEnvironment = "Antarctica";
    }

    public void desertSelected(ActionEvent e){
        selectedEnvironment = "Desert";
    }

    public void vampireSelected(ActionEvent e){
        selectedCreature = "Vampire";
    }

    public void birdSelected(ActionEvent e){
        selectedCreature = "Bird";
    }
    public void alienSelected(ActionEvent e){
        selectedCreature = "Alien";
    }

    public void startTheGame(ActionEvent e){
        if (selectedEnvironment != null && selectedCreature != null) {
            // signal the main thread to continue
            //main.startGame(selectedEnvironment, selectedCreature);
            System.out.println("Starting the game");


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an environment and a creature.");
            alert.showAndWait();
        }
    }
}