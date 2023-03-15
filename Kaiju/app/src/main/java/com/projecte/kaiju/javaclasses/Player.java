package com.projecte.kaiju.javaclasses;

import com.projecte.kaiju.javaclasses.Card;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> deckOfPlayer;
    private int life;
    private int pa;


    /**
     * Constructor de la clase Player
     * @param name
     *
     */
    public Player(String name){
        this.name = name;
        this.deckOfPlayer = new ArrayList<>();
        this.pa = 0;

    }

    /**
     * Getters y setters de la clase Player
     * @return
     */
    public String getName(){
        return name;
    }
    public ArrayList<Card> getDeck(){
        return deckOfPlayer;
    }

    public int getPa(){
        return pa;
    }




    public void setPa(int pa){
        this.pa = pa;
    }

    public void addCard(Card card){
        deckOfPlayer.add(card);
    }
    public void removeCard(Card card){
        deckOfPlayer.remove(card);
    }
    public Card JugarCarta(int id){
        Card card = deckOfPlayer.get(id);
        deckOfPlayer.remove(id);
        return card;
    }



}
