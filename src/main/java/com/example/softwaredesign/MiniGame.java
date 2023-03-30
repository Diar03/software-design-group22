package com.example.softwaredesign;

public abstract class MiniGame {
    private int earnedMoney = 0;

    public int getEarnedMoney() {
        return earnedMoney;
    }

    public void increaseEarnedMoney() {
        this.earnedMoney += 10;
    }

    protected MiniGame(String miniGameName){}
}

