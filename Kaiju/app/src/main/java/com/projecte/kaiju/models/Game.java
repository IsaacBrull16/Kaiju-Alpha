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
        if ((turn.getTurnValue() == true) && (board.getDiceRolledP1() == false)) {
            board.getPlayer1().getPlayerDice().rollDice();
            diceValue1 = diceValue1 + (board.getPlayer1().getPlayerDice().getValue());
            board.changeDiceRolledP1();
        }
    }

    public void dice2Actions(){
        if ((turn.getTurnValue() == false) && (board.getDiceRolledP1() == false)) {
            board.getPlayer2().getPlayerDice().rollDice();
            diceValue2 = diceValue2 + (board.getPlayer2().getPlayerDice().getValue());
            board.changeDiceRolledP2();
        }
    }

    public void deck1Actions(){
        if ((turn.getTurnValue() == true) && (board.isCardOnTableP1() == false) && (board.getDiceRolledP1() == true)) {
            if (board.getPlayer1().getDeckOfPlayer().deckSize() != 0) {
                Card cardtemp1 = board.getPlayer1().getDeckOfPlayer().putCard();
                cardT1 = cardtemp1;
                board.changeCardOnTableP1();
            }
        }
    }

    public void deck2Actions(){
        if ((turn.getTurnValue() == false) && (board.isCardOnTableP2() == false) && (board.getDiceRolledP2() == true)) {
            if (board.getPlayer2().getDeckOfPlayer().deckSize() != 0) {
                Card cardtemp2 = board.getPlayer2().getDeckOfPlayer().putCard();
                cardT2 = cardtemp2;
                board.changeCardOnTableP2();
            }
        }
    }

    public void card1Actions(){
        if ((turn.getTurnValue() == true) && (board.isCardOnTableP1() == true)) {
            if (diceValue1 >= cardT1.getCost()) {
                diceValue1 = diceValue1 - cardT1.getCost();
                if (diceValue1 < 0) {
                    diceValue1 = 0;
                }
                life2 = life2 - cardT1.getDamage();
                board.getPlayer2().setLife(life2);
                board.changeCardOnTableP1();
            }
        }
    }

    public void card2Actions(){
        if ((turn.getTurnValue() == false) && (board.isCardOnTableP2() == true)) {
            if (diceValue2 >= cardT2.getCost()) {
                diceValue2 = diceValue2 - cardT2.getCost();
                if (diceValue2 < 0) {
                    diceValue2 = 0;
                }
                life1 = life1 - cardT2.getDamage();
                board.getPlayer1().setLife(life1);
                board.changeCardOnTableP2();
            }
        }
    }

    public void endTurn1(){
        if (turn.getTurnValue() == true) {
            board.changeDiceRolledP1();
            turn.changeTurn();
        }
    }

    public void endTurn2(){
        if (turn.getTurnValue() == false) {
            board.changeDiceRolledP2();
            turn.changeTurn();
        }
    }

    public Card cardOnTable1(){
        return cardT1;
    }

    public Card cardOnTable2(){
        return cardT2;
    }
}
