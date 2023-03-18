package com.projecte.kaiju.models;

public class Game {

    /**
     * Creamos nuestros 2 jugadores, turno, y unos contadores
     */

    public static Player player1;
    public static Player player2;
    private static Turn turn = new Turn();
    boolean cardTableP1 = false;
    boolean cardTableP2 = false;
    boolean diceRolledP1 = false;
    boolean diceRolledP2 = false;

    /**
     * Al iniciar el juego el turno será del jugador 1, se crearán dos jugadores, se les asignará
     * una vida a cada jugador y se barajaran sus mazos
     */

    public Game(){
        turn.currentTurn = true;
        player1 = new Player("J1");
        player2 = new Player("J2");
        player1.setLife(25);
        player2.setLife(25);
        player1.getDeckOfPlayer().Shuffle();
        player2.getDeckOfPlayer().Shuffle();
    }

    /**
     * Método getter de jugador 1
     * @return
     */

    public Player getPlayer1(){
        return this.player1;
    }

    /**
     * Método getter de jugador 2
     * @return
     */

    public Player getPlayer2(){
        return this.player2;
    }

    /**
     * Método getter del turno
     * @return
     */

    public Turn getTurn(){
        return this.turn;
    }

    /**
     * Método que nos permitirá decidir si hay una carta o no en el tablero del jugador 1
     */

    public void changeCardOnTableP1(){
        if (cardTableP1 == true){
            this.cardTableP1 = false;
        } else {
            this.cardTableP1 = true;
        }
    }

    /**
     * Método que nos retornará el estado de la carta en el tablero del jugador 1
     * @return
     */

    public boolean isCardOnTableP1(){
        return this.cardTableP1;
    }

    /**
     * Método que nos permitirá decidir si hay una carta o no en el tablero del jugador 2
     */

    public void changeCardOnTableP2(){
        if (cardTableP2 == true){
            this.cardTableP2 = false;
        } else {
            this.cardTableP2 = true;
        }
    }

    /**
     * Método que nos retornará el estado de la carta en el tablero del jugador 2
     * @return
     */

    public boolean isCardOnTableP2(){
        return this.cardTableP2;
    }

    /**
     * Método que nos retornará el estado del dado en el tablero del jugador 1
     * @return
     */

    public boolean getDiceRolledP1(){
        return this.diceRolledP1;
    }

    /**
     * Método que nos permitirá decidir si hay un dado o no en el tablero del jugador 1
     */

    public void changeDiceRolledP1(){
        if (diceRolledP1 == true){
            this.diceRolledP1 = false;
        } else {
            this.diceRolledP1 = true;
        }
    }

    /**
     * Método que nos retornará el estado del dado en el tablero del jugador 2
     * @return
     */

    public boolean getDiceRolledP2(){
        return this.diceRolledP2;
    }

    /**
     * Método que nos permitirá decidir si hay un dado o no en el tablero del jugador 2
     */

    public void changeDiceRolledP2(){
        if (diceRolledP2 == true){
            this.diceRolledP2 = false;
        } else {
            this.diceRolledP2 = true;
        }
    }
}

