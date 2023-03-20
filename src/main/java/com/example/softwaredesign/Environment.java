package com.example.softwaredesign;

import com.example.softwaredesign.Time;
import javafx.scene.image.Image;

class Environment{
    String name;

    Image daySprite;
    Image nightSprite;
    int sunlightIntensity;
    Time timeOfDay;

    public Environment(String name, int sunlightIntensity, Time timeOfDay) {
        this.name = name;
        switch (name){
            case "Forest":
                daySprite = new Image(getClass().getResourceAsStream("forestDay.png"));
                nightSprite = new Image(getClass().getResourceAsStream("forestNight.png"));
                break;
            case "Desert":
                daySprite = new Image(getClass().getResourceAsStream("desertDay.png"));
                nightSprite = new Image(getClass().getResourceAsStream("DesertNight.png"));
                break;
            case "Antarctica":
                daySprite = new Image(getClass().getResourceAsStream("iceDay.png"));
                nightSprite = new Image(getClass().getResourceAsStream("iceDark.png"));
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
