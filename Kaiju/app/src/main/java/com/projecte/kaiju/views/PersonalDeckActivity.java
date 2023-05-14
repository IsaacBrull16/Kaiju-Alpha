package com.projecte.kaiju.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.CardHelper;
import com.projecte.kaiju.helpers.GlobalInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PersonalDeckActivity extends AppCompatActivity {

    private ArrayList<CardHelper> personalDeck = new ArrayList<>();
    protected String myClassTag = this.getClass().getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    private int ALL_CARDS_LENGHT = 18;

    ImageButton cardImgButton1;
    ImageButton cardImgButton2;
    ImageButton cardImgButton3;
    ImageButton cardImgButton4;
    ImageButton cardImgButton5;
    ImageButton cardImgButton6;
    ImageButton cardImgButton7;
    ImageButton cardImgButton8;
    ImageButton cardImgButton9;
    ImageButton cardImgButton10;
    ImageButton cardImgButton11;
    ImageButton cardImgButton12;
    ImageButton cardImgButton13;
    ImageButton cardImgButton14;
    ImageButton cardImgButton15;
    ImageButton cardImgButton16;
    ImageButton cardImgButton17;
    ImageButton cardImgButton18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_deck);
        String url = GlobalInfo.getInstance().getFB_DB();

        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance(url);

        cardImgButton1 = findViewById(R.id.cardImgButton1);
        cardImgButton2 = findViewById(R.id.cardImgButton2);
        cardImgButton3 = findViewById(R.id.cardImgButton3);
        cardImgButton4 = findViewById(R.id.cardImgButton4);
        cardImgButton5 = findViewById(R.id.cardImgButton5);
        cardImgButton6 = findViewById(R.id.cardImgButton6);
        cardImgButton7 = findViewById(R.id.cardImgButton7);
        cardImgButton8 = findViewById(R.id.cardImgButton8);
        cardImgButton9 = findViewById(R.id.cardImgButton9);
        cardImgButton10 = findViewById(R.id.cardImgButton10);
        cardImgButton11 = findViewById(R.id.cardImgButton11);
        cardImgButton12 = findViewById(R.id.cardImgButton12);
        cardImgButton13 = findViewById(R.id.cardImgButton13);
        cardImgButton14 = findViewById(R.id.cardImgButton14);
        cardImgButton15 = findViewById(R.id.cardImgButton15);
        cardImgButton16 = findViewById(R.id.cardImgButton16);
        cardImgButton17 = findViewById(R.id.cardImgButton17);
        cardImgButton18 = findViewById(R.id.cardImgButton18);

        findViewById(R.id.ReturnbuttonDeck).setOnClickListener(v ->returnMain());
        cardImgButton1.setOnClickListener(v -> addCard("0", "TrotoVolco"));
        cardImgButton2.setOnClickListener(v -> addCard("1", "PlactBot"));
        cardImgButton3.setOnClickListener(v -> addCard("2", "ElectroRazz"));
        cardImgButton4.setOnClickListener(v -> addCard("3", "TecnoLight"));
        cardImgButton5.setOnClickListener(v -> addCard("4", "DuckWind"));
        cardImgButton6.setOnClickListener(v -> addCard("5", "PlastiKiller"));
        cardImgButton7.setOnClickListener(v -> addCard("6", "Criogen"));
        cardImgButton8.setOnClickListener(v -> addCard("7", "MecaDog"));
        cardImgButton9.setOnClickListener(v -> addCard("8", "DemoKing"));
        cardImgButton10.setOnClickListener(v -> addCard("9", "FyRex"));
        cardImgButton11.setOnClickListener(v -> addCard("10", "DJBear"));
        cardImgButton12.setOnClickListener(v -> addCard("11", "FrozWolf"));
        cardImgButton13.setOnClickListener(v -> addCard("12", "FrogBurn"));
        cardImgButton14.setOnClickListener(v -> addCard("13", "GoldDuck"));
        cardImgButton15.setOnClickListener(v -> addCard("14", "DarckBac"));
        cardImgButton16.setOnClickListener(v -> addCard("15", "TheEye"));
        cardImgButton17.setOnClickListener(v -> addCard("16", "MecaMantis"));
        cardImgButton18.setOnClickListener(v -> addCard("17", "SharkMaster"));
        findViewById(R.id.SaveDeckButton).setOnClickListener(v -> saveDeck());

        if (mAuth.getCurrentUser() != null){
            String id = mAuth.getCurrentUser().getUid();
            mRef = mDatabase.getReference("users").child(id).child("personal_deck");
            for (int i = 0; i < ALL_CARDS_LENGHT; i++){
                depaintCard(String.valueOf(i));
            }
            getPersonalDeck();
        }
    }

    public void returnMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void addCard(String newCardId, String newCardName){
        boolean found = false;
        CardHelper newCard = new CardHelper(newCardId, newCardName);
        for (CardHelper cardHelper : personalDeck) {
            if (cardHelper.getId().equals(newCardId)) {
                found = true;
                break;
            }
        }
        if (found){
            Toast.makeText(this, "You already have this card in your deck =O", Toast.LENGTH_SHORT).show();
        } else {
            depaintCard(personalDeck.get(0).getId());
            for(int i = 1; i < personalDeck.size(); i++){
                personalDeck.set(i - 1, personalDeck.get(i));
                paintCard(personalDeck.get(i).getId());
            }
            personalDeck.set(personalDeck.size() - 1, newCard);
            paintCard(personalDeck.get(personalDeck.size() - 1).getId());
        }
    }
    public void saveDeck(){
        Map<String, String> deck = new HashMap<>();
        mRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null){
                    for (int i = 0; i < personalDeck.size(); i++){
                        String idCard = personalDeck.get(i).getId();
                        String idName = personalDeck.get(i).getName();
                        deck.put(idCard, idName);
                    }
                    mRef.setValue(deck);
                    Toast.makeText(PersonalDeckActivity.this, R.string.CardsSaved, Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(myClassTag, "error loading into firebase");
                }
            }
        });
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void getPersonalDeck(){
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()){
                    String idCard = ds.getKey();
                    String nameCard = ds.getValue(String.class);
                    personalDeck.add(new CardHelper(idCard, nameCard));
                    paintCard(idCard);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void paintCard(String idCard){
        switch(idCard){
            case "0":
                cardImgButton1.setBackgroundColor(Color.GREEN);
                break;
            case "1":
                cardImgButton2.setBackgroundColor(Color.GREEN);
                break;
            case "2":
                cardImgButton3.setBackgroundColor(Color.GREEN);
                break;
            case "3":
                cardImgButton4.setBackgroundColor(Color.GREEN);
                break;
            case "4":
                cardImgButton5.setBackgroundColor(Color.GREEN);
                break;
            case "5":
                cardImgButton6.setBackgroundColor(Color.GREEN);
                break;
            case "6":
                cardImgButton7.setBackgroundColor(Color.GREEN);
                break;
            case "7":
                cardImgButton8.setBackgroundColor(Color.GREEN);
                break;
            case "8":
                cardImgButton9.setBackgroundColor(Color.GREEN);
                break;
            case "9":
                cardImgButton10.setBackgroundColor(Color.GREEN);
                break;
            case "10":
                cardImgButton11.setBackgroundColor(Color.GREEN);
                break;
            case "11":
                cardImgButton12.setBackgroundColor(Color.GREEN);
                break;
            case "12":
                cardImgButton13.setBackgroundColor(Color.GREEN);
                break;
            case "13":
                cardImgButton14.setBackgroundColor(Color.GREEN);
                break;
            case "14":
                cardImgButton15.setBackgroundColor(Color.GREEN);
                break;
            case "15":
                cardImgButton16.setBackgroundColor(Color.GREEN);
                break;
            case "16":
                cardImgButton17.setBackgroundColor(Color.GREEN);
                break;
            case "17":
                cardImgButton18.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    public void depaintCard(String idCard){
        switch(idCard){
            case "0":
                cardImgButton1.setBackgroundColor(Color.LTGRAY);
                break;
            case "1":
                cardImgButton2.setBackgroundColor(Color.LTGRAY);
                break;
            case "2":
                cardImgButton3.setBackgroundColor(Color.LTGRAY);
                break;
            case "3":
                cardImgButton4.setBackgroundColor(Color.LTGRAY);
                break;
            case "4":
                cardImgButton5.setBackgroundColor(Color.LTGRAY);
                break;
            case "5":
                cardImgButton6.setBackgroundColor(Color.LTGRAY);
                break;
            case "6":
                cardImgButton7.setBackgroundColor(Color.LTGRAY);
                break;
            case "7":
                cardImgButton8.setBackgroundColor(Color.LTGRAY);
                break;
            case "8":
                cardImgButton9.setBackgroundColor(Color.LTGRAY);
                break;
            case "9":
                cardImgButton10.setBackgroundColor(Color.LTGRAY);
                break;
            case "10":
                cardImgButton11.setBackgroundColor(Color.LTGRAY);
                break;
            case "11":
                cardImgButton12.setBackgroundColor(Color.LTGRAY);
                break;
            case "12":
                cardImgButton13.setBackgroundColor(Color.LTGRAY);
                break;
            case "13":
                cardImgButton14.setBackgroundColor(Color.LTGRAY);
                break;
            case "14":
                cardImgButton15.setBackgroundColor(Color.LTGRAY);
                break;
            case "15":
                cardImgButton16.setBackgroundColor(Color.LTGRAY);
                break;
            case "16":
                cardImgButton17.setBackgroundColor(Color.LTGRAY);
                break;
            case "17":
                cardImgButton18.setBackgroundColor(Color.LTGRAY);
                break;
        }
    }
}

