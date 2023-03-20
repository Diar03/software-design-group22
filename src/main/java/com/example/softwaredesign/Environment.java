package com.example.softwaredesign;

import com.example.softwaredesign.Time;
import javafx.scene.image.Image;

class Environment{
    String name;

    Image sprite;
    int sunlightIntensity;
    Time timeOfDay;

    public Environment(String name, int sunlightIntensity, Time timeOfDay) {
        this.name = name;
        switch (name){
            case "Forest":
                sprite = new Image(getClass().getResourceAsStream("forestDay.png"));
                break;
            case "Desert":
                sprite = new Image(getClass().getResourceAsStream("desertDay.png"));
                break;
            case "Antarctica":
                sprite = new Image(getClass().getResourceAsStream("iceDay.png"));
                break;
            default:
                System.out.println("No such environment found");
                break;
        }
        this.sunlightIntensity = sunlightIntensity;
        this.timeOfDay = timeOfDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Boolean getRaining() {
        return isRaining;
    }

    public void setRaining(Boolean raining) {
        isRaining = raining;
    }*/

    public int getSunlightIntensity() {
        return sunlightIntensity;
    }

    public void setSunlightIntensity(int sunlightIntensity) {
        this.sunlightIntensity = sunlightIntensity;
    }

    public Time getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(Time timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    void setNextTimeOfDay() {
        switch (this.timeOfDay){
            case DAY:
                this.timeOfDay = Time.NIGHT;
                break;
            case NIGHT:
                this.timeOfDay = Time.DAY;
                break;
        }
    }
}
