package com.example.softwaredesign;

public abstract class MiniGame {
    private String name;
    private int earnedMoney = 0;

    public int getEarnedMoney() {
        return earnedMoney;
    }

    public void increaseEarnedMoney() {
        this.earnedMoney += 10;
    }

    public MiniGame(String miniGameName){
        this.name = miniGameName;
    }
}

