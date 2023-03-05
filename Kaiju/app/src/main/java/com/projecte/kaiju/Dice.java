package com.projecte.kaiju;
import java.util.Random;
public class Dice {

    Random rand = new Random();

    private int dado = 7;

    int int_random = rand.nextInt(dado);


    public int getInt_random() {
        return int_random;
    }

}
