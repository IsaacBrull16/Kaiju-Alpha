package com.projecte.kaiju.models;

import android.util.Log;

public class Game {

    /**
     * Creamos nuestros tablero y turno
     */

    private static Board board;
    private static Turn turn = new Turn();
    private static int diceValue1;
    private static int diceValue2;
    private static Card cardT11;
    private static Card cardT12;
    private static Card cardT13;
    private static Card cardT21;
    private static Card cardT22;
    private static Card cardT23;

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
        cardT11 = new Card(0,"",1000,0,0,"");
        cardT12 = new Card(0,"",1000,0,0,"");
        cardT13 = new Card(0,"",1000,0,0,"");
        cardT21 = new Card(0,"",1000,0,0,"");
        cardT22 = new Card(0,"",1000,0,0,"");
        cardT23 = new Card(0,"",1000,0,0,"");
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
        Card cardtemp1 = board.getPlayer1().getDeckOfPlayer().putCard();
        Log.d("NewCard1: ", cardtemp1.getName());
        for (int i = 0; i < board.getPlayer1().getDeckOfPlayer().deckSize(); i++) {
            Log.d("AllDeck1: ", board.getPlayer1().getDeckOfPlayer().getDeckCards().get(i).getName());
        }
        if (!cardT11.isOnTable()){
            setCardT11(cardtemp1);
            //cardT11 = cardtemp1;
            board.changeCardOnTableP11();
            cardT11.setOnTable(true);
            Log.d("NameC11: ", cardT11.getName());
        } else if (!cardT12.isOnTable()) {
            setCardT12(cardtemp1);
            //cardT12 = cardtemp1;
            board.changeCardOnTableP12();
            cardT12.setOnTable(true);
            Log.d("NameC12: ", cardT12.getName());
        } else if (!cardT13.isOnTable()) {
            setCardT13(cardtemp1);
            //cardT13 = cardtemp1;
            board.changeCardOnTableP13();
            cardT13.setOnTable(true);
            Log.d("NameC13: ", cardT13.getName());
        }
    }

    public void deck2Actions(){
        Card cardtemp2 = board.getPlayer2().getDeckOfPlayer().putCard();
        Log.d("NewCard2: ", cardtemp2.getName());
        for (int i = 0; i < board.getPlayer2().getDeckOfPlayer().deckSize(); i++) {
            Log.d("AllDeck2: ", board.getPlayer2().getDeckOfPlayer().getDeckCards().get(i).getName());
        }
        if (!cardT21.isOnTable()){
            setCardT21(cardtemp2);
            //cardT21 = cardtemp2;
            board.changeCardOnTableP21();
            cardT21.setOnTable(true);
            Log.d("NameC21: ", cardT21.getName());
        } else if (!cardT22.isOnTable()) {
            setCardT22(cardtemp2);
            //cardT22 = cardtemp2;
            board.changeCardOnTableP22();
            cardT22.setOnTable(true);
            Log.d("NameC22: ", cardT22.getName());
        } else if (!cardT23.isOnTable()) {
            setCardT23(cardtemp2);
            //cardT23 = cardtemp2;
            board.changeCardOnTableP23();
            cardT23.setOnTable(true);
            Log.d("NameC23: ", cardT23.getName());
        }
    }

    public Card getCardT11(){
        return this.cardT11;
    }
    public void setCardT11(Card card){this.cardT11 = card;}
    public Card getCardT12(){
        return this.cardT12;
    }
    public void setCardT12(Card card){this.cardT12 = card;}
    public Card getCardT13(){
        return this.cardT13;
    }
    public void setCardT13(Card card){this.cardT13 = card;}
    public Card getCardT21(){
        return this.cardT21;
    }
    public void setCardT21(Card card){this.cardT21 = card;}
    public Card getCardT22(){
        return this.cardT22;
    }
    public void setCardT22(Card card){this.cardT22 = card;}
    public Card getCardT23(){
        return this.cardT23;
    }
    public void setCardT23(Card card){this.cardT23 = card;}

    public void card1Actions(){
        diceValue1 = board.getPlayer1().getPlayerDice().getAcumValue();
        if (diceValue1 >= 0){
            board.getPlayer1().getPlayerDice().setDiceValue(diceValue1);
        } else {
            diceValue1 = 0;
            board.getPlayer1().getPlayerDice().setDiceValue(diceValue1);
        }
    }

    public void card2Actions(){
        diceValue2 = board.getPlayer2().getPlayerDice().getAcumValue();
        if (diceValue2 >= 0){
            board.getPlayer2().getPlayerDice().setDiceValue(diceValue2);
        } else {
            diceValue2 = 0;
            board.getPlayer2().getPlayerDice().setDiceValue(diceValue2);
        }
    }

    public void endTurn1(){
        board.changeDiceRolledP1();
        turn.changeTurn();
    }

    public void endTurn2(){
        board.changeDiceRolledP2();
        turn.changeTurn();
    }
}

