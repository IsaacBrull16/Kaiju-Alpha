package com.projecte.kaiju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Partida extends AppCompatActivity {
    public static Game game;
    /**
     * Declaramos todos los objetos del layout que querramos modificar/usar
     */
    Button dado;
    Button homeButton;
    Button deckButton;

    TextView valorDado;
    TextView nameText1;
    TextView costText1;
    TextView damageText1;
    TextView lifeText1;
    TextView typeText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        valorDado = (TextView) findViewById(R.id.valorDado);

        /**
         * Encontramos cada uno de los TextViews del layout
         */

        nameText1 = (TextView) findViewById(R.id.nameText1);
        costText1 = (TextView) findViewById(R.id.costText1);
        damageText1 = (TextView) findViewById(R.id.damageText1);
        lifeText1 = (TextView) findViewById(R.id.lifeText1);
        typeText1 = (TextView) findViewById(R.id.typeText1);

        /**
         * Hacemos un dado que sea tirable
         */

        Button dado = findViewById(R.id.dado);
        TextView valorDado = findViewById(R.id.valorDado);

        dado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int valor = rand.nextInt(6) + 1;

                valorDado.setText(String.valueOf(valor));
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

        Deck deck = new Deck();
        deck.Shuffle();

        deckButton = (Button) findViewById(R.id.deckButton);

        deckButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //deck.Shuffle();
                Card cardr = deck.putCard();
                nameText1.setText("Name: " + cardr.getName());
                costText1.setText("Cost: " + cardr.getCost());
                damageText1.setText("Damage: " + cardr.getDamage());
                lifeText1.setText("Life: " + cardr.getLife());
                typeText1.setText("Type: " + cardr.getType());
            }
        });
    }
}