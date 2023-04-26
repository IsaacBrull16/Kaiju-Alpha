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
import android.widget.Toast;

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
    private boolean diceRolledP1;
    private boolean diceRolledP2;
    private boolean cardUsedP1;
    private boolean cardUsedP2;
    private boolean cardCantUseP1;
    private boolean cardCantUseP2;

    private boolean currentPlayer;
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
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);

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
                if (integer <= 0){
                    Intent i2 = new Intent (Partida.this, Player1Win.class);
                    startActivity(i2);
                    finish();
                }
                lifeP1.setText(String.valueOf(integer));
            }
        });

        partidaviewModel.getLife2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer <= 0){
                    Intent i3 = new Intent (Partida.this, Player1Win.class);
                    startActivity(i3);
                    finish();
                }
                lifeP2.setText(String.valueOf(integer));
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
                    cardCantUseP1 = false;
                } else {
                    cardCantUseP1 = true;
                    card1.setBackgroundColor(Color.GRAY);
                }
        });

        partidaviewModel.getIsCard2Playable().observe(this, Boolean -> {
            if (Boolean == true){
                card2.setBackgroundColor(Color.parseColor("#3F2893"));
                cardCantUseP2 = false;
            } else {
                cardCantUseP2 = true;
                card2.setBackgroundColor(Color.GRAY);
            }
        });

        partidaviewModel.getIsCard1OnTable().observe(this, Boolean -> {
            if (Boolean == false){
                card1.setText("");
                cardUsedP1 = false;
            } else {
                cardUsedP1 = true;
            }
        });

        partidaviewModel.getIsCard2OnTable().observe(this, Boolean -> {
            if (Boolean == false) {
                card2.setText("");
                cardUsedP2 = false;
            } else {
                cardUsedP2 = true;
            }
        });

        partidaviewModel.getIsDice1Rolled().observe(this, Boolean -> {
            if (Boolean == true){
                diceRolledP1 = true;
            } else {
                diceRolledP1 = false;
            }
        });

        partidaviewModel.getIsDice2Rolled().observe(this, Boolean -> {
            if (Boolean == true){
                diceRolledP2 = true;
            } else {
                diceRolledP2 = false;
            }
        });

        partidaviewModel.getTurnChanged().observe(this, Boolean -> {
            if (Boolean == true){
                currentPlayer = true;
                findViewById(R.id.deckButton2).setVisibility(View.INVISIBLE);
                findViewById(R.id.endTurn2).setVisibility(View.INVISIBLE);
                findViewById(R.id.dado2).setVisibility(View.INVISIBLE);
                findViewById(R.id.deckButton1).setVisibility(View.VISIBLE);
                findViewById(R.id.endTurn1).setVisibility(View.VISIBLE);
                findViewById(R.id.dado1).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.deckButton2).setVisibility(View.VISIBLE);
                findViewById(R.id.endTurn2).setVisibility(View.VISIBLE);
                findViewById(R.id.dado2).setVisibility(View.VISIBLE);
                findViewById(R.id.deckButton1).setVisibility(View.INVISIBLE);
                findViewById(R.id.endTurn1).setVisibility(View.INVISIBLE);
                findViewById(R.id.dado1).setVisibility(View.INVISIBLE);
                currentPlayer = false;
            }
        });


        /**
         * Al dar al botón de dado, se lanzará, irá guardando el valor si no se va usando,
         * pondrá el valor por texto y te indicará si la carta la puedes usar
         */

        findViewById(R.id.dado1).setOnClickListener(v -> playingDice1());


        /**
         * Mismas condiciones del dado del jugador 1 se aplican para jugador 2
         */

        findViewById(R.id.dado2).setOnClickListener(v -> playingDice2());

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

        findViewById(R.id.deckButton1).setOnClickListener(v -> addCard1());

        /**
         * Mismas condiciones de baraja de jugador 1 se aplican para jugador 2
         */

        findViewById(R.id.deckButton2).setOnClickListener(v -> addCard2());

        /**
         * La carta solo se podrá usar si su valor es menor o igual al del dado.
         * Al ser pulsada la carta desaparecerá del tablero y restará vida al jugador contrario,
         * el valor del dado se reducirá por el coste de la carta y si la vida del jugador es igual
         * o inferior a 0, se irá a la pantalla del jugador ganador
         */

        card1.setOnClickListener(v -> useCard1());

        /**
         * Mismas condiciones de la carta del jugador 1 se aplican al jugador 2
         */

        card2.setOnClickListener(v -> useCard2());

        /**
         * Para acabar el turno, el jugador pulsa el botón
         */

        findViewById(R.id.endTurn1).setOnClickListener(v -> partidaviewModel.changeTurn1());

        /**
         * Mismas condiciones del botón acabar turno del jugador 1 se aplican para el jugador 2
         */

        findViewById(R.id.endTurn2).setOnClickListener(v -> partidaviewModel.changeTurn2());
    }

    public void playingDice1(){
        if(diceRolledP1 == false){
            partidaviewModel.launchDice1();
        } else {
            Toast.makeText(this, "You have already rolled the dice", Toast.LENGTH_SHORT).show();
        }
    }

    public void playingDice2(){
        if(diceRolledP2 == false){
            partidaviewModel.launchDice2();
        } else {
            Toast.makeText(this, "You have already rolled the dice", Toast.LENGTH_SHORT).show();
        }
    }

    public void addCard1(){
        if(cardUsedP1 == false){
            partidaviewModel.setCardOnT1();
        } else {
            Toast.makeText(this, "You have already a card on the table", Toast.LENGTH_SHORT).show();
        }
    }

    public void addCard2(){
        if(cardUsedP2 == false){
            partidaviewModel.setCardOnT2();
        } else {
            Toast.makeText(this, "You have already a card on the table", Toast.LENGTH_SHORT).show();
        }
    }

    public void useCard1(){
        if(cardCantUseP1 == false){
            partidaviewModel.useCard1();
        } else if (currentPlayer == false) {
            Toast.makeText(this, "It's not your turn", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You can't use this card", Toast.LENGTH_SHORT).show();
        }
    }

    public void useCard2(){
        if(cardCantUseP2 == false){
            partidaviewModel.useCard2();
        } else if (currentPlayer == true) {
            Toast.makeText(this, "It's not your turn", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You can't use this card", Toast.LENGTH_SHORT).show();
        }
    }
}