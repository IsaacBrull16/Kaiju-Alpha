package com.projecte.kaiju.models;

public class Player {
    private String name;
    private Deck deckOfPlayer;
    private Dice playerDice;
    private int life;
    //private int pa;

    /**
     * Constructor de la clase Player
     * @param name
     *
     */
    public Player(String name){
        this.name = name;
        this.deckOfPlayer = new Deck();
        this.playerDice = new Dice();
        this.life = life;
        //this.pa = 0;

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

    /*public int getPa(){
        return pa;
    }*/

    public int getLife(){
        return life;
    }

    public void setLife(int l){
        this.life = l;
    }

    public void setName(String n){
        this.name = n;
    }


    /*public void setPa(int pa){
        this.pa = pa;
    }*/

    /*public void addCard(Card card){
        deckOfPlayer.add(card);
    }
    public void removeCard(Card card){
        deckOfPlayer.remove(card);
    }
    /*public Card JugarCarta(int id){
        Card card = deckOfPlayer.get(id);
        deckOfPlayer.remove(id);
        return card;
    }*/



}
