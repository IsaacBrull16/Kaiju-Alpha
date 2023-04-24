package com.projecte.kaiju.models;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projecte.kaiju.helpers.CardHelper;
import com.projecte.kaiju.helpers.GlobalInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    /**
     * Privatizamos nuestro número de cartas para que no se le pueda cambiar y un arraylist
     * con todas las cartas del jugador
     */

    private static final String KAIJU_CSV = "models/Kaiju.csv";
    //private static final File csvFile = new File("Kaiju.csv");

    private static ArrayList<Card> deckCards = new ArrayList<Card>();

    private ArrayList<CardHelper> personalDeck = new ArrayList<CardHelper>();

    private ArrayList<Card> totalCards = new ArrayList<Card>();

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private int numCards;




    public Deck(){

        allCards();

        deckCards.add(Trotowild);
        deckCards.add(PlantBot);
        deckCards.add(ElectroRazz);
        deckCards.add(TechnoLight);
        deckCards.add(DuckWind);
        deckCards.add(PlasticKiller);
        numCards = deckCards.size();

        String url = GlobalInfo.getInstance().getFB_DB();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance(url);

        if (mAuth.getCurrentUser() != null) {
            String id = mAuth.getCurrentUser().getUid();
            mRef = mDatabase.getReference("users").child(id).child("personal_deck");
            getPersonalDeck();
        }
    }


    /**
     * Mezclará las cartas
     */

    public void Shuffle() {
        Collections.shuffle(deckCards);
    }

    /**
     * Seleccionará la primera carta del mazo
     *
     * @return
     */

    public Card putCard() {
        Card card = deckCards.get(0);
        deckCards.remove(0);
        return card;
    }

    public int deckSize() {
        return deckCards.size();
    }

    /**
     * Creamos nuestra baraja de cartas
     */

    Card Trotowild = new Card(0, "Trotowild", 1, 2, 5, "Nature");
    Card PlantBot = new Card(1, "PlantBot", 2, 6, 2, "Technology");
    Card ElectroRazz = new Card(2, "ElectroRazz", 3, 2, 4, "Science");
    Card TechnoLight = new Card(3, "TechnoLight", 5, 10, 2, "Technology");
    Card DuckWind = new Card(4, "DuckWind", 4, 2, 9, "Nature");
    Card PlasticKiller = new Card(5, "PlasticKiller", 6, 11, 3, "Science");

    public void getPersonalDeck() {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
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

    public void allCards() {
        try {
            //FileInputStream fis = new FileInputStream(csvFile);
            BufferedReader br = new BufferedReader(new FileReader(KAIJU_CSV));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                int cost = Integer.parseInt(values[2]);
                int damage = Integer.parseInt(values[3]);
                int life = Integer.parseInt(values[4]);
                String type = values[5];
                totalCards.add(new Card(id, name, cost, damage, life, type));
            }
            br.close();
            //fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

