package com.projecte.kaiju;

import java.util.ArrayList;

public class Player {
    String name;
    int life;
    int pa;

    ArrayList<Card> deck = new ArrayList<Card>();

    /**
     * Constructor de la clase Player
     * @param name
     * @param life
     * @param pa
     */
    public Player(String name, int life, int pa){
        this.name = name;
        this.life = life;
        this.pa = pa;
    }

    /**
     * Getters y setters de la clase Player
     * @return
     */
    public String getName(){
        return name;
    }
    public int getLife(){
        return life;
    }
    public int getPa(){
        return pa;
    }

    public void setLife(int life){
        this.life = life;
    }

    public void setPa(int pa){
        this.pa = pa;
    }
    public void setName(String name){
        this.name = name;
    }

}
