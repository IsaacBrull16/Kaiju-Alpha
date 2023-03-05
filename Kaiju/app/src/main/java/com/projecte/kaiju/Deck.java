package com.projecte.kaiju;

import java.util.Random;

public class Deck {

    private int CARDS = 2;
    Random rand = new Random();

    int resDeck = CARDS;

    int deckRandom = rand.nextInt(CARDS);

    public int getCard_random() {
        return deckRandom;
    }

    public Card Random_Card() {
        if (deckRandom == 1){
            return Trotowild;
        } else {
            return PlantBot;
        }
    }

    Card Trotowild = new Card(1, "Trotowild", 1, 2, 5, "Naturaleza");
    Card PlantBot = new Card(2,"PlantBot", 2, 6, 2, "Tecnología");

}
