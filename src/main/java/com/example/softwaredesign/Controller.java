package com.example.softwaredesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller extends Screen{

    private String environmentSelected;
    private String creatureSelected;

    public void forestChosen(){
        environmentSelected = "Forest";
    }

    public void antarcticaChosen(){ environmentSelected = "Antarctica"; }

    public void desertChosen(){
        environmentSelected = "Desert";
    }
    public void birdChosen(){
        creatureSelected = "Bird";
    }
    public void vampireChosen(){
        creatureSelected = "Vampire";
    }
    public void alienChosen(){
        creatureSelected = "Alien";
    }

    public void startTheGame(ActionEvent e) throws IOException {
        if (environmentSelected != null && creatureSelected != null) {
            switch (environmentSelected){
                case "Forest":
                    engine.setEnvironment(Environment.getInstance(environmentSelected, 4, Time.DAY));
                    break;
                case "Antarctica":
                    engine.setEnvironment(Environment.getInstance(environmentSelected, 2, Time.DAY));
                    break;
                case "Desert":
                    engine.setEnvironment(Environment.getInstance(environmentSelected, 10, Time.DAY));
                    break;
                default:
                    // If you have a new environment, it should be here in a case statement
                    break;
            }

            engine.setCreature(Creature.getInstance(creatureSelected,engine.getEnvironment()));

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gameScreen.fxml"));
            Parent root = loader.load();
            GameController controller = loader.getController();
            Engine.getInstance().setScreenController(controller);
            controller.adaptScreenToCreature();
            controller.loadImages();

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            engine.initSchedulers(controller);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an environment and a creature.");
            alert.showAndWait();
        }
    }


}