package com.projecte.kaiju.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projecte.kaiju.R;
import com.projecte.kaiju.models.Card;
import com.projecte.kaiju.models.Deck;
import com.projecte.kaiju.models.Dice;
import com.projecte.kaiju.models.Turn;
import com.projecte.kaiju.models.Game;

public class Partida extends AppCompatActivity {
    //private Game game;
    /**
     * Declaramos todos los objetos del layout que querramos modificar/usar
     */

    public static int diceValue = 0;

    Button dado1;
    Button dado2;
    Button homeButton;
    Button deckButton1;
    Button deckButton2;
    Button card1;
    Button card2;
    Button endTurn1;
    Button endTurn2;

    TextView valorDado1;
    TextView valorDado2;
    TextView turnIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        dado1 = (Button) findViewById(R.id.dado1);
        dado2 = (Button) findViewById(R.id.dado2);
        homeButton = (Button) findViewById(R.id.homeButton);
        deckButton1 = (Button) findViewById(R.id.deckButton1);
        deckButton2 = (Button) findViewById(R.id.deckButton2);
        endTurn1 = (Button) findViewById(R.id.endTurn1);
        endTurn2 = (Button) findViewById(R.id.endTurn2);
        valorDado1 = (TextView) findViewById(R.id.valorDado1);
        valorDado2 = (TextView) findViewById(R.id.valorDado2);

        turnIndicator = (TextView) findViewById(R.id.turnIndicator);
        card1 = (Button) findViewById(R.id.card1);
        card2 = (Button) findViewById(R.id.card2);


        //ArrayList<Player> jugadores = new ArrayList<>();

        /**
         * Encontramos cada uno de los TextViews del layout
         */

        Game game = new Game();

        Turn actualTurn = game.getTurn();
        Dice diceP1 = game.getPlayer1().getPlayerDice();
        Dice diceP2 = game.getPlayer2().getPlayerDice();
        Deck deckP1 = game.getPlayer1().getDeckOfPlayer();
        Deck deckP2 = game.getPlayer2().getDeckOfPlayer();

        /**
         * Hacemos un dado que sea tirable
         */

        dado1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((actualTurn.getTurnValue() == true) && (game.getDiceRolledP1() == false)) {
                    diceP1.rollDice();
                    int diceValue1 = diceP1.getValue();
                    valorDado1.setText(String.valueOf(diceValue1));
                    game.changeDiceRolledP1();
                }
            }
        });
        dado2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((actualTurn.getTurnValue() == false) && (game.getDiceRolledP2() == false)) {
                    diceP2.rollDice();
                    int diceValue2 = diceP2.getValue();
                    valorDado2.setText(String.valueOf(diceValue2));
                    game.changeDiceRolledP2();
                }
            }
        });

        /**
         * Hacemos un botón para ir a la MainActivity(Página Principal)
         */

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Partida.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        /**
         * Ahora haremos un botón que emulará una baraja de cartas, y colocará las cartas primero
         * en el primer hueco, después en el segundo y acabará con el tercero, y no repetirá ninguna
         * carta
         */

        /**
         * Primero creamos una clase Deck(Baraja), creamos un contador de las cartas
         * restantes (resDeck) y escogemos una carta aleatoria de entre las disponibles
         */

        deckButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if ((actualTurn.getTurnValue() == true) && (game.isCardOnTableP1() == false) && (game.getDiceRolledP1()) == true) {
                    if (deckP1.deckSize() != 0) {
                        Card cardr1 = deckP1.putCard();
                        card1.setText("Name: " + cardr1.getName() + "\n\nCost: " + cardr1.getCost() + "\n\nDamage: " + cardr1.getDamage());
                        game.changeCardOnTableP1();
                    }
                }
            }
        });
        deckButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if ((actualTurn.getTurnValue() == false) && (game.isCardOnTableP2() == false) && (game.getDiceRolledP2()) == true) {
                    if (deckP2.deckSize() != 0) {
                        Card cardr2 = deckP2.putCard();
                        card2.setText("Name: " + cardr2.getName() + "\n\nCost: " + cardr2.getCost() + "\n\nDamage: " + cardr2.getDamage());
                        game.changeCardOnTableP2();
                    }
                }
            }
        });

        card1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        card2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        endTurn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (actualTurn.getTurnValue() == true) {
                    game.changeDiceRolledP1();
                    actualTurn.changeTurn();
                }
            }
        });
        endTurn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (actualTurn.getTurnValue() == false) {
                    game.changeDiceRolledP2();
                    actualTurn.changeTurn();
                }
            }
        });
    }
}