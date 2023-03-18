package com.projecte.kaiju.models;

public class Player {
    private String name;
    private Deck deckOfPlayer;
    private Dice playerDice;
    private int life;

    /**
     * Constructor de la clase Player, en el que cada jugador tendrá un nombre, una baraja, un dado
     * y su vida
     * @param name
     */
    public Player(String name){
        this.name = name;
        this.deckOfPlayer = new Deck();
        this.playerDice = new Dice();
        this.life = life;
    }

    /**
     * Getters y setters de la clase Player
     * @return
     */
    public String getName(){
        return this.name;
    }
    public Deck getDeckOfPlayer(){
        return this.deckOfPlayer;
    }

    public Dice getPlayerDice(){
        return this.playerDice;
    }

    public int getLife(){
        return life;
    }

    public void setLife(int l){
        this.life = l;
    }

    public void setName(String n){
        this.name = n;
    }
}
