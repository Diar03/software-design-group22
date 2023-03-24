package com.example.softwaredesign;

public class Engine {
    private Initalizer initalizer;
    private Creature creature;
    private Environment environment;

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

    public Initalizer getInitalizer() {
        return initalizer;
    }

    public void setInitalizer(Initalizer initalizer) {
        this.initalizer = initalizer;
    }

    static void displayMainScreen() {}
    static void displayGameMenu() {}
    static void handleDeath() {}
}
