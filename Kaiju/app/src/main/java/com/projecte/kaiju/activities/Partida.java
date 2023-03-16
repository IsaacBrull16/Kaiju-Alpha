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
import com.projecte.kaiju.models.Game;
import com.projecte.kaiju.models.Turn;

public class Partida extends AppCompatActivity {
    //private Game game;
    /**
     * Declaramos todos los objetos del layout que querramos modificar/usar
     */

    public static int diceValue = 0;

    Button dado;
    Button homeButton;
    Button deckButton;

    TextView valorDado;
    TextView nameText1;
    TextView costText1;
    TextView damageText1;
    TextView turnIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        valorDado = (TextView) findViewById(R.id.valorDado);
        //ArrayList<Player> jugadores = new ArrayList<>();



        /**
         * Encontramos cada uno de los TextViews del layout
         */

        nameText1 = (TextView) findViewById(R.id.nameText1);
        costText1 = (TextView) findViewById(R.id.costText1);
        damageText1 = (TextView) findViewById(R.id.damageText1);

        Game game = new Game();
        /*Deck deck = new Deck();
        Dice dice = new Dice();
        deck.Shuffle();

        Turn turn = new Turn();*/

        deckButton = (Button) findViewById(R.id.deckButton);

        /**
         * Hacemos un dado que sea tirable
         */

        dado = (Button) findViewById(R.id.dado);
        valorDado = (TextView) findViewById(R.id.valorDado);
        turnIndicator = (TextView) findViewById(R.id.turnIndicator);

        dado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getPlayer1().getPlayerDice().rollDice();
                int diceValue1 = game.getPlayer1().getPlayerDice().getValue();
                valorDado.setText(String.valueOf(diceValue1));
            }
        });

        /**
         * Hacemos un botón para ir a la MainActivity(Página Principal)
         */

        homeButton = (Button) findViewById(R.id.homeButton);

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

        deckButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (game.getTurn().getTurnValue() == true) {
                    if (game.getPlayer1().getDeck().deckSize() != 0) {
                        Card cardr = game.getPlayer1().getDeck().putCard();
                        nameText1.setText("Name: " + cardr.getName());
                        costText1.setText("Cost: " + cardr.getCost());
                        damageText1.setText("Damage: " + cardr.getDamage());
                    }
                }
            }
        });
    }
}