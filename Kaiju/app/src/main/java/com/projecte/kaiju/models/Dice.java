package com.projecte.kaiju.models;

import java.util.Random;

public class Dice {
    Random rand = new Random();
    private int value = 0;

    /**
     * El objeto dado tendrá un 0 de valor inicial
     */

    public Dice(){
        value = 0;
    }

    /**
     * Método getter que nos devolverá el valor del dado
     * @return
     */

    public int getValue(){
        return value;
    }

    /**
     * Función que nos girará el dado
     */

    public void rollDice(){
        value = rand.nextInt(6) + 1;
    }
}
