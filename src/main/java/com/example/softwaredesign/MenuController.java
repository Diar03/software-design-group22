package com.example.softwaredesign;

import javafx.scene.control.Alert;

import java.io.IOException;

public class MenuController extends Screen{

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

    public void startTheGame() throws IOException {
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

            Creature creature = CreatureFactory.getCreature(creatureSelected, engine.getEnvironment());
            if(creature != null){
                engine.setCreature(creature);
            }
            displayGameScreen();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an environment and a creature.");
            alert.showAndWait();
        }
    }


}