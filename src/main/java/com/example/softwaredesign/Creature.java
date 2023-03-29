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

     protected static final String HUNGER_STR = "Hunger";
     protected static final String HEALTH_STR = "Health";

     private Vital hunger;
     private Vital health;
     private Map<Food, Integer> inventory;

     private static Creature instance = null;
     Creature(){}

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
                     // Add other creatures here in a separate case statement
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
        updateExtension();
        int currentHunger = hunger.getPercentageLevel();
        if(currentHunger < 20){
            isHungry = true;
        }

        if(Boolean.TRUE.equals(isHungry)){
            hunger.decreaseVital(1);
            health.decreaseVital(1);
        }else{
            hunger.decreaseVital(2);
        }

        return (health.getPercentageLevel() > 0);
    }

    public abstract void updateExtension();

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
                hunger.increaseVital(20);
                break;
            case SALAD:
                hunger.increaseVital(25);
                break;
            default:
                // Future foods can be added with further cases
                break;
        }

        inventory.put(food, inventory.get(food) - 1);
    }
    void increaseCoins(int value) {
        int currVal = getCoins();
        setCoins(currVal + value);
    }
    void decreaseCoins(int value) {
        int currVal = getCoins();
        setCoins(currVal - value);
    }

}

class Bird extends Creature {

    private Vital flight;

    private boolean isFlying;

    private Image flyingSprite;

    public Bird(Environment env){
        super();
        setSprite(new Image(getClass().getResourceAsStream("tweetyIdle.png")));
        setFlyingSprite(new Image(getClass().getResourceAsStream("ftweetyFlying.gif")));
        this.initVitals();
        setHungry(false);
        setFlying(false);
        setEnvironment(env);
        setInventory( new HashMap<>());
        setName("Bird");
    }

    public Image getFlyingSprite() {
        return flyingSprite;
    }

    public void setFlyingSprite(Image sprite) {
        this.flyingSprite = sprite;
    }

    public Vital getFlight() {
        return flight;
    }

    public void setFlight(Vital flight) {
        this.flight = flight;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setFlying(boolean flying) {
        isFlying = flying;
    }

    @Override
    void initVitals() {
        setFlight(new Vital(50, "Flight"));
        setHunger(new Vital(50, HUNGER_STR));
        setHealth(new Vital(50, HEALTH_STR));
    }

    @Override
    public void updateExtension() {
        if(isFlying()){
            this.getFlight().decreaseVital(20);
            this.getHealth().increaseVital(5);
            this.getHunger().increaseVital(5);
        }
        if(this.getFlight().getPercentageLevel() <= 0){
            ((GameController) Engine.getInstance().getScreenController()).stopFlight();
            setFlying(false);
        }
    }

    @Override
    public boolean sleep() {
        if(getEnvironment().getTimeOfDay() == Time.NIGHT) {
            flight.increaseVital(20);
            getHunger().increaseVital(20);
            getHealth().increaseVital(20);
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

    public boolean fly(){
        if(isFlying()){
            setFlying(false);
            return false;
        }
        if(this.getFlight().getPercentageLevel() <= 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You do not have any flight left...");
            alert.showAndWait();
            setFlying(false);
            return false;
        }else{
            setFlying(true);
            return true;
        }

    }
}

class Vampire extends Creature {
    private Boolean isBurning;
    private Vital photosensitivity;
    private Image burningSprite;

    public Vampire(Environment env){
        setSprite(new Image(getClass().getResourceAsStream("vampireIdle.png")));
        setBurningSprite(new Image(getClass().getResourceAsStream("vampireFire.gif")));
        initVitals();
        setHungry(false);
        setBurning(false);
        setEnvironment(env);
        setInventory(new HashMap<>());
        setName("Vampire");
    }

    public Boolean getBurning() {
        return isBurning;
    }

    public void setBurning(Boolean burning) {
        isBurning = burning;
    }

    public Image getBurningSprite() {
        return burningSprite;
    }

    public void setBurningSprite(Image sprite) {
        this.burningSprite = sprite;
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
        setHunger(new Vital(50, HUNGER_STR));
        setHealth(new Vital(50, HEALTH_STR));
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
    public void updateExtension() {
        if(getEnvironment().getTimeOfDay() == Time.DAY){
            int currentIntensity = getEnvironment().getSunlightIntensity();
            photosensitivity.decreaseVital(currentIntensity);
        }else{
            photosensitivity.increaseVital(10);
        }

        if(photosensitivity.getPercentageLevel() < 20){
            ((GameController) Engine.getInstance().getScreenController()).startBurning();
            setBurning(true);
        }else{
            ((GameController) Engine.getInstance().getScreenController()).stopBurning();
            setBurning(false);
        }
    }
}

class Alien extends Creature {
    private Vital shapeshift;
    private Image shapeshiftSprite;
    public Alien(Environment env){
        setSprite(new Image(getClass().getResourceAsStream("alienIdle.png")));
        setShapeshiftSprite(new Image(getClass().getResourceAsStream("alienShapeshift.png")));
        initVitals();
        setHungry(false);
        setEnvironment(env);
        setInventory(new HashMap<>());
        setName("Alien");
    }

    public Image getShapeshiftSprite() {
        return shapeshiftSprite;
    }

    public void setShapeshiftSprite(Image sprite) {
        this.shapeshiftSprite = sprite;
    }

    @Override
    void initVitals() {
        setShapeshift(new Vital(50, "Shapeshift"));
        setHunger(new Vital(50, HUNGER_STR));
        setHealth(new Vital(50, HEALTH_STR));
    }

    @Override
    public void updateExtension() {/* No additional functionality needed for alien in update */}

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

    public boolean changeShape() {
        if(shapeshift.getPercentageLevel() - 20 < 0) {
            return false;
        }
        shapeshift.decreaseVital(20);
        return true;
    }
}