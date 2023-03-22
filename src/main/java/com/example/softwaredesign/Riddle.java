package com.example.softwaredesign;

import java.util.HashMap;
import java.util.Map;

public class Riddle extends MiniGame {
    public Riddle(String miniGameName){
        super(miniGameName);
    }

    private Map<String, String> questionAnswer = Map.of(
            "If there are three apples and you take away two, how many apples do you have?", "2",
            "What's full of holes but still holds water?", "Sponge",
            "How many months of the year have 28 days?", "12",
            "When Grant was 8, his brother was half his age. Now, Grant is 14. How old is his brother?", "10",
            "Kate’s mother has three children: Snap, Crackle and ___?", "Kate"
    );
}
