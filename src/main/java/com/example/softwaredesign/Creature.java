package com.example.softwaredesign;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public abstract class Creature {
     private int coins = 50;
     private Image sprite;
     private Boolean isHungry;

     private String name;

     private Environment environment;

     private Vital hunger;
     private Vital health;
     private Map<Food, Integer> inventory;

     private static Creature instance = null;
     private Creature(){};
     public static Creature getInstance(String chosenCreature,Environment env){
         if (instance == null){
             switch (chosenCreature){
                 case "Bird":
                     instance = new Bird(env);
                     break;
                 case "Vampire":
                     instance = new Vampire(env);
                     break;
                 case "Alien":
                     instance = new Alien(env);
                     break;
                 default:
                     System.err.println("Invalid creature!!");
                     break;
             }
         }
         return instance;
     }

    public int getCoins() {
        return this.coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public Boolean getHungry() {
        return isHungry;
    }

    public void setHungry(Boolean hungry) {
        isHungry = hungry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Vital getHunger() {
        return hunger;
    }

    public void setHunger(Vital hunger) {
        this.hunger = hunger;
    }

    public Vital getHealth() {
        return health;
    }

    public void setHealth(Vital health) {
        this.health = health;
    }

    public Map<Food, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Food, Integer> inventory) {
        this.inventory = inventory;
    }

    static void playMiniGame() {}

    abstract boolean sleep();
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

        return (health.getPercentageLevel() > 0);
    }

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
    void increaseCoins(int value) {
        int currVal = getCoins();
        setCoins(currVal + value);
    }
    void deccreaseCoins(int value) {
        int currVal = getCoins();
        setCoins(currVal - value);
    }

}

class Bird extends Creature {

    private Vital flight;

    public Bird(Environment env){
        super();
        setSprite(new Image(getClass().getResourceAsStream("tweetyIdle.png")));
        this.initVitals();
        setHungry(false);
        setEnvironment(env);
        setInventory( new HashMap<Food, Integer>());
        setName("Bird");
    }

    public Vital getFlight() {
        return flight;
    }

    public void setFlight(Vital flight) {
        this.flight = flight;
    }

    @Override
    void initVitals() {
        setFlight(new Vital(50, "Flight"));
        setHunger(new Vital(50, "Hunger"));
        setHealth(new Vital(50, "Health"));
    }

    @Override
    boolean sleep() {
        if(getEnvironment().getTimeOfDay() == Time.NIGHT) {
            if((flight.getPercentageLevel() + 20) > 100){
                flight.increaseVital(100 - flight.getPercentageLevel());
            }else{
                flight.increaseVital(20);
            }
            if((getHunger().getPercentageLevel() + 20) > 100){
                getHunger().increaseVital(100 - getHunger().getPercentageLevel());
            }else{
                getHunger().increaseVital(20);
            }
            if((getHealth().getPercentageLevel() + 20) > 100){
                getHealth().increaseVital(100 - getHealth().getPercentageLevel());
            }else{
                getHealth().increaseVital(20);
            }
            getEnvironment().setNextTimeOfDay();
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You cannot sleep during the Day");
            alert.showAndWait();
            return false;
        }
    }

    public void fly(){}
}

class Vampire extends Creature {
    private Boolean isBurning;
    private Vital photosensitivity;

    public Vampire(Environment env){
        setSprite(new Image(getClass().getResourceAsStream("vampireIdle.png")));
        initVitals();
        setHungry(false);
        setBurning(false);
        setEnvironment(env);
        setInventory(new HashMap<Food, Integer>());
        setName("Vampire");
    }

    public Boolean getBurning() {
        return isBurning;
    }

    public void setBurning(Boolean burning) {
        isBurning = burning;
    }

    public Vital getPhotosensitivity() {
        return photosensitivity;
    }

    public void setPhotosensitivity(Vital photosensitivity) {
        this.photosensitivity = photosensitivity;
    }

    @Override
    void initVitals() {
        setPhotosensitivity(new Vital(50, "Photosensitivity"));
        setHunger(new Vital(50, "Hunger"));
        setHealth(new Vital(50, "Health"));
    }

    @Override
    boolean sleep() {
        if(getEnvironment().getTimeOfDay() == Time.DAY) {
            if((photosensitivity.getPercentageLevel() + 20) > 100){
                photosensitivity.increaseVital(100 - photosensitivity.getPercentageLevel());
            }else{
                photosensitivity.increaseVital(20);
            }
            if((getHunger().getPercentageLevel() + 20) > 100){
                getHunger().increaseVital(100 - getHunger().getPercentageLevel());
            }else{
                getHunger().increaseVital(20);
            }
            if((getHealth().getPercentageLevel() + 20) > 100){
                getHealth().increaseVital(100 - getHealth().getPercentageLevel());
            }else{
                getHealth().increaseVital(20);
            }
            getEnvironment().setNextTimeOfDay();
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You cannot sleep during the Night");
            alert.showAndWait();
            return false;
        }
    }

    @Override
    public boolean update() {

        if(getEnvironment().getTimeOfDay() == Time.DAY){
            int currentIntensity = getEnvironment().getSunlightIntensity();
            photosensitivity.decreaseVital(currentIntensity);
        }

        if(getHunger().getPercentageLevel() < 20){
            setHungry(true);
        }


        if(photosensitivity.getPercentageLevel() < 20){
            isBurning = true;
        }


        if(Boolean.TRUE.equals(getHungry())){
            getHunger().decreaseVital(2);
            getHealth().decreaseVital(2);
        }else{
            getHunger().decreaseVital(3);
        }

        if(Boolean.TRUE.equals(getBurning())){
            getHealth().decreaseVital(5);
        }

        return (getHealth().getPercentageLevel() > 0);

    }
}

class Alien extends Creature {
    private Vital shapeshift;
    public Alien(Environment env){
        setSprite(new Image(getClass().getResourceAsStream("alienIdle.png")));
        initVitals();
        setHungry(false);
        setEnvironment(env);
        setInventory(new HashMap<Food, Integer>());
        setName("Alien");
    }
    @Override
    void initVitals() {
        setShapeshift(new Vital(50, "Shapeshift"));
        setHunger(new Vital(50, "Hunger"));
        setHealth(new Vital(50, "Health"));
    }

    @Override
    boolean sleep() {
        if(getEnvironment().getTimeOfDay() == Time.NIGHT) {
            if((shapeshift.getPercentageLevel() + 20) > 100){
                shapeshift.increaseVital(100 - shapeshift.getPercentageLevel());
            }else{
                shapeshift.increaseVital(20);
            }
            if((getHunger().getPercentageLevel() + 20) > 100){
                getHunger().increaseVital(100 - getHunger().getPercentageLevel());
            }else{
                getHunger().increaseVital(20);
            }
            if((getHealth().getPercentageLevel() + 20) > 100){
                getHealth().increaseVital(100 - getHealth().getPercentageLevel());
            }else{
                getHealth().increaseVital(20);
            }
            getEnvironment().setNextTimeOfDay();
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You cannot sleep during the Day");
            alert.showAndWait();
            return false;
        }
    }

    public Vital getShapeshift() {
        return shapeshift;
    }

    public void setShapeshift(Vital shapeshift) {
        this.shapeshift = shapeshift;
    }

    public void changeShape() {}
}