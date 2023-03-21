package com.example.softwaredesign;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

abstract class Creature {
     int coins = 50;
     Image sprite;
     Boolean isHungry;

     String name;

     Environment environment;

     Vital hunger;
     Vital health;
     //Set<Vital> vitals;
     Map<Food, Integer> inventory;

    static void playMiniGame() {}
    static void sleep() {}
    abstract void initVitals();

    public boolean update(){
        int currentHunger = hunger.getPercentageLevel();
        if(currentHunger < 20){
            isHungry = true;
        }

        if(isHungry){
            hunger.decreaseVital(2);
            health.decreaseVital(2);
        }else{
            hunger.decreaseVital(3);
        }

        if(health.getPercentageLevel() <= 0){
            return false;
        }else{
            return true;
        }
    }
    static void increaseCoins(int value) {}
    public void eat(Food food){

        if(!inventory.containsKey(food)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You do not have any " + food.toString() + " left");
            alert.showAndWait();
            return;
        }


        if(inventory.get(food) < 1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You do not have any " + food.toString() + " left");
            alert.showAndWait();
            return;
        }

        switch (food){
            case MEAT:
                if((hunger.getPercentageLevel() + 20) > 100){
                    hunger.increaseVital(100 - hunger.getPercentageLevel());
                }else{
                    hunger.increaseVital(20);
                }
                break;
            case SALAD:
                if((hunger.getPercentageLevel() + 25) > 100){
                    hunger.increaseVital(100 - hunger.getPercentageLevel());
                }else{
                    hunger.increaseVital(25);
                }
                break;
        }

        inventory.put(food, inventory.get(food) - 1);
    }
}

class Bird extends Creature {

    Vital flight;

    public Bird(Environment env){
        this.sprite = new Image(getClass().getResourceAsStream("tweetyIdle.png"));
        this.initVitals();
        this.isHungry = false;
        this.environment = env;
        this.inventory = new HashMap<Food, Integer>();
        this.name = "Bird";
    }
    @Override
    void initVitals() {
        flight = new Vital(50, "Flight");
        hunger = new Vital(50, "Hunger");
        health = new Vital(50, "Health");
    }

    public void fly(){}
}

class Vampire extends Creature {
    Boolean isBurning;
    Vital photosensitivity;
    public Vampire(Environment env){
        this.sprite = new Image(getClass().getResourceAsStream("vampireIdle.png"));
        initVitals();
        this.isHungry = false;
        this.isBurning = false;
        this.environment = env;
        this.inventory = new HashMap<Food, Integer>();
        this.name = "Vampire";
    }
    @Override
    void initVitals() {
        photosensitivity = new Vital(50, "Photosensitivity");
        hunger = new Vital(50, "Hunger");
        health = new Vital(50, "Health");

    }

    @Override
    public boolean update() {

        if(environment.getTimeOfDay() == Time.DAY){
            int currentIntensity = environment.getSunlightIntensity();
            photosensitivity.decreaseVital(currentIntensity);
        }

        if(hunger.getPercentageLevel() < 20){
            isHungry = true;
        }


        if(photosensitivity.getPercentageLevel() < 20){
            isBurning = true;
        }


        if(isHungry){
            hunger.decreaseVital(2);
            health.decreaseVital(2);
        }else{
            hunger.decreaseVital(3);
        }

        if(isBurning){
            health.decreaseVital(5);
        }

        if(health.getPercentageLevel() <= 0){
            return false;
        }else{
            return true;
        }

    }
}

class Alien extends Creature {
    Vital shapeshift;
    public Alien(Environment env){
        this.sprite = new Image(getClass().getResourceAsStream("alienIdle.png"));
        initVitals();
        this.isHungry = false;
        this.environment = env;
        this.inventory = new HashMap<>();
        this.name = "Alien";
    }
    @Override
    void initVitals() {
        shapeshift = new Vital(50, "Shapeshift");
        hunger = new Vital(50, "Hunger");
        health = new Vital(50, "Health");
    }

    public void changeShape() {}
}