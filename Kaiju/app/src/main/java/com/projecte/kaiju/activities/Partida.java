package com.projecte.kaiju.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

    public static int diceValue1 = 0;
    public static int diceValue2 = 0;
    public static Card cardT1 = new Card(0,"",1000,0,0,"");
    public static Card cardT2 = new Card(0,"",1000,0,0,"");

    public static int life1;
    public static int life2;

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
        lifeP1 = (TextView) findViewById(R.id.lifeP1);
        lifeP2 = (TextView) findViewById(R.id.lifeP2);

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

        card1.setBackgroundColor(Color.GRAY);
        card2.setBackgroundColor(Color.GRAY);

        life1 = game.getPlayer1().getLife();
        lifeP1.setText(String.valueOf(life1));
        life2 = game.getPlayer2().getLife();
        lifeP2.setText(String.valueOf(life2));
        turnIndicator.setText("Turn of P1");

        /**
         * Hacemos un dado que sea tirable
         */

        dado1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((actualTurn.getTurnValue() == true) && (game.getDiceRolledP1() == false)) {
                    diceP1.rollDice();
                    diceValue1 = diceValue1 + (diceP1.getValue());
                    valorDado1.setText(String.valueOf(diceValue1));
                    if ((diceValue1 >= cardT1.getCost()) && (game.isCardOnTableP1() == true)){
                        card1.setBackgroundColor(Color.parseColor("#3F2893"));
                    }
                    game.changeDiceRolledP1();
                }
            }
        });
        dado2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((actualTurn.getTurnValue() == false) && (game.getDiceRolledP2() == false)) {
                    diceP2.rollDice();
                    diceValue2 = diceValue2 + diceP2.getValue();
                    valorDado2.setText(String.valueOf(diceValue2));
                    if ((diceValue2 >= cardT2.getCost()) && (game.isCardOnTableP2() == true)){
                        card2.setBackgroundColor(Color.parseColor("#3F2893"));
                    }
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
                        cardT1 = cardr1;
                        game.changeCardOnTableP1();
                    }
                }
                if (diceValue1 >= cardT1.getCost()) {
                    card1.setBackgroundColor(Color.parseColor("#3F2893"));
                }
            }
        });
        deckButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if ((actualTurn.getTurnValue() == false) && (game.isCardOnTableP2() == false) && (game.getDiceRolledP2() == true)) {
                    if (deckP2.deckSize() != 0) {
                        Card cardr2 = deckP2.putCard();
                        card2.setText("Name: " + cardr2.getName() + "\n\nCost: " + cardr2.getCost() + "\n\nDamage: " + cardr2.getDamage());
                        cardT2 = cardr2;
                        game.changeCardOnTableP2();
                    }
                }
                if (diceValue2 >= cardT2.getCost()) {
                    card2.setBackgroundColor(Color.parseColor("#3F2893"));
                }
            }
        });

        card1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if ((actualTurn.getTurnValue() == true) && (game.isCardOnTableP1() == true)) {
                    if (diceValue1 >= cardT1.getCost()) {
                        diceValue1 = diceValue1 - cardT1.getCost();
                        if (diceValue1 < 0) {
                            diceValue1 = 0;
                            valorDado1.setText(String.valueOf(diceValue1));
                        }
                        valorDado1.setText(String.valueOf(diceValue1));
                        life2 = life2 - cardT1.getDamage();
                        if (life2 <= 0){
                            Intent i2 = new Intent (Partida.this, Player1Win.class);
                            startActivity(i2);
                            finish();
                        }
                        lifeP2.setText(String.valueOf(life2));
                        game.changeCardOnTableP1();
                        card1.setText("");
                        card1.setBackgroundColor(Color.GRAY);
                    }
                }
            }
        });
        card2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if ((actualTurn.getTurnValue() == false) && (game.isCardOnTableP2() == true)) {
                    if (diceValue2 >= cardT2.getCost()) {
                        diceValue2 = diceValue2 - cardT1.getCost();
                        if (diceValue2 < 0) {
                            diceValue2 = 0;
                            valorDado2.setText(String.valueOf(diceValue2));
                        }
                        valorDado2.setText(String.valueOf(diceValue2));
                        life1 = life1 - cardT1.getDamage();
                        if (life1 <= 0){
                            Intent i3 = new Intent (Partida.this, Player2Win.class);
                            startActivity(i3);
                            finish();
                        }
                        lifeP1.setText(String.valueOf(life1));
                        game.changeCardOnTableP2();
                        card2.setText("");
                        card2.setBackgroundColor(Color.GRAY);
                    }
                }
            }
        });
        endTurn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (actualTurn.getTurnValue() == true) {
                    game.changeDiceRolledP1();
                    actualTurn.changeTurn();
                    turnIndicator.setText("Turn of P2");
                }
            }
        });
        endTurn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (actualTurn.getTurnValue() == false) {
                    game.changeDiceRolledP2();
                    actualTurn.changeTurn();
                    turnIndicator.setText("Turn of P1");
                }
            }
        });
    }
}