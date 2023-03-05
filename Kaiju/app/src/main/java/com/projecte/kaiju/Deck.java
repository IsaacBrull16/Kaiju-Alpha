package com.projecte.kaiju;

import java.util.Random;

public class Deck {

    private int CARDS = 3;
    Random rand = new Random();

    int resDeck = CARDS;

    int deckRandom = rand.nextInt(CARDS);

    public int getCard_random() {
        return deckRandom;
    }

    public Card Random_Card() {
        if (deckRandom == Trotowild.getId()){
            return Trotowild;
        } else if (deckRandom == PlantBot.getId()){
            return PlantBot;
        } else {
            return ElectroRazz;
        }
    }

    Card Trotowild = new Card(0, "Trotowild", 1, 2, 5, "Naturaleza");
    Card PlantBot = new Card(1,"PlantBot", 2, 6, 2, "Tecnología");
    Card ElectroRazz = new Card(2, "ElectroRazz", 1, 2, 4, "Science");
}
