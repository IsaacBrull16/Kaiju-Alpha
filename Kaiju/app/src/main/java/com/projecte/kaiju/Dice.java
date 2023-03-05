package com.projecte.kaiju;
import java.util.Random;
public class Dice {

    Random rand = new Random();

    private int dado = 6;

    int int_random = rand.nextInt(dado) + 1;


    public int getInt_random() {
        return int_random;
    }

}
