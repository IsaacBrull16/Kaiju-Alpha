package com.projecte.kaiju.models;


public class Player {
    private String name;
    private static Deck deckOfPlayer;
    private Dice playerDice;
    private int life;

    private String type;


    /**
     * Constructor de la clase Player, en el que cada jugador tendrá un nombre, una baraja, un dado
     * y su vida
     * @param name
     */
    public Player(String name){
        this.name = name;
        this.type = type;
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

    public String getType(){
        return this.type;
    }

    public void setLife(int l){
        this.life = l;
    }

    public void setName(String n){
        this.name = n;
    }

    public void setType(String t){
        this.type = t;
    }
}
