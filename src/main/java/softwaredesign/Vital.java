package softwaredesign;

public class Vital {
    private static int percentageLevel;
    private static String name;

    public Vital(int percentageLevel, String name) {
        this.percentageLevel = percentageLevel;
        this.name = name;
    }

    public static int getPercentageLevel() {
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

    public static int increaseVital(int value){
        int newVal =  getPercentageLevel();
        newVal = newVal + value;
        return newVal;
    }
    public static int decreaseVital(int value){
        int newVal =  getPercentageLevel();
        newVal = newVal - value;
        return newVal;
    }
}
