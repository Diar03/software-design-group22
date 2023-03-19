package com.example.softwaredesign;

public class Vital {
    private static int percentageLevel;
    private static String name;

    public Vital(int percentageLevel, String name) {
        this.percentageLevel = percentageLevel;
        this.name = name;
    }

    public int getPercentageLevel() {
        return percentageLevel;
    }

    public void setPercentageLevel(int percentageLevel) {
        this.percentageLevel = percentageLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int increaseVital(int value){
        int newVal =  getPercentageLevel();
        newVal = newVal + value;
        return newVal;
    }
    public int decreaseVital(int value){
        int newVal =  getPercentageLevel();
        newVal = newVal - value;
        return newVal;
    }
}
