package softwaredesign;

class Environment{
    String name;
    Boolean isRaining;
    int sunlightIntensity;
    Time timeOfDay;

    public Environment(String name, Boolean isRaining, int sunlightIntensity, Time timeOfDay) {
        this.name = name;
        this.isRaining = isRaining;
        this.sunlightIntensity = sunlightIntensity;
        this.timeOfDay = timeOfDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRaining() {
        return isRaining;
    }

    public void setRaining(Boolean raining) {
        isRaining = raining;
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

    static void setNextEnvironment() {}
}
