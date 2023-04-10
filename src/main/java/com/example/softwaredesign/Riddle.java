package com.example.softwaredesign;

public class Riddle extends MiniGame {
    public Riddle(String miniGameName){
        super(miniGameName);
    }
    private final String[] questions = {
            "If there are three apples and you take away two, how many apples do you have?",
            "What's full of holes but still holds water?",
            "How many months of the year have 28 days?",
            "When Grant was 8, his brother was half his age. Now, Grant is 14. How old is his brother?",
            "Kate’s mother has three children: Snap, Crackle and ___?"
    };

    private final String[] answers = {
            "2",
            "Sponge",
            "12",
            "10",
            "Kate"
    };

    public String[] getAnswers() {
        return answers;
    }

    public String[] getQuestions() {
        return questions;
    }
}
