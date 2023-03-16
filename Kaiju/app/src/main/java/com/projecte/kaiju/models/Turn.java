package com.projecte.kaiju.models;

public class Turn {
    boolean player = true;

    public Turn(){
        player = true;
    }

    /*public void turnPlayer(){
        if (player == true){
            player = false;
        } else {
            //turnPlayer2();
        }
    }*/

    public void changeTurn(){
        if (player == true){
            player = false;
        } else {
            player = true;
        }
    }
}
