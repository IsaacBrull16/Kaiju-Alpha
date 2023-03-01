package com.projecte.kaiju;

public class Card {
    String name;
    int cost;
    int damage;
    int life;

    int type;

    /**
     * Constructor de la clase Card
     * @param name
     * @param cost
     * @param damage
     * @param life
     * @param type
     */
    public Card(String name, int cost, int damage, int life, int type){
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
    public int getType(){
        return type;
    }

    public void setCost(int cost){
        this.cost = cost;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }
    public void setLife(int life){
        this.life = life;
    }
    public void setType(int type){
        this.type = type;
    }
    public void setName(String name){
        this.name = name;
    }
}
