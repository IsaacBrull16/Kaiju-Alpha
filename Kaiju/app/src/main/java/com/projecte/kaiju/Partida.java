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
    TextView nameText2;
    TextView costText2;
    TextView damageText2;
    TextView lifeText2;
    TextView typeText2;
    TextView nameText3;
    TextView costText3;
    TextView damageText3;
    TextView lifeText3;
    TextView typeText3;
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
        nameText2 = (TextView) findViewById(R.id.nameText2);
        costText2 = (TextView) findViewById(R.id.costText2);
        damageText2 = (TextView) findViewById(R.id.damageText2);
        lifeText2 = (TextView) findViewById(R.id.lifeText2);
        typeText2 = (TextView) findViewById(R.id.typeText2);
        nameText3 = (TextView) findViewById(R.id.nameText3);
        costText3 = (TextView) findViewById(R.id.costText3);
        damageText3 = (TextView) findViewById(R.id.damageText3);
        lifeText3 = (TextView) findViewById(R.id.lifeText3);
        typeText3 = (TextView) findViewById(R.id.typeText3);

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

        deckButton = (Button) findViewById(R.id.deckButton);

        deckButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                /**
                 * Primero creamos una clase Deck(Baraja), creamos un contador de las cartas
                 * restantes (resDeck) y escogemos una carta aleatoria de entre las disponibles
                 */
                Deck deck = new Deck();
                int resDeck = deck.resDeck;
                Card cardr = deck.Random_Card();
                /*int randomCard = deck.getCard_random();
                System.out.println(resDeck);
                System.out.println(nameText1.getText());
                System.out.println(randomCard);
                System.out.println(cardr);*/
                /**
                 * El primer if nos verá si la primera carta ya está puesta, en caso que no,
                 * añadimos las caracteristicas de nuestra carta
                 */
                if ((resDeck != 0) && (nameText1.getText().equals("Name"))){
                    nameText1.setText("Name: " + cardr.getName());
                    costText1.setText("Cost: " + cardr.getCost());
                    damageText1.setText("Damage: " + cardr.getDamage());
                    lifeText1.setText("Life: " + cardr.getLife());
                    typeText1.setText("Type: " + cardr.getType());
                    resDeck--;
                }

                /**
                 * Si la primera carta si está ocupada pero la segunda no, se pone en la segunda
                 * posición del tablero
                 */

                else if ((resDeck != 0) && (!nameText1.getText().equals("Name")) && (nameText2.getText().equals("Name"))){

                    /**
                     * Si la primera carta ya es la que acaba de salir obviamos el resultado
                     * Si es diferente a la primera carta, se añade en la segunda posición
                     */

                    if (nameText1.getText().equals("Name: " + cardr.getName())){
                        nameText2.setText("Name");
                        costText2.setText("Cost");
                        damageText2.setText("Damage");
                        lifeText2.setText("Life");
                        typeText2.setText("Type");
                    } else {
                        nameText2.setText("Name: " + cardr.getName());
                        costText2.setText("Cost: " + cardr.getCost());
                        damageText2.setText("Damage: " + cardr.getDamage());
                        lifeText2.setText("Life: " + cardr.getLife());
                        typeText2.setText("Type: " + cardr.getType());
                        resDeck--;
                    }
                }

                /**
                 * Si la primera y segunda cartas son ocupadas y la tercera está disponible,
                 * pondremos la carta en la tercera y última posición disponible
                 */

                else if ((resDeck != 0) && (!nameText1.getText().equals("Name")) && (!nameText2.getText().equals("Name")) && (nameText3.getText().equals("Name"))) {

                    /**
                     * Si la carta ya existe en la primera posición o en la segunda, no se añadirá
                     * Si no, se pondrá en el tablero
                     */

                    if (nameText1.getText().equals("Name: " + cardr.getName())) {
                        nameText3.setText("Name");
                        costText3.setText("Cost");
                        damageText3.setText("Damage");
                        lifeText3.setText("Life");
                        typeText3.setText("Type");
                    } else if (nameText2.getText().equals("Name: " + cardr.getName())) {
                        nameText3.setText("Name");
                        costText3.setText("Cost");
                        damageText3.setText("Damage");
                        lifeText3.setText("Life");
                        typeText3.setText("Type");
                    } else {
                        nameText3.setText("Name: " + cardr.getName());
                        costText3.setText("Cost: " + cardr.getCost());
                        damageText3.setText("Damage: " + cardr.getDamage());
                        lifeText3.setText("Life: " + cardr.getLife());
                        typeText3.setText("Type: " + cardr.getType());
                        resDeck--;
                    }
                }
            }
        });
    }
}