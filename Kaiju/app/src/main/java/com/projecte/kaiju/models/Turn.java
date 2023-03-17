package com.projecte.kaiju.models;

public class Turn {
    boolean currentTurn = true;

    public Turn(){
        this.currentTurn = true;
    }

    /*public void turnPlayer(){
        if (player == true){
            player = false;
        } else {
            //turnPlayer2();
        }
    }*/

    public void changeTurn(){
        if (currentTurn == true){
            this.currentTurn = false;
        } else {
            this.currentTurn = true;
        }
    }

    public boolean getTurnValue(){
        return currentTurn;
    }
}
