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

    private String environmentSelected;
    private String creatureSelected;
    private Main main;

    public void forestChosen(ActionEvent e){
        environmentSelected = "Forest";
    }

    public void antarcticaChosen(ActionEvent e){
        environmentSelected = "Antarctica";
    }

    public void desertChosen(ActionEvent e){
        environmentSelected = "Desert";
    }
    public void birdChosen(ActionEvent e){
        creatureSelected = "Bird";
    }
    public void vampireChosen(ActionEvent e){
        creatureSelected = "Vampire";
    }
    public void alienChosen(ActionEvent e){
        creatureSelected = "Alien";
    }

    public void startTheGame(ActionEvent e){
        if (environmentSelected != null && creatureSelected != null) {
            main.startGame(environmentSelected, creatureSelected);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an environment and a creature.");
            alert.showAndWait();
        }
    }

    public void setMain(Main theMainInstance){
        this.main = theMainInstance;
    }


}