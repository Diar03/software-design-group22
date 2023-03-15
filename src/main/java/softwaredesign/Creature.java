package softwaredesign;

import java.util.ArrayList;
abstract class Creature {
    String deathSound;
    int coins;
    Boolean isHungry;
    Vital health;
    Vital hunger;
    ArrayList<Food> inventory = new ArrayList<Food>();

    static void playMiniGame() {}
    static void sleep() {}
    static void initVitals() {}
    static void increaseCoins(int value) {}
    static void eat(Food food){}
}

class Bird extends Creature {
    Vital flight;

    static void fly(){}
}

class Vampire extends Creature {
    Vital photosensitivity;
    Boolean isBurning;

    static int checkSunlight(){return 10;}
}

class Alien extends Creature {
    Vital shapeShift;

    static void changeShape() {}
}