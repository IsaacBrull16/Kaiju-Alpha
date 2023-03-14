package com.projecte.kaiju.javaclasses;

import com.projecte.kaiju.javaclasses.Card;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> deck;
    private int life;
    private int pa;


    /**
     * Constructor de la clase Player
     * @param name
     *
     */
    public Player(String name){
        this.name = name;
        this.deck = new ArrayList<>();
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
        return deck;
    }

    public int getPa(){
        return pa;
    }




    public void setPa(int pa){
        this.pa = pa;
    }

    public void addCard(Card card){
        deck.add(card);
    }
    public void removeCard(Card card){
        deck.remove(card);
    }
    public Card JugarCarta(int id){
        Card card = deck.get(id);
        deck.remove(id);
        return card;
    }



}
