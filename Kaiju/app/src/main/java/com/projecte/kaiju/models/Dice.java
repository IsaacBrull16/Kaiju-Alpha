package com.projecte.kaiju.models;

import java.util.Random;

public class Dice {
    private int acumValue = 0;

    /**
     * El objeto dado tendrá un 0 de valor inicial
     */

    public Dice(){
        this.acumValue = 0;
    }

    /**
     * Método getter que nos devolverá el valor del dado
     * @return
     */

    public int getValue(){
        return this.acumValue;
    }

    /**
     * Método setter del valor del dado
     * @param value
     */

    public void setDiceValue(int value){
        this.acumValue = value;
    }

    /**
     * Función que nos girará el dado
     */

    public void rollDice(){
        Random rand = new Random();
        int value = rand.nextInt(6) + 1;
        this.acumValue += value;
    }
}
