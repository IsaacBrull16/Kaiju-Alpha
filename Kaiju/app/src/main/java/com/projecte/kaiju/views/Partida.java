package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projecte.kaiju.R;
import com.projecte.kaiju.models.Card;
import com.projecte.kaiju.viewmodels.PartidaViewModel;

public class Partida extends AppCompatActivity {

    /**
     * Declaramos todos los objetos del layout que querramos modificar/usar
     */

    Button card1;
    Button card2;

    TextView valorDado1;
    TextView valorDado2;
    TextView turnIndicator;
    TextView lifeP1;
    TextView lifeP2;

    private PartidaViewModel partidaviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        partidaviewModel = new ViewModelProvider(this).get(PartidaViewModel.class);

        /**
         * Encontramos las id de cada objeto del layout que querramos usar
         */

        valorDado1 = (TextView) findViewById(R.id.valorDado1);
        valorDado2 = (TextView) findViewById(R.id.valorDado2);
        turnIndicator = (TextView) findViewById(R.id.turnIndicator);
        lifeP1 = (TextView) findViewById(R.id.lifeP1);
        lifeP2 = (TextView) findViewById(R.id.lifeP2);

        /**
         * Ponemos color gris a las cartas cuando no se pueden usar y ponemos información en los textos
         */

        partidaviewModel.getDice1().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                valorDado1.setText(String.valueOf(integer));
            }
        });

        partidaviewModel.getDice2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                valorDado2.setText(String.valueOf(integer));
            }
        });

        partidaviewModel.getCard1().observe(this, new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                card1.setText("Name: " + card.getName() + "\n\nCost: " + card.getCost() + "\n\nDamage: " + card.getDamage());
            }
        });

        partidaviewModel.getCard2().observe(this, new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                card2.setText("Name: " + card.getName() + "\n\nCost: " + card.getCost() + "\n\nDamage: " + card.getDamage());
            }
        });

        partidaviewModel.getLife1().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                lifeP1.setText(String.valueOf(integer));
                if (integer <= 0){
                    Intent i2 = new Intent (Partida.this, Player2Win.class);
                    startActivity(i2);
                    finish();
                }
            }
        });

        partidaviewModel.getLife2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                lifeP2.setText(String.valueOf(integer));
                if (integer <= 0){
                    Intent i3 = new Intent (Partida.this, Player1Win.class);
                    startActivity(i3);
                    finish();
                }
            }
        });

        partidaviewModel.getCurrentPlayer().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {
                turnIndicator.setText("Turn of: " + string);
            }
        });

        partidaviewModel.getIsCard1Playable().observe(this, Boolean -> {
                if (Boolean == true){
                    card1.setBackgroundColor(Color.parseColor("#3F2893"));
                } else {
                    card1.setBackgroundColor(Color.GRAY);
                }
        });

        partidaviewModel.getIsCard2Playable().observe(this, Boolean -> {
            if (Boolean == true){
                card2.setBackgroundColor(Color.parseColor("#3F2893"));
            } else {
                card2.setBackgroundColor(Color.GRAY);
            }
        });

        partidaviewModel.getIsCard1OnTable().observe(this, Boolean -> {
            if (Boolean == false){
                card1.setText("");
            }
        });

        partidaviewModel.getIsCard2OnTable().observe(this, Boolean -> {
            if (Boolean == false) {
                card2.setText("");
            }
        });

        /**
         * Al dar al botón de dado, se lanzará, irá guardando el valor si no se va usando,
         * pondrá el valor por texto y te indicará si la carta la puedes usar
         */

        findViewById(R.id.dado1).setOnClickListener(v -> partidaviewModel.launchDice1());

        /**
         * Mismas condiciones del dado del jugador 1 se aplican para jugador 2
         */

        findViewById(R.id.dado2).setOnClickListener(v -> partidaviewModel.launchDice2());

        /**
         * Hacemos un botón para ir a la MainActivity(Página Principal)
         */

        findViewById(R.id.homeButton).setOnClickListener(new View.OnClickListener(){
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

        findViewById(R.id.deckButton1).setOnClickListener(v -> partidaviewModel.setCardOnT1());

        /**
         * Mismas condiciones de baraja de jugador 1 se aplican para jugador 2
         */

        findViewById(R.id.deckButton2).setOnClickListener(v -> partidaviewModel.setCardOnT2());

        /**
         * La carta solo se podrá usar si su valor es menor o igual al del dado.
         * Al ser pulsada la carta desaparecerá del tablero y restará vida al jugador contrario,
         * el valor del dado se reducirá por el coste de la carta y si la vida del jugador es igual
         * o inferior a 0, se irá a la pantalla del jugador ganador
         */

        findViewById(R.id.card1).setOnClickListener(v -> partidaviewModel.useCard1());

        /**
         * Mismas condiciones de la carta del jugador 1 se aplican al jugador 2
         */

        findViewById(R.id.card2).setOnClickListener(v -> partidaviewModel.useCard2());

        /**
         * Para acabar el turno, el jugador pulsa el botón
         */

        findViewById(R.id.endTurn1).setOnClickListener(v -> partidaviewModel.changeTurn1());

        /**
         * Mismas condiciones del botón acabar turno del jugador 1 se aplican para el jugador 2
         */

        findViewById(R.id.endTurn2).setOnClickListener(v -> partidaviewModel.changeTurn2());
    }
}