package com.example.softwaredesign;

public class CreatureFactory {
    public static Creature getCreature(String chosenCreature,Environment env){
            switch (chosenCreature){
                case "Bird":
                    return new Bird(env);
                case "Vampire":
                    return new Vampire(env);
                case "Alien":
                    return new Alien(env);
                default:
                    // Add other creatures here in a separate case statement
                    break;
            }
        return null;
    }
}
