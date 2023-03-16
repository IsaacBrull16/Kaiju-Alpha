package com.projecte.kaiju.models;

import java.util.Random;

public class Dice {
    Random rand = new Random();
    private int value = 0;

    public Dice(){
        value = 0;
    }
    public int getValue(){
        return value;
    }
    public void rollDice(){
        value = rand.nextInt(6) + 1;
    }
}
