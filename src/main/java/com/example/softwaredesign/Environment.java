package com.example.softwaredesign;

import com.example.softwaredesign.Time;
import javafx.scene.image.Image;

class Environment{
    private String name;

    private Image daySprite;
    private Image nightSprite;
    private int sunlightIntensity;
    private Time timeOfDay;
    private static Environment instance = null;
    private Environment(String name, int sunlightIntensity, Time timeOfDay) {
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
    public static Environment getInstance(String name, int sunlightIntensity, Time timeOfDay){
        if (instance == null){
            instance = new Environment(name,sunlightIntensity, timeOfDay);
        }
        return instance;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


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

    public Image getDaySprite() {
        return daySprite;
    }

    public void setDaySprite(Image daySprite) {
        this.daySprite = daySprite;
    }

    public Image getNightSprite() {
        return nightSprite;
    }

    public void setNightSprite(Image nightSprite) {
        this.nightSprite = nightSprite;
    }
}
