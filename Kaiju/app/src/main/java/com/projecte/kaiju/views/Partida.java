package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projecte.kaiju.R;
import com.projecte.kaiju.models.Game;

public class Partida extends AppCompatActivity {

    /**
     * Declaramos todos los objetos del layout que querramos modificar/usar
     */

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
    TextView lifeP1;
    TextView lifeP2;

    public static int life1;
    public static int life2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        /**
         * Encontramos las id de cada objeto del layout que querramos usar
         */

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
        lifeP1 = (TextView) findViewById(R.id.lifeP1);
        lifeP2 = (TextView) findViewById(R.id.lifeP2);

        /**
         * Iniciamos el juego y declaramos unas variables que vienen del juego para acortar
         */

        Game game = new Game();

        /**
         * Ponemos color gris a las cartas cuando no se pueden usar y ponemos información en los textos
         */

        card1.setBackgroundColor(Color.GRAY);
        card2.setBackgroundColor(Color.GRAY);
        lifeP1.setText(String.valueOf(life1));
        lifeP2.setText(String.valueOf(life2));
        turnIndicator.setText("Turn of " + game.getBoard().getPlayer1().getName());

        /**
         * Al dar al botón de dado, se lanzará, irá guardando el valor si no se va usando,
         * pondrá el valor por texto y te indicará si la carta la puedes usar
         */

        dado1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if ((actualTurn.getTurnValue() == true) && (game.getBoard().getDiceRolledP1() == false)) {
                    diceP1.rollDice();
                    diceValue1 = diceValue1 + (diceP1.getValue());
                    valorDado1.setText(String.valueOf(diceValue1));
                    if ((diceValue1 >= cardT1.getCost()) && (game.getBoard().isCardOnTableP1() == true)){
                        card1.setBackgroundColor(Color.parseColor("#3F2893"));
                    }
                    game.getBoard().changeDiceRolledP1();
                }*/
                game.dice1Actions();
            }
        });

        /**
         * Mismas condiciones del dado del jugador 1 se aplican para jugador 2
         */

        dado2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if ((actualTurn.getTurnValue() == false) && (game.getBoard().getDiceRolledP2() == false)) {
                    diceP2.rollDice();
                    diceValue2 = diceValue2 + diceP2.getValue();
                    valorDado2.setText(String.valueOf(diceValue2));
                    if ((diceValue2 >= cardT2.getCost()) && (game.getBoard().isCardOnTableP2() == true)){
                        card2.setBackgroundColor(Color.parseColor("#3F2893"));
                    }
                    game.getBoard().changeDiceRolledP2();
                }*/
                game.dice2Actions();
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
         * La baraja del jugador 1 al ser pulsada sacará una carta que se mostrará en el tablero y
         * será de color gris si no la puedes usar, y morada si es que sí
         */

        deckButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*if ((actualTurn.getTurnValue() == true) && (game.getBoard().isCardOnTableP1() == false) && (game.getBoard().getDiceRolledP1() == true)) {
                    if (deckP1.deckSize() != 0) {
                        Card cardtemp1 = deckP1.putCard();
                        card1.setText("Name: " + cardtemp1.getName() + "\n\nCost: " + cardtemp1.getCost() + "\n\nDamage: " + cardtemp1.getDamage());
                        cardT1 = cardtemp1;
                        game.getBoard().changeCardOnTableP1();
                    }
                }
                if (diceValue1 >= cardT1.getCost()) {
                    card1.setBackgroundColor(Color.parseColor("#3F2893"));
                }*/
                game.deck1Actions();
            }
        });

        /**
         * Mismas condiciones de baraja de jugador 1 se aplican para jugador 2
         */

        deckButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*if ((actualTurn.getTurnValue() == false) && (game.getBoard().isCardOnTableP2() == false) && (game.getBoard().getDiceRolledP2() == true)) {
                    if (deckP2.deckSize() != 0) {
                        Card cardtemp2 = deckP2.putCard();
                        card2.setText("Name: " + cardtemp2.getName() + "\n\nCost: " + cardtemp2.getCost() + "\n\nDamage: " + cardtemp2.getDamage());
                        cardT2 = cardtemp2;
                        game.getBoard().changeCardOnTableP2();
                    }
                }
                if (diceValue2 >= cardT2.getCost()) {
                    card2.setBackgroundColor(Color.parseColor("#3F2893"));
                }*/
                game.deck2Actions();
            }
        });

        /**
         * La carta solo se podrá usar si su valor es menor o igual al del dado.
         * Al ser pulsada la carta desaparecerá del tablero y restará vida al jugador contrario,
         * el valor del dado se reducirá por el coste de la carta y si la vida del jugador es igual
         * o inferior a 0, se irá a la pantalla del jugador ganador
         */

        card1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*if ((actualTurn.getTurnValue() == true) && (game.getBoard().isCardOnTableP1() == true)) {
                    if (diceValue1 >= cardT1.getCost()) {
                        diceValue1 = diceValue1 - cardT1.getCost();
                        if (diceValue1 < 0) {
                            diceValue1 = 0;
                            valorDado1.setText(String.valueOf(diceValue1));
                        }
                        valorDado1.setText(String.valueOf(diceValue1));
                        life2 = life2 - cardT1.getDamage();
                        game.getBoard().getPlayer2().setLife(life2);
                        if (life2 <= 0){
                            Intent i2 = new Intent (Partida.this, Player1Win.class);
                            startActivity(i2);
                            finish();
                        }
                        lifeP2.setText(String.valueOf(life2));
                        game.getBoard().changeCardOnTableP1();
                        card1.setText("");
                        card1.setBackgroundColor(Color.GRAY);
                    }
                }*/
                game.card1Actions();
            }
        });

        /**
         * Mismas condiciones de la carta del jugador 1 se aplican al jugador 2
         */

        card2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*if ((actualTurn.getTurnValue() == false) && (game.getBoard().isCardOnTableP2() == true)) {
                    if (diceValue2 >= cardT2.getCost()) {
                        diceValue2 = diceValue2 - cardT2.getCost();
                        if (diceValue2 < 0) {
                            diceValue2 = 0;
                            valorDado2.setText(String.valueOf(diceValue2));
                        }
                        valorDado2.setText(String.valueOf(diceValue2));
                        life1 = life1 - cardT2.getDamage();
                        game.getBoard().getPlayer1().setLife(life1);
                        if (life1 <= 0){
                            Intent i3 = new Intent (Partida.this, Player2Win.class);
                            startActivity(i3);
                            finish();
                        }
                        lifeP1.setText(String.valueOf(life1));
                        game.getBoard().changeCardOnTableP2();
                        card2.setText("");
                        card2.setBackgroundColor(Color.GRAY);
                    }
                }*/
                game.card2Actions();
            }
        });

        /**
         * Para acabar el turno, el jugador pulsa el botón
         */

        endTurn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*if (actualTurn.getTurnValue() == true) {
                    game.getBoard().changeDiceRolledP1();
                    actualTurn.changeTurn();
                    turnIndicator.setText("Turn of " + game.getBoard().getPlayer2().getName());
                }*/
                game.endTurn1();
            }
        });

        /**
         * Mismas condiciones del botón acabar turno del jugador 1 se aplican para el jugador 2
         */

        endTurn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*if (actualTurn.getTurnValue() == false) {
                    game.getBoard().changeDiceRolledP2();
                    actualTurn.changeTurn();
                    turnIndicator.setText("Turn of " + game.getBoard().getPlayer1().getName());
                }*/
                game.endTurn2();
            }
        });
    }
}