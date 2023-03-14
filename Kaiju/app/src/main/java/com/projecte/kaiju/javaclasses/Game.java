package com.projecte.kaiju.javaclasses;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private ArrayList<Card> table;
    private int actualTurn;
    private int actualPlayer;

    /**
     * Constructor de la clase Game
     *
     * @param players
     *
     */
    public Game(ArrayList<Player> players){
        this.players = players;
        this.deck = new Deck();
        this.table = new ArrayList<>();
        this.actualTurn = 1;
        this.actualPlayer = 0;
        repartiCartes();
    }

    public void repartiCartes(){
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                player.addCard(deck.sacarCarta());
            }
        }
    }

    /**
     * Getters y setters de la clase Game
     *
     * @return
     */

    public void siguienteTurno(){
        actualPlayer = (actualPlayer + 1) % players.size();
    }

    public void jugarCarta(int id){
        Player actualPlayer = players.get(this.actualPlayer);
        Card card = actualPlayer.JugarCarta(id);
        table.add(card);
        siguienteTurno();
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public Deck getDeck(){
        return deck;
    }
    public ArrayList<Card> getTable(){
        return table;
    }
    public int getActualTurn(){
        return actualTurn;
    }
    public int getActualPlayer(){
        return actualPlayer;
    }
}

