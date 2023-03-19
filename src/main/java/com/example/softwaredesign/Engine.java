package com.example.softwaredesign;

public class Engine {
    Initalizer initalizer;
    Creature creature;
    Environment environment;
    //add the map with String to Image


    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    static void displayMainScreen() {}
    static void displayGameMenu() {}
    static void handleDeath() {}
}
