package com.projecte.kaiju.models;

import java.util.Random;

public class Dice {
    private int acumValue;
    private int originalValue;

    /**
     * El objeto dado tendrá un 0 de valor inicial
     */

    public Dice(){
        this.acumValue = 0;
        this.originalValue = 0;
    }

    /**
     * Método getter que nos devolverá el valor del dado
     * @return
     */

    public int getAcumValue(){
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
        this.originalValue = value;
        this.acumValue += value;
    }

    public int getOriginalValue() {
        return this.originalValue;
    }
}
