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
    boolean cardTableP1 = false;
    boolean cardTableP2 = false;
    boolean diceRolledP1 = false;
    boolean diceRolledP2 = false;
    public Game(){
        turn.currentTurn = true;
        player1 = new Player("J1");
        player2 = new Player("J2");
        player1.setLife(15);
        player2.setLife(15);
        player1.getDeckOfPlayer().Shuffle();
        player2.getDeckOfPlayer().Shuffle();
    }

    public Player getPlayer1(){
        return this.player1;
    }

    public Player getPlayer2(){
        return this.player2;
    }

    public Turn getTurn(){
        return this.turn;
    }

    public void changeCardOnTableP1(){
        if (cardTableP1 == true){
            this.cardTableP1 = false;
        } else {
            this.cardTableP1 = true;
        }
    }

    public boolean isCardOnTableP1(){
        return this.cardTableP1;
    }

    public void changeCardOnTableP2(){
        if (cardTableP2 == true){
            this.cardTableP2 = false;
        } else {
            this.cardTableP2 = true;
        }
    }

    public boolean isCardOnTableP2(){
        return this.cardTableP2;
    }

    public boolean getDiceRolledP1(){
        return this.diceRolledP1;
    }

    public void changeDiceRolledP1(){
        if (diceRolledP1 == true){
            this.diceRolledP1 = false;
        } else {
            this.diceRolledP1 = true;
        }
    }
    public boolean getDiceRolledP2(){
        return this.diceRolledP2;
    }

    public void changeDiceRolledP2(){
        if (diceRolledP2 == true){
            this.diceRolledP2 = false;
        } else {
            this.diceRolledP2 = true;
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

