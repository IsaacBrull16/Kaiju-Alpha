package com.projecte.kaiju.models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    /**
     * Privatizamos nuestro número de cartas para que no se le pueda cambiar
     */

    private static ArrayList<Card> deckCards = new ArrayList<Card>();
    private int actualCard;
    //private Random randNum;
    private int numCards;

    public Deck(){
        deckCards.add(Trotowild);
        deckCards.add(PlantBot);
        deckCards.add(ElectroRazz);
        deckCards.add(TechnoLight);
        deckCards.add(DuckWind);
        deckCards.add(PlasticKiller);
        numCards = deckCards.size();
        //randNum = new Random();
    }

    /**
     * Mezclará las cartas
     */

    public void Shuffle(){
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
        //while (deckCards.size() != 0) {
            Card card = deckCards.get(0);
            deckCards.remove(0);
            return card;
        //}
        //return new Card (0,"", 0, 0, 0, "");
    }

    public int deckSize(){
        return deckCards.size();
    }

    /**
     * Creamos nuestra baraja de cartas
     */

    Card Trotowild = new Card(0, "Trotowild", 1, 2, 5, "Nature");
    Card PlantBot = new Card(1,"PlantBot", 2, 6, 2, "Technology");
    Card ElectroRazz = new Card(2, "ElectroRazz", 3, 2, 4, "Science");
    Card TechnoLight = new Card(3, "TechnoLight", 5, 10, 2, "Technology");
    Card DuckWind = new Card(4, "DuckWind", 4, 2, 9, "Nature");
    Card PlasticKiller = new Card(5, "PlasticKiller", 6, 11, 3, "Science");

    /*public Card sacarCarta() {
        Card carta = deckCards.get(0);
        deckCards.remove(0);
        return carta;
    }*/
}
