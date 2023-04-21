package com.projecte.kaiju.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_deck);
        String url = GlobalInfo.getInstance().getFB_DB();

        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance(url);

        if (mAuth.getCurrentUser() != null){
            String id = mAuth.getCurrentUser().getUid();
            mRef = mDatabase.getReference("users").child(id).child("personal_deck");
            getPersonalDeck();
        }

        findViewById(R.id.ReturnbuttonDeck).setOnClickListener(v ->returnMain());
        findViewById(R.id.cardImgButton1).setOnClickListener(v -> addCard("0", "Trotowild"));
        findViewById(R.id.cardImgButton2).setOnClickListener(v -> addCard("1", "PlantBot"));
        findViewById(R.id.cardImgButton3).setOnClickListener(v -> addCard("2", "ElectroRazz"));
        findViewById(R.id.cardImgButton4).setOnClickListener(v -> addCard("3", "TecnoLight"));
        findViewById(R.id.cardImgButton5).setOnClickListener(v -> addCard("4", "DuckWind"));
        findViewById(R.id.cardImgButton6).setOnClickListener(v -> addCard("5", "PlastiKiller"));
        findViewById(R.id.SaveDeckButton).setOnClickListener(v -> saveDeck());
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
            if (cardHelper.equals(newCard)) {
                found = true;
                break;
            }
        }
        if(!found){
            for(int i = 1; i < personalDeck.size(); i++){
                personalDeck.set(i - 1, personalDeck.get(i));
            }
            personalDeck.set(personalDeck.size() - 1, newCard);
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
                    Toast.makeText(PersonalDeckActivity.this, "Your cards have been saved correctly :D", Toast.LENGTH_SHORT).show();
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
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}

