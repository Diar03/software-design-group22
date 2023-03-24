package com.example.softwaredesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;
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

    public void antarcticaChosen(ActionEvent e){ environmentSelected = "Antarctica"; }

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

    public void startTheGame(ActionEvent e) throws IOException {
        if (environmentSelected != null && creatureSelected != null) {
            switch (environmentSelected){
                case "Forest":
                    main.getEngine().setEnvironment(Environment.getInstance(environmentSelected, 4, Time.DAY));
                    break;
                case "Antarctica":
                    main.getEngine().setEnvironment(Environment.getInstance(environmentSelected, 2, Time.DAY));
                    break;
                case "Desert":
                    main.getEngine().setEnvironment(Environment.getInstance(environmentSelected, 10, Time.DAY));
                    break;
                default:
                    System.err.println("Wrong value passed to start game. Please exit and restart game");
            }

            main.getEngine().setCreature(Creature.getInstance(creatureSelected,main.getEngine().getEnvironment()));

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gameScreen.fxml"));
            root = loader.load();
            GameController controller = loader.getController();
            controller.setMain(main);
            controller.adaptScreenToCreature();
            controller.loadImages();

            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            main.initSchedulers(controller);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an environment and a creature.");
            alert.showAndWait();
        }
    }

    public void setMain(Main theMainInstance){
        this.main = theMainInstance;
    }

}