package com.projecte.kaiju;

public class Game {
    Player player1;
    Player player2;
    int turn = 0;
    int round = 0;

    /**
     * Constructor de la clase Game
     *
     * @param player1
     * @param player2
     */
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Getters y setters de la clase Game
     *
     * @return
     */
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getTurn() {
        return turn;
    }

    public int getRound() {
        return round;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Funcion que devuelve el jugador que tiene el turno
     *
     * @return
     */
    public Player getTurnPlayer() {
        if (turn == 0) {
            return player1;
        } else {
            return player2;
        }
    }

    /**
     * Funcion que devuelve el jugador que no tiene el turno
     *
     * @return
     */
    public Player getNotTurnPlayer() {
        if (turn == 0) {
            return player2;
        } else {
            return player1;
        }
    }

    /**
     * Funcion que cambia el turno
     */
    public void changeTurn() {
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
    }

    /**
     * Funcion que cambia el turno
     */
    public void changeRound() {
        round++;
    }

    /**
     * Funcion que devuelve el jugador que tiene el turno
     *
     * @return
     */
    public Player getRoundPlayer() {
        if (round % 2 == 0) {
            return player1;
        } else {
            return player2;
        }
    }

    /**
     * Funcion que devuelve el jugador que no tiene el turno
     *
     * @return
     */
    public Player getNotRoundPlayer() {
        if (round % 2 == 0) {
            return player2;
        }
        return null;
    }
}

