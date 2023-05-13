package com.projecte.kaiju.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.GlobalInfo;
import com.projecte.kaiju.models.Card;
import com.projecte.kaiju.viewmodels.PartidaViewModel;

public class Partida extends AppCompatActivity {

    /**
     * Declaramos todos los objetos del layout que querramos modificar/usar
     */
    protected String myClassTag = this.getClass().getSimpleName();
    ImageButton card11;
    ImageButton card12;
    ImageButton card13;
    ImageButton card21;
    ImageButton card22;
    ImageButton card23;

    TextView valorDado1;
    TextView valorDado2;
    TextView turnIndicator;
    TextView lifeP1;
    TextView lifeP2;

    TextView vidaCarta11;
    TextView vidaCarta12;
    TextView vidaCarta13;
    TextView vidaCarta21;
    TextView vidaCarta22;
    TextView vidaCarta23;
    ImageButton dice1Button;
    ImageButton dice2Button;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference playRef;

    private PartidaViewModel partidaviewModel;
    private boolean diceRolledP1;
    private boolean diceRolledP2;
    private boolean cardUsedP11;
    private boolean cardUsedP12;
    private boolean cardUsedP13;
    private boolean cardUsedP21;
    private boolean cardUsedP22;
    private boolean cardUsedP23;
    private boolean cardCantUseP11;
    private boolean cardCantUseP12;
    private boolean cardCantUseP13;
    private boolean cardCantUseP21;
    private boolean cardCantUseP22;
    private boolean cardCantUseP23;

    private boolean currentPlayer;

    private boolean player2Type;

    private boolean isCard11Pressed;
    private boolean isCard12Pressed;
    private boolean isCard13Pressed;

    private boolean isCard21Pressed;
    private boolean isCard22Pressed;
    private boolean isCard23Pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);



        currentPlayer = true;
        diceRolledP1 = false;
        diceRolledP2 = false;
        cardUsedP11 = false;
        cardUsedP12 = false;
        cardUsedP13 = false;
        cardUsedP21 = false;
        cardUsedP22 = false;
        cardUsedP23 = false;
        cardCantUseP11 = false;
        cardCantUseP12 = false;
        cardCantUseP13 = false;
        cardCantUseP21 = false;
        cardCantUseP22 = false;
        cardCantUseP23 = false;

        partidaviewModel = new ViewModelProvider(this).get(PartidaViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getString("tipusdejoc") != null) {
            String type = bundle.getString("tipusdejoc");
            partidaviewModel.setPlayerMode(type);
            Log.d(myClassTag, type);
        }



        /**
         * Encontramos las id de cada objeto del layout que querramos usar
         */

        valorDado1 = (TextView) findViewById(R.id.valorDado1);
        valorDado2 = (TextView) findViewById(R.id.valorDado2);
        turnIndicator = (TextView) findViewById(R.id.turnIndicator);
        lifeP1 = (TextView) findViewById(R.id.lifeP1);
        lifeP2 = (TextView) findViewById(R.id.lifeP2);
        card11 = findViewById(R.id.card11);
        card12 = findViewById(R.id.card12);
        card13 = findViewById(R.id.card13);
        card21 = findViewById(R.id.card21);
        card22 = findViewById(R.id.card22);
        card23 = findViewById(R.id.card23);
        dice1Button = findViewById(R.id.dice1Button);
        dice2Button = findViewById(R.id.dice2Button);
        vidaCarta11 = findViewById(R.id.vidaCarta11);
        vidaCarta12 = findViewById(R.id.vidaCarta12);
        vidaCarta13 = findViewById(R.id.vidaCarta13);
        vidaCarta21 = findViewById(R.id.vidaCarta21);
        vidaCarta22 = findViewById(R.id.vidaCarta22);
        vidaCarta23 = findViewById(R.id.vidaCarta23);

        card11.setVisibility(View.INVISIBLE);
        card12.setVisibility(View.INVISIBLE);
        card13.setVisibility(View.INVISIBLE);
        card21.setVisibility(View.INVISIBLE);
        card22.setVisibility(View.INVISIBLE);
        card23.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            String id = mAuth.getCurrentUser().getUid();
            String url = GlobalInfo.getInstance().getFB_DB();
            db = FirebaseDatabase.getInstance(url);
            playRef = db.getReference(id);

        }

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

        partidaviewModel.getCard11().observe(this, new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                card11.setBackgroundColor(Color.LTGRAY);
                card11.setImageDrawable(idCard(GlobalInfo.getInstance().getContext(), card.getId()));
            }
        });

        partidaviewModel.getCard12().observe(this, new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                card12.setBackgroundColor(Color.LTGRAY);
                card12.setImageDrawable(idCard(GlobalInfo.getInstance().getContext(), card.getId()));
            }
        });

        partidaviewModel.getCard13().observe(this, new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                card13.setBackgroundColor(Color.LTGRAY);
                card13.setImageDrawable(idCard(GlobalInfo.getInstance().getContext(), card.getId()));
            }
        });

        partidaviewModel.getCard21().observe(this, new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                card21.setBackgroundColor(Color.LTGRAY);
                card21.setImageDrawable(idCard(GlobalInfo.getInstance().getContext(), card.getId()));
            }
        });

        partidaviewModel.getCard22().observe(this, new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                card22.setBackgroundColor(Color.LTGRAY);
                card22.setImageDrawable(idCard(GlobalInfo.getInstance().getContext(), card.getId()));
            }
        });

        partidaviewModel.getCard23().observe(this, new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                card23.setBackgroundColor(Color.LTGRAY);
                card23.setImageDrawable(idCard(GlobalInfo.getInstance().getContext(), card.getId()));
            }
        });

        partidaviewModel.getLife1().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer <= 0){
                    lifeP1.setText(String.valueOf(0));
                    Intent intent = new Intent(Partida.this, WinActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("result", "J2");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else {
                    lifeP1.setText(String.valueOf(integer));
                }

            }
        });

        partidaviewModel.getLife2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer <= 0) {
                    lifeP2.setText(String.valueOf(0));
                    Intent intent = new Intent(Partida.this, WinActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("result", "J1");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else {
                    lifeP2.setText(String.valueOf(integer));
                }
            }
        });

        partidaviewModel.getCurrentPlayer().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {
                turnIndicator.setText("Turn of: " + string);
            }
        });

        partidaviewModel.getIsCard11Playable().observe(this, Boolean -> {
            if (Boolean == true){
                card11.setBackgroundColor(Color.GREEN);
                cardCantUseP11 = false;
            } else {
                cardCantUseP11 = true;
                card11.setBackgroundColor(Color.LTGRAY);
            }
        });

        partidaviewModel.getIsCard12Playable().observe(this, Boolean -> {
            if (Boolean == true){
                card12.setBackgroundColor(Color.GREEN);
                cardCantUseP12 = false;
            } else {
                cardCantUseP12 = true;
                card12.setBackgroundColor(Color.LTGRAY);
            }
        });

        partidaviewModel.getIsCard13Playable().observe(this, Boolean -> {
            if (Boolean == true){
                card13.setBackgroundColor(Color.GREEN);
                cardCantUseP13 = false;
            } else {
                cardCantUseP13 = true;
                card13.setBackgroundColor(Color.LTGRAY);
            }
        });

        partidaviewModel.getIsCard21Playable().observe(this, Boolean -> {
            if (Boolean == true){
                card21.setBackgroundColor(Color.GREEN);
                cardCantUseP21 = false;
            } else {
                cardCantUseP21 = true;
                card21.setBackgroundColor(Color.LTGRAY);
            }
        });

        partidaviewModel.getIsCard22Playable().observe(this, Boolean -> {
            if (Boolean == true){
                card22.setBackgroundColor(Color.GREEN);
                cardCantUseP22 = false;
            } else {
                cardCantUseP22 = true;
                card22.setBackgroundColor(Color.LTGRAY);
            }
        });

        partidaviewModel.getIsCard23Playable().observe(this, Boolean -> {
            if (Boolean == true){
                card23.setBackgroundColor(Color.GREEN);
                cardCantUseP23 = false;
            } else {
                cardCantUseP23 = true;
                card23.setBackgroundColor(Color.LTGRAY);
            }
        });

        partidaviewModel.getIsCard11OnTable().observe(this, Boolean -> {
            if (Boolean == false){
                card11.setVisibility(View.INVISIBLE);
                vidaCarta11.setVisibility(View.INVISIBLE);
                cardUsedP11 = false;
            } else {
                cardUsedP11 = true;
                card11.setVisibility(View.VISIBLE);
                vidaCarta11.setVisibility(View.VISIBLE);
            }
        });

        partidaviewModel.getIsCard12OnTable().observe(this, Boolean -> {
            if (Boolean == false){
                card12.setVisibility(View.INVISIBLE);
                vidaCarta12.setVisibility(View.INVISIBLE);
                cardUsedP12 = false;
            } else {
                cardUsedP12 = true;
                card12.setVisibility(View.VISIBLE);
                vidaCarta12.setVisibility(View.VISIBLE);
            }
        });

        partidaviewModel.getIsCard13OnTable().observe(this, Boolean -> {
            if (Boolean == false){
                card13.setVisibility(View.INVISIBLE);
                vidaCarta13.setVisibility(View.INVISIBLE);
                cardUsedP13 = false;
            } else {
                cardUsedP13 = true;
                card13.setVisibility(View.VISIBLE);
                vidaCarta13.setVisibility(View.VISIBLE);
            }
        });

        partidaviewModel.getIsCard21OnTable().observe(this, Boolean -> {
            if (Boolean == false) {
                card21.setVisibility(View.INVISIBLE);
                vidaCarta21.setVisibility(View.INVISIBLE);
                cardUsedP21 = false;
            } else {
                cardUsedP21 = true;
                card21.setVisibility(View.VISIBLE);
                vidaCarta21.setVisibility(View.VISIBLE);
            }
        });

        partidaviewModel.getIsCard22OnTable().observe(this, Boolean -> {
            if (Boolean == false) {
                card22.setVisibility(View.INVISIBLE);
                vidaCarta22.setVisibility(View.INVISIBLE);
                cardUsedP22 = false;
            } else {
                cardUsedP22 = true;
                card22.setVisibility(View.VISIBLE);
                vidaCarta22.setVisibility(View.VISIBLE);
            }
        });

        partidaviewModel.getIsCard23OnTable().observe(this, Boolean -> {
            if (Boolean == false) {
                card23.setVisibility(View.INVISIBLE);
                vidaCarta23.setVisibility(View.INVISIBLE);
                cardUsedP23 = false;
            } else {
                cardUsedP23 = true;
                card23.setVisibility(View.VISIBLE);
                vidaCarta23.setVisibility(View.VISIBLE);
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
                Toast.makeText(Partida.this, "Player 1 Turn", Toast.LENGTH_SHORT).show();
                findViewById(R.id.deckButton2).setVisibility(View.INVISIBLE);
                findViewById(R.id.endTurn2).setVisibility(View.INVISIBLE);
                findViewById(R.id.dice2Button).setVisibility(View.INVISIBLE);
                findViewById(R.id.valorDado2).setVisibility(View.INVISIBLE);
                findViewById(R.id.deckButton1).setVisibility(View.VISIBLE);
                findViewById(R.id.endTurn1).setVisibility(View.VISIBLE);
                findViewById(R.id.dice1Button).setVisibility(View.VISIBLE);
                findViewById(R.id.valorDado1).setVisibility(View.VISIBLE);
            } else if(player2Type == false) {
                Toast.makeText(Partida.this, "Player 2 Turn", Toast.LENGTH_SHORT).show();
                findViewById(R.id.deckButton2).setVisibility(View.VISIBLE);
                findViewById(R.id.endTurn2).setVisibility(View.VISIBLE);
                findViewById(R.id.dice2Button).setVisibility(View.VISIBLE);
                findViewById(R.id.valorDado2).setVisibility(View.VISIBLE);
                findViewById(R.id.deckButton1).setVisibility(View.INVISIBLE);
                findViewById(R.id.endTurn1).setVisibility(View.INVISIBLE);
                findViewById(R.id.dice1Button).setVisibility(View.INVISIBLE);
                findViewById(R.id.valorDado1).setVisibility(View.INVISIBLE);
                currentPlayer = false;
            } else {
                findViewById(R.id.deckButton1).setVisibility(View.INVISIBLE);
                findViewById(R.id.endTurn1).setVisibility(View.INVISIBLE);
                findViewById(R.id.dice1Button).setVisibility(View.INVISIBLE);
                findViewById(R.id.valorDado1).setVisibility(View.INVISIBLE);
                currentPlayer = false;
            }
        });

        partidaviewModel.getActNum1().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                dice1Button.setImageDrawable(diceNum(GlobalInfo.getInstance().getContext(), integer));
                valorDado1.setText(String.valueOf(integer));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        partidaviewModel.getActNum2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                dice2Button.setImageDrawable(diceNum(GlobalInfo.getInstance().getContext(), integer));
                valorDado2.setText(String.valueOf(integer));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        partidaviewModel.getActPlusNewDiceNum1().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {
                valorDado1.setText(string);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        partidaviewModel.getActPlusNewDiceNum2().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {
                valorDado2.setText(string);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        partidaviewModel.getObjective1().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        partidaviewModel.getObjective2().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        partidaviewModel.getCard11Life().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer)  {
                if(integer<=0) {
                    card11.setVisibility(View.INVISIBLE);
                    vidaCarta11.setVisibility(View.INVISIBLE);
                } else {
                    vidaCarta11.setText(String.valueOf(integer));
                }
            }
        });

        partidaviewModel.getCard12Life().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer)  {
                if(integer<=0) {
                    card12.setVisibility(View.INVISIBLE);
                    vidaCarta12.setVisibility(View.INVISIBLE);
                } else {
                    vidaCarta12.setText(String.valueOf(integer));
                }
            }
        });

        partidaviewModel.getCard13Life().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer)  {
                if(integer<=0) {
                    card13.setVisibility(View.INVISIBLE);
                    vidaCarta13.setVisibility(View.INVISIBLE);
                } else {
                    vidaCarta13.setText(String.valueOf(integer));
                }
            }
        });

        partidaviewModel.getCard21Life().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer<=0) {
                    card21.setVisibility(View.INVISIBLE);
                    vidaCarta21.setVisibility(View.INVISIBLE);
                } else {
                    vidaCarta21.setText(String.valueOf(integer));
                }
            }
        });

        partidaviewModel.getCard22Life().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer<=0) {
                    card22.setVisibility(View.INVISIBLE);
                    vidaCarta22.setVisibility(View.INVISIBLE);
                } else {
                    vidaCarta22.setText(String.valueOf(integer));
                }
            }
        });

        partidaviewModel.getCard23Life().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer<=0) {
                    card23.setVisibility(View.INVISIBLE);
                    vidaCarta23.setVisibility(View.INVISIBLE);
                } else {
                    vidaCarta23.setText(String.valueOf(integer));
                }
            }
        });

        partidaviewModel.getPlayer2Type().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("IA")){
                    player2Type = true;
                    findViewById(R.id.deckButton2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.endTurn2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.dice2Button).setVisibility(View.INVISIBLE);
                    findViewById(R.id.valorDado2).setVisibility(View.INVISIBLE);
                } else {
                    player2Type = false;
                }
            }
        });

        /**
         * Al dar al botón de dado, se lanzará, irá guardando el valor si no se va usando,
         * pondrá el valor por texto y te indicará si la carta la puedes usar
         */

        findViewById(R.id.dice1Button).setOnClickListener(v -> playingDice1());


        /**
         * Mismas condiciones del dado del jugador 1 se aplican para jugador 2
         */

        findViewById(R.id.dice2Button).setOnClickListener(v -> playingDice2());

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

        card11.setOnClickListener(v -> useCard11());
        card12.setOnClickListener(v -> useCard12());
        card13.setOnClickListener(v -> useCard13());

        /**
         * Mismas condiciones de la carta del jugador 1 se aplican al jugador 2
         */

        card21.setOnClickListener(v -> useCard21());
        card22.setOnClickListener(v -> useCard22());
        card23.setOnClickListener(v -> useCard23());

        /**
         * Para acabar el turno, el jugador pulsa el botón
         */

        findViewById(R.id.endTurn1).setOnClickListener(v -> endTurn1());

        /**
         * Mismas condiciones del botón acabar turno del jugador 1 se aplican para el jugador 2
         */

        findViewById(R.id.endTurn2).setOnClickListener(v -> endTurn2());

        findViewById(R.id.imageBusuario1).setOnClickListener(v -> attackPlayer1());

        findViewById(R.id.imageBusuario2).setOnClickListener(v -> attackPlayer2());


    }


    public void playingDice1(){
        if(diceRolledP1 == false){
            partidaviewModel.launchDice1();
        } else {
            Toast.makeText(this, R.string.DiceRolled, Toast.LENGTH_SHORT).show();
        }
    }

    public void playingDice2(){
        if(diceRolledP2 == false){
            partidaviewModel.launchDice2();
        } else {
            Toast toast = Toast.makeText(this, R.string.DiceRolled, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();
        }
    }

    public void addCard1(){
        if(!diceRolledP1){
            Toast.makeText(this, R.string.NotDiceRolled, Toast.LENGTH_SHORT).show();
        } else if (cardUsedP11){
            Toast.makeText(this, R.string.CardOnTable, Toast.LENGTH_SHORT).show();
        } else {
            partidaviewModel.setCardOnT11();
            card11.setVisibility(View.VISIBLE);
            vidaCarta11.setVisibility(View.VISIBLE);
        }
    }

    public void addCard2(){
        if(!diceRolledP2){
            Toast toast = Toast.makeText(this, R.string.NotDiceRolled, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();
        } else if (cardUsedP21){
            Toast toast = Toast.makeText(this, R.string.CardOnTable, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();
        } else {
            partidaviewModel.setCardOnT21();
            card21.setVisibility(View.VISIBLE);
            vidaCarta21.setVisibility(View.VISIBLE);
        }
    }

    public void useCard11() {
        if ((cardCantUseP11 == false) && (isCard11Pressed == false) && (currentPlayer==true)) {
            isCard11Pressed = true;
        } else if ((isCard21Pressed == true) && (currentPlayer==false)){
            partidaviewModel.setObjective2("card1");
            partidaviewModel.useCard21();
            isCard21Pressed = false;

        } else if (currentPlayer == false) {
            Toast.makeText(this, R.string.NotTurn, Toast.LENGTH_SHORT).show();
        } else if (cardUsedP11 == false){
            Toast.makeText(this, R.string.NotCardOnTable, Toast.LENGTH_SHORT).show();
        } else if (isCard11Pressed){
            Toast.makeText(this, "Select your objective", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.CantUseCard, Toast.LENGTH_SHORT).show();
        }
    }

    public void useCard12(){}
    public void useCard13(){}

    public void useCard21(){
        if ((cardCantUseP21 == false) && (isCard21Pressed == false) && (currentPlayer == false)) {
            isCard21Pressed = true;
        } else if ((isCard11Pressed == true) && (currentPlayer == true)){
            partidaviewModel.setObjective1("card1");
            partidaviewModel.useCard11();
            isCard11Pressed = false;
        } else if (currentPlayer == true) {
            Toast toast = Toast.makeText(this, R.string.NotTurn, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();
        } else if (cardUsedP21 == false) {
            Toast toast = Toast.makeText(this, R.string.NotCardOnTable, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();
        } else if (isCard21Pressed){
            Toast toast = Toast.makeText(this, "Select your objective", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();

        } else {
            Toast toast = Toast.makeText(this, R.string.CantUseCard, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();
        }
    }

    public void useCard22(){}
    public void useCard23(){}

    public static Drawable diceNum(Context cont, int diceValue){
        if (diceValue == 1){
            return ContextCompat.getDrawable(cont, R.drawable.dado_1);
        } else if (diceValue == 2){
            return ContextCompat.getDrawable(cont, R.drawable.dado_2);
        } else if (diceValue == 3){
            return ContextCompat.getDrawable(cont, R.drawable.dado_3);
        } else if (diceValue == 4) {
            return ContextCompat.getDrawable(cont, R.drawable.dado_4);
        } else if (diceValue == 5) {
            return ContextCompat.getDrawable(cont, R.drawable.dado_5);
        } else {
            return ContextCompat.getDrawable(cont, R.drawable.dado_6);
        }
    }

    public static Drawable idCard(Context cont, int providedId){
        switch (providedId){
            case 0:
                return ContextCompat.getDrawable(cont, R.drawable.trotuvolco);
            case 1:
                return ContextCompat.getDrawable(cont, R.drawable.plactbot);
            case 2:
                return ContextCompat.getDrawable(cont, R.drawable.electrorazz);
            case 3:
                return ContextCompat.getDrawable(cont, R.drawable.technolight);
            case 4:
                return ContextCompat.getDrawable(cont, R.drawable.duckwind);
            case 5:
                return ContextCompat.getDrawable(cont, R.drawable.plastickiller);
            case 6:
                return ContextCompat.getDrawable(cont, R.drawable.criogen);
            case 7:
                return ContextCompat.getDrawable(cont, R.drawable.mecadog);
            case 8:
                return ContextCompat.getDrawable(cont, R.drawable.demoking);
            case 9:
                return ContextCompat.getDrawable(cont, R.drawable.fyrex);
            case 10:
                return ContextCompat.getDrawable(cont, R.drawable.djbear);
            case 11:
                return ContextCompat.getDrawable(cont, R.drawable.frozwolf);
            case 12:
                return ContextCompat.getDrawable(cont, R.drawable.frogburn);
            case 13:
                return ContextCompat.getDrawable(cont, R.drawable.goldduck);
            case 14:
                return ContextCompat.getDrawable(cont, R.drawable.darckbac);
            case 15:
                return ContextCompat.getDrawable(cont, R.drawable.theeye);
            case 16:
                return ContextCompat.getDrawable(cont, R.drawable.mecamantis);
            case 17:
                return ContextCompat.getDrawable(cont, R.drawable.sharkmaster);
            default:
                return null;
        }
    }
    public void endTurn1() {
        if (!diceRolledP1){
            Toast.makeText(this, R.string.NotDiceRolled, Toast.LENGTH_SHORT).show();
        } else if (player2Type == true) {
            partidaviewModel.changeTurn1();
            partidaviewModel.IAMode();
        } else {
            partidaviewModel.changeTurn1();

        }
    }

    public void endTurn2() {
        if (!diceRolledP2){
            Toast toast = Toast.makeText(this, R.string.NotDiceRolled, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();
        } else {
            partidaviewModel.changeTurn2();

        }
    }

    public void attackPlayer1(){
        if (currentPlayer == false){
            if (isCard21Pressed == true){
                partidaviewModel.setObjective2("player");
                partidaviewModel.useCard21();
                isCard21Pressed = false;
            }
        }
    }
    public void attackPlayer2(){
        if (currentPlayer == true){
            if (isCard11Pressed == true){
                partidaviewModel.setObjective1("player");
                partidaviewModel.useCard11();
                isCard11Pressed = false;
            }
        }
    }
}