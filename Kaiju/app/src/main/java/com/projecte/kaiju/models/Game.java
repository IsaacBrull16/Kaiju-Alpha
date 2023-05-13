package com.projecte.kaiju.models;

public class Game {

    /**
     * Creamos nuestros tablero y turno
     */

    private static Board board;
    private static Turn turn = new Turn();
    private static int diceValue1;
    private static int diceValue2;
    private static Card cardT1;
    private static Card cardT2;

    private static int life1;
    private static int life2;



    /**
     * Al iniciar el juego el turno será del jugador 1, se crearán dos jugadores, se les asignará
     * una vida a cada jugador y se barajaran sus mazos
     */

    public Game(){
        turn.currentTurn = true;
        board = new Board();
        diceValue1 = 0;
        diceValue2 = 0;
        cardT1 = new Card(0,"",1000,0,0,"");
        cardT2 = new Card(0,"",1000,0,0,"");
        life1 = board.getPlayer1().getLife();
        life2 = board.getPlayer2().getLife();
    }

    /**
     * Método getter del turno
     * @return
     */

    public Turn getTurn(){
        return this.turn;
    }

    /**
     * Método getter del tablero
     * @return
     */

    public Board getBoard(){
        return this.board;
    }

    public void dice1Actions(){
            board.getPlayer1().getPlayerDice().rollDice();
            board.changeDiceRolledP1();
    }

    public void dice2Actions(){
            board.getPlayer2().getPlayerDice().rollDice();
            board.changeDiceRolledP2();
    }

    public void deck1Actions(){
        //if ((turn.getTurnValue() == true) && (board.isCardOnTableP1() == false) && (board.getDiceRolledP1() == true)) {
            //if (board.getPlayer1().getDeckOfPlayer().deckSize() != 0) {
                Card cardtemp1 = board.getPlayer1().getDeckOfPlayer().putCard();
                cardT1 = cardtemp1;
                board.changeCardOnTableP1();
           //}
        //}
    }

    public void deck2Actions(){
        //if ((turn.getTurnValue() == false) && (board.isCardOnTableP2() == false) && (board.getDiceRolledP2() == true)) {
            //if (board.getPlayer2().getDeckOfPlayer().deckSize() != 0) {
                Card cardtemp2 = board.getPlayer2().getDeckOfPlayer().putCard();
                cardT2 = cardtemp2;
                board.changeCardOnTableP2();
            //}
        //}
    }

    public Card getCardT1(){
        return this.cardT1;
    }
    public void setCardT1(Card card){this.cardT1 = card;}
    public Card getCardT2(){
        return this.cardT2;
    }
    public void setCardT2(Card card){this.cardT2 = card;}

    public void card1Actions(){
                diceValue1 = board.getPlayer1().getPlayerDice().getAcumValue();
                diceValue1 = diceValue1 - cardT1.getCost();
                if (diceValue1 >= 0){
                    board.getPlayer1().getPlayerDice().setDiceValue(diceValue1);
                } else {
                    diceValue1 = 0;
                    board.getPlayer1().getPlayerDice().setDiceValue(diceValue1);
                }
                //board.changeCardOnTableP1();
    }

    public void card2Actions(){
                diceValue2 = board.getPlayer2().getPlayerDice().getAcumValue();
                diceValue2 = diceValue2 - cardT2.getCost();
                if (diceValue2 >= 0){
                    board.getPlayer2().getPlayerDice().setDiceValue(diceValue2);
                } else {
                    diceValue2 = 0;
                    board.getPlayer2().getPlayerDice().setDiceValue(diceValue2);
                }
                //board.changeCardOnTableP2();
    }

    public void endTurn1(){
        //if (turn.getTurnValue() == true) {
            board.changeDiceRolledP1();
            turn.changeTurn();
        //}
    }

    public void endTurn2(){
        //if (turn.getTurnValue() == false) {
            board.changeDiceRolledP2();
            turn.changeTurn();
        //}
    }
}

