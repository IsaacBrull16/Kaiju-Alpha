package com.projecte.kaiju;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    /**
     * Privatizamos nuestro número de cartas para que no se le pueda cambiar
     */

    private static ArrayList<Card> deckCards = new ArrayList<Card>();
    private int actualCard;
    private Random randNum;
    private final int NUM_CARDS = deckCards.size();

    public Deck(){
        deckCards.add(Trotowild);
        deckCards.add(PlantBot);
        deckCards.add(ElectroRazz);
        actualCard = 0;
        randNum = new Random();
    }

    /**
     * Mezclará las cartas
     */

    public void Shuffle(){
        actualCard = 0;
        Collections.shuffle(deckCards);

        /*for (int first = 0; first < NUM_CARDS; first++){

            int second = randNum.nextInt(NUM_CARDS);

            Card temp = deckCards.get(first);
            deckCards.set(first, deckCards.get(second));
            deckCards.set(second, temp);
        }*/
    }

    /**
     * Seleccionará la primera carta del mazo
     * @return
     */

    public Card putCard() {
        if (actualCard < NUM_CARDS) {
            return deckCards.get(actualCard++);
        } else if (actualCard >= NUM_CARDS) {
            actualCard = 0;
            return deckCards.get(actualCard++);
        } else return null;
    }

    /**
     * Creamos nuestra baraja de cartas
     */

    Card Trotowild = new Card(0, "Trotowild", 1, 2, 5, "Nature");
    Card PlantBot = new Card(1,"PlantBot", 2, 6, 2, "Technology");
    Card ElectroRazz = new Card(2, "ElectroRazz", 1, 2, 4, "Science");

}
