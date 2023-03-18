package com.example.softwaredesign;

import java.util.ArrayList;
abstract class Creature {
     String deathSound;
     int coins;
     Boolean isHungry;
     Vital health;
     Vital hunger;
     ArrayList<Food> inventory = new ArrayList<Food>();

    public Creature(String deathSound, int coins, Boolean isHungry, Vital health, Vital hunger, ArrayList<Food> inventory) {
        this.deathSound = deathSound;
        this.coins = coins;
        this.isHungry = isHungry;
        this.health = health;
        this.hunger = hunger;
        this.inventory = inventory;
    }

    public String getDeathSound() {
        return deathSound;
    }

    public void setDeathSound(String deathSound) {
        this.deathSound = deathSound;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Boolean getHungry() {
        return isHungry;
    }

    public void setHungry(Boolean hungry) {
        isHungry = hungry;
    }

    public Vital getHealth() {
        return health;
    }

    public void setHealth(Vital health) {
        this.health = health;
    }

    public Vital getHunger() {
        return hunger;
    }

    public void setHunger(Vital hunger) {
        this.hunger = hunger;
    }

    public ArrayList<Food> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Food> inventory) {
        this.inventory = inventory;
    }

    static void playMiniGame() {}
    static void sleep() {}
    static void initVitals() {}
    static void increaseCoins(int value) {}
    static void eat(Food food){}
}

class Bird extends Creature {
    Vital flight;

    public Bird(String deathSound, int coins, Boolean isHungry, Vital health, Vital hunger, ArrayList<Food> inventory, Vital flight) {
        super(deathSound, coins, isHungry, health, hunger, inventory);
        this.flight = flight;
    }

    public Vital getFlight() {
        return flight;
    }

    public void setFlight(Vital flight) {
        this.flight = flight;
    }

    static void fly(){}
}

class Vampire extends Creature {
    Vital photosensitivity;
    Boolean isBurning;

    public Vampire(String deathSound, int coins, Boolean isHungry, Vital health, Vital hunger, ArrayList<Food> inventory, Vital photosensitivity, Boolean isBurning) {
        super(deathSound, coins, isHungry, health, hunger, inventory);
        this.photosensitivity = photosensitivity;
        this.isBurning = isBurning;
    }

    public Vital getPhotosensitivity() {
        return photosensitivity;
    }

    public void setPhotosensitivity(Vital photosensitivity) {
        this.photosensitivity = photosensitivity;
    }

    public Boolean getBurning() {
        return isBurning;
    }

    public void setBurning(Boolean burning) {
        isBurning = burning;
    }

    static int checkSunlight(){return 10;}
}

class Alien extends Creature {
    Vital shapeShift;

    public Alien(String deathSound, int coins, Boolean isHungry, Vital health, Vital hunger, ArrayList<Food> inventory, Vital shapeShift) {
        super(deathSound, coins, isHungry, health, hunger, inventory);
        this.shapeShift = shapeShift;
    }

    public Vital getShapeShift() {
        return shapeShift;
    }

    public void setShapeShift(Vital shapeShift) {
        this.shapeShift = shapeShift;
    }

    static void changeShape() {}
}