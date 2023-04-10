package com.example.softwaredesign;

public class Vital {
    private int percentageLevel;
    private String name;

    public Vital(int percentageLevel, String name) {
        setPercentageLevel(percentageLevel);
        setName(name);
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

    public void increaseVital(int value){
        int newVal =  getPercentageLevel();
        setPercentageLevel(newVal + value);
        if(newVal + value > 100){
            setPercentageLevel(100);
        }else {
            setPercentageLevel(newVal + value);
        }
    }
    public void decreaseVital(int value){
        int newVal =  getPercentageLevel();
        if(newVal - value < 0){
            setPercentageLevel(0);
        }else {
            setPercentageLevel(newVal - value);
        }
    }
}
