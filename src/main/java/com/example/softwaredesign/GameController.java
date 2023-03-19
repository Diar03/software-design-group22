package com.example.softwaredesign;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class GameController {
    private Main main;

    @FXML
    private ImageView environmentView;

    @FXML
    private ImageView creatureView;

    public void loadImages(String env, String creature){
        Image environmentImage;
        Image creatureImage;

        switch (env){
            case "Forest":
                environmentImage = new Image(getClass().getResourceAsStream("forestDay.png"));
                environmentView.setImage(environmentImage);
                break;
            case "Desert":
                environmentImage = new Image(getClass().getResourceAsStream("desertDay.png"));
                environmentView.setImage(environmentImage);
                break;
            case "Antarctica":
                environmentImage = new Image(getClass().getResourceAsStream("iceDay.png"));
                environmentView.setImage(environmentImage);
                break;
        }

        switch (creature){
            case "Bird":
                creatureImage = new Image(getClass().getResourceAsStream("tweetyIdle.png"));
                creatureView.setImage(creatureImage);
                break;
            case "Vampire":
                creatureImage = new Image(getClass().getResourceAsStream("tweetyIdle.png"));
                creatureView.setImage(creatureImage);
                break;
            case "Alien":
                creatureImage = new Image(getClass().getResourceAsStream("tweetyIdle.png"));
                creatureView.setImage(creatureImage);
                break;
        }
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
