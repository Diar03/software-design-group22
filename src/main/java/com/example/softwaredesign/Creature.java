package com.example.softwaredesign;

import java.util.*;

abstract class Creature {
     int coins;
     Boolean isHungry;
     Set<Vital> vitals;
     Map<Food, Integer> inventory;

    static void playMiniGame() {}
    static void sleep() {}
    abstract void initVitals();
    static void increaseCoins(int value) {}
    static void eat(Food food){}
}

class Bird extends Creature {

    public Bird(){
        this.initVitals();
        this.isHungry = false;
        this.inventory = new HashMap<Food, Integer>();
    }
    @Override
    void initVitals() {
        Vital flight = new Vital(50, "Flight");
        Vital hunger = new Vital(50, "Hunger");
        Vital health = new Vital(50, "Health");
        this.vitals = new HashSet<Vital>();
        this.vitals.add(flight);
        this.vitals.add(hunger);
        this.vitals.add(health);
    }

    static void fly(){}
}

class Vampire extends Creature {
    Boolean isBurning;
    public Vampire(){
        this.initVitals();
        this.isHungry = false;
        this.isBurning = false;
        this.inventory = new HashMap<Food, Integer>();
    }
    @Override
    void initVitals() {
        Vital photosensitivity = new Vital(50, "Photosensitivity");
        Vital hunger = new Vital(50, "Hunger");
        Vital health = new Vital(50, "Health");
        this.vitals = new HashSet<Vital>();
        this.vitals.add(photosensitivity);
        this.vitals.add(hunger);
        this.vitals.add(health);
    }
}

class Alien extends Creature {

    public Alien(){
        this.initVitals();
        this.isHungry = false;
        this.inventory = new HashMap<Food, Integer>();
    }
    @Override
    void initVitals() {
        Vital shapeshift = new Vital(50, "Shapeshift");
        Vital hunger = new Vital(50, "Hunger");
        Vital health = new Vital(50, "Health");
        this.vitals = new HashSet<Vital>();
        this.vitals.add(shapeshift);
        this.vitals.add(hunger);
        this.vitals.add(health);
    }

    static void changeShape() {}
}