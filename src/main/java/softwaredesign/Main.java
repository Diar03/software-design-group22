package softwaredesign;

import java.util.Set;
import java.util.Scanner;


public class Main {
    public static void main (String[] args){
        Engine engine = new Engine();

        Scanner input = new Scanner(System.in);

        Boolean isEnvChosen = false;
        while(!isEnvChosen){
            String env = input.nextLine();
            switch (env){
                case "Forest":
                    engine.environment = new Environment("Forest", 2,Time.MORNING);
                    System.out.println("Forest chosen");
                    isEnvChosen = true;
                    break;
                case "Snow land":
                    engine.environment = new Environment("Snow land", 3,Time.MORNING);
                    System.out.println("Snow land chosen");
                    isEnvChosen = true;
                    break;
                case "Desert":
                    engine.environment = new Environment("Desert", 5,Time.MORNING);
                    System.out.println("Desert chosen");
                    isEnvChosen = true;
                    break;
                default:
                    System.out.println("Please enter a valid environemnt.\nThe input is case sensitive!");
                    continue;
            }
        }

        // Make 3 objects of environment with different values
        // User chooses environment. environment variable is going to be set to the corresponding env

        // Choose the creature
        // Make a new creature of that class and set the appropriate values


        // Start scheduler for vitals
        // Start scheduler for day, afternoon and night
        // Display that game has started

        // While loop to handle input


    }
}