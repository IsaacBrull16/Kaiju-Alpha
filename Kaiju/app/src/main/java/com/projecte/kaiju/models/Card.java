package com.projecte.kaiju.models;

public class Card {
    int id;
    String name;
    int cost;
    int damage;
    int life;

    String type;

    /**
     * Constructor de la clase Card
     * @param id
     * @param name
     * @param cost
     * @param damage
     * @param life
     * @param type
     */
    public Card(int id, String name, int cost, int damage, int life, String type){
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.life = life;
        this.type = type;
    }

    /**
     * Getters y setters de la clase Card
     * @return
     */

    public int getId(){ return id; }
    public String getName(){
        return name;
    }
    public int getCost(){
        return cost;
    }
    public int getDamage(){
        return damage;
    }
    public int getLife(){
        return life;
    }
    public String getType(){
        return type;
    }

    public void setId(int id){this.id = id;}
    public void setCost(int cost){
        this.cost = cost;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }
    public void setLife(int life){
        this.life = life;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setName(String name){
        this.name = name;
    }
}

