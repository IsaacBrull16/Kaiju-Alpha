package com.projecte.kaiju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Partida extends AppCompatActivity {
    public static Game game;

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

        nameText1 = (TextView) findViewById(R.id.nameText1);
        costText1 = (TextView) findViewById(R.id.costText1);
        damageText1 = (TextView) findViewById(R.id.damageText1);
        lifeText1 = (TextView) findViewById(R.id.lifeText1);
        typeText1 = (TextView) findViewById(R.id.typeText1);

        dado = (Button) findViewById(R.id.dado);
        dado.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Dice dice = new Dice();
                //valorDado.setText("");
                int int_random = dice.getInt_random();
                System.out.println(int_random);
                //valorDado.setText(int_random);

            }
        });

         homeButton = (Button) findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Partida.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        deckButton = (Button) findViewById(R.id.deckButton);

        deckButton.setOnClickListener(new View.OnClickListener(){
            Deck deck = new Deck();
            int resDeck = deck.resDeck;
            @Override
            public void onClick(View v) {
                while (resDeck != 0)
                {
                    int card_random = deck.getCard_random();
                    Card card1r = deck.Random_Card();
                    nameText1.setText("Name: " + card1r.getName());
                    costText1.setText("Cost: " + card1r.getCost());
                    damageText1.setText("Damage: " + card1r.getDamage());
                    lifeText1.setText("Life: " + card1r.getLife());
                    typeText1.setText("Type: " + card1r.getType());
                    resDeck--;
                }
            }
        });
    }
}