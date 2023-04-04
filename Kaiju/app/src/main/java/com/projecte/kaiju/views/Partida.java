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

    private PartidaViewModel partidaviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        partidaviewModel = new ViewModelProvider(this).get(PartidaViewModel.class);

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

        //Game game = new Game();

        /**
         * Ponemos color gris a las cartas cuando no se pueden usar y ponemos información en los textos
         */

        //card1.setBackgroundColor(Color.GRAY);
        //card2.setBackgroundColor(Color.GRAY);
        /*lifeP1.setText(String.valueOf(game.getBoard().getPlayer1().getLife()));
        lifeP2.setText(String.valueOf(game.getBoard().getPlayer2().getLife()));
        turnIndicator.setText("Turn of " + game.getBoard().getPlayer1().getName());*/

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
                /*
                game.dice1Actions();
                valorDado1.setText(String.valueOf(game.getBoard().getPlayer1().getPlayerDice().getValue()));
                if ((game.getBoard().getPlayer1().getPlayerDice().getValue() >= game.cardOnTable1().getCost()) && (game.getBoard().isCardOnTableP1() == true)){
                    card1.setBackgroundColor(Color.parseColor("#3F2893"));
                }*/
                partidaviewModel.launchDice1();
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
                /*
                game.dice2Actions();
                valorDado2.setText(String.valueOf(game.getBoard().getPlayer2().getPlayerDice().getValue()));
                if ((game.getBoard().getPlayer2().getPlayerDice().getValue() >= game.cardOnTable2().getCost()) && (game.getBoard().isCardOnTableP2() == true)){
                    card2.setBackgroundColor(Color.parseColor("#3F2893"));
                }*/
                partidaviewModel.launchDice2();
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
                /*game.deck1Actions();
                card1.setText("Name: " + game.cardOnTable1().getName() + "\n\nCost: " + game.cardOnTable1().getCost() + "\n\nDamage: " + game.cardOnTable1().getDamage());
                if (game.getBoard().getPlayer1().getPlayerDice().getValue() >= game.cardOnTable1().getCost()) {
                    card1.setBackgroundColor(Color.parseColor("#3F2893"));
                }*/
                partidaviewModel.setCardOnT1();
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
                /*game.deck2Actions();
                card2.setText("Name: " + game.cardOnTable2().getName() + "\n\nCost: " + game.cardOnTable2().getCost() + "\n\nDamage: " + game.cardOnTable2().getDamage());
                if (game.getBoard().getPlayer2().getPlayerDice().getValue() >= game.cardOnTable2().getCost()) {
                    card2.setBackgroundColor(Color.parseColor("#3F2893"));
                }*/
                partidaviewModel.setCardOnT2();
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
                /*game.card1Actions();
                if (game.getBoard().getPlayer2().getLife() <= 0){
                    Intent i2 = new Intent (Partida.this, Player1Win.class);
                    startActivity(i2);
                    finish();
                }
                valorDado1.setText(String.valueOf(game.getBoard().getPlayer1().getPlayerDice().getValue()));
                lifeP2.setText(String.valueOf(game.getBoard().getPlayer2().getLife()));
                card1.setText("");
                card1.setBackgroundColor(Color.GRAY);*/
                partidaviewModel.useCard1();
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
                /*game.card2Actions();
                if (game.getBoard().getPlayer1().getLife() <= 0){
                    Intent i3 = new Intent (Partida.this, Player2Win.class);
                    startActivity(i3);
                    finish();
                }
                valorDado2.setText(String.valueOf(game.getBoard().getPlayer2().getPlayerDice().getValue()));
                lifeP1.setText(String.valueOf(game.getBoard().getPlayer1().getLife()));
                card2.setText("");
                card2.setBackgroundColor(Color.GRAY);*/
                partidaviewModel.useCard2();
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
                /*game.endTurn1();
                turnIndicator.setText("Turn of " + game.getBoard().getPlayer2().getName());*/
                partidaviewModel.changeTurn1();
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
                /*game.endTurn2();
                turnIndicator.setText("Turn of " + game.getBoard().getPlayer1().getName());*/
                partidaviewModel.changeTurn2();
            }
        });
    }
}