package com.projecte.kaiju.models;

public class Turn {
    boolean currentTurn = true;

    /**
     * El objeto turno siempre será true al principio
     */

    public Turn(){
        this.currentTurn = true;
    }

    /**
     * Función que nos permite cambiar el estado del turno
     */

    public void changeTurn(){
        if (currentTurn == true){
            this.currentTurn = false;
        } else {
            this.currentTurn = true;
        }
    }

    /**
     * Método getter del estado del turno
     * @return
     */

    public boolean getTurnValue(){
        return currentTurn;
    }
}
