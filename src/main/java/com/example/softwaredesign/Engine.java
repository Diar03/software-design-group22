package com.example.softwaredesign;

public class Engine {
    private Initalizer initalizer;
    private Creature creature;
    private Environment environment;

    public Screen getScreenController() {
        return screenController;
    }

    public void setScreenController(Screen screenController) {
        this.screenController = screenController;
    }

    private Screen screenController;

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
    private static Engine instance = null;

    private Engine() {
        initalizer = null;
        environment = null;
        creature = null;
    }
    public static Engine getInstance(){
        if (instance == null){
            instance = new Engine();
        }
        return instance;
    }

    static void displayMainScreen() {}
    static void displayGameMenu() {}
    static void handleDeath() {}
}
