package com.projecte.kaiju.models;

import android.widget.Button;

public class Game {
    /*private ArrayList<Player> players;
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
    public static Player player1;
    public static Player player2;
    private static Turn turn = new Turn();
    public Game(){
        turn.currentTurn = true;
        player1 = new Player("J1");
        player2 = new Player("J2");
        player1.setLife(25);
        player2.setLife(25);
        player1.getDeckOfPlayer().Shuffle();
        player2.getDeckOfPlayer().Shuffle();
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public Turn getTurn(){
        return turn;
    }

    public boolean cardOnTable(boolean cardTable){
        if (cardTable == true){
            return true;
        } else {
            return false;
        }

    }
    /*public Game(ArrayList<Player> players){
        this.players = players;
        this.deck = new Deck();
        this.table = new ArrayList<>();
        this.actualTurn = 1;
        this.actualPlayer = 0;
        repartirCartes();
    }*/

    /*public void repartirCartes(){
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                player.addCard(deck.sacarCarta());
            }
        }
    }*/

    /**
     * Getters y setters de la clase Game
     *
     * @return
     */

    /*public void siguienteTurno(){
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
    }*/
}

