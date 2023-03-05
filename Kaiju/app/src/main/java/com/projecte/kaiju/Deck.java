package com.projecte.kaiju;

import java.util.Random;

public class Deck {

    /**
     * Privatizamos nuestro número de cartas para que no se le pueda cambiar
     */

    private int CARDS = 3;

    /**
     * Generamos un número aleatorio
     */

    Random rand = new Random();

    int resDeck = CARDS;

    int deckRandom = rand.nextInt(CARDS);

    public int getCard_random() {
        return deckRandom;
    }

    /**
     * Comparamos el número aleatorio (deckRandom) con el id de cada carta,
     * y retornaremos la carta correspondiente al id
     * @return
     */

    public Card Random_Card() {
        if (deckRandom == Trotowild.getId()){
            return Trotowild;
        } else if (deckRandom == PlantBot.getId()){
            return PlantBot;
        } else {
            return ElectroRazz;
        }
    }

    /**
     * Creamos nuestra baraja de cartas
     */

    Card Trotowild = new Card(0, "Trotowild", 1, 2, 5, "Naturaleza");
    Card PlantBot = new Card(1,"PlantBot", 2, 6, 2, "Tecnología");
    Card ElectroRazz = new Card(2, "ElectroRazz", 1, 2, 4, "Science");
}
