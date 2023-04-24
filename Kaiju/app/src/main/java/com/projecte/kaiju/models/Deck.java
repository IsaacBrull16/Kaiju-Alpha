package com.projecte.kaiju.models;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opencsv.CSVReader;
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
import java.util.List;

public class Deck {

    /**
     * Privatizamos nuestro número de cartas para que no se le pueda cambiar y un arraylist
     * con todas las cartas del jugador
     */
    protected String myClassTag = this.getClass().getSimpleName();

    private ArrayList<Card> deckCards = new ArrayList<Card>();

    private ArrayList<CardHelper> personalDeck = new ArrayList<CardHelper>();

    private ArrayList<Card> totalCards = new ArrayList<Card>();

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private int numCards;

    public Deck(){

        allCards();

        String url = GlobalInfo.getInstance().getFB_DB();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance(url);

        if (mAuth.getCurrentUser() != null) {
            String id = mAuth.getCurrentUser().getUid();
            mRef = mDatabase.getReference("users").child(id).child("personal_deck");
            OnDataLoadedListener listener = new OnDataLoadedListener() {
                @Override
                public void onDataLoaded(List<CardHelper> personalDeck) {
                    compareDecks();
                }
            };
            getPersonalDeck(listener);
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

    /*Card Trotowild = new Card(0, "Trotowild", 1, 2, 5, "Nature");
    Card PlantBot = new Card(1, "PlantBot", 2, 6, 2, "Technology");
    Card ElectroRazz = new Card(2, "ElectroRazz", 3, 2, 4, "Science");
    Card TechnoLight = new Card(3, "TechnoLight", 5, 10, 2, "Technology");
    Card DuckWind = new Card(4, "DuckWind", 4, 2, 9, "Nature");
    Card PlasticKiller = new Card(5, "PlasticKiller", 6, 11, 3, "Science");*/

    public void getPersonalDeck(final OnDataLoadedListener listener) {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    String idCard = ds.getKey();
                    String nameCard = ds.getValue(String.class);
                    personalDeck.add(new CardHelper(idCard, nameCard));
                }
                listener.onDataLoaded(personalDeck);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void allCards() {
        for (int i = 0; i < rowsData; i++){
                int id = Integer.parseInt(data[i][0]);
                String name = data[i][1];
                int cost = Integer.parseInt(data[i][2]);
                int damage = Integer.parseInt(data[i][3]);
                int life = Integer.parseInt(data[i][4]);
                String type = data[i][5];
                totalCards.add(new Card(id, name, cost, damage, life, type));
        }
        /*try {
            String csvFile = "kaiju.csv";
            CSVReader csvReader = new CSVReader(new FileReader(csvFile));
            //FileReader fr = new FileReader(csvFile);
            //BufferedReader br = new BufferedReader(fr);
            String[] line = null;
            while ((line = csvReader.readNext()) != null) {
                //String[] values = line.split(",");
                int id = Integer.parseInt(line[0]);
                String name = line[1];
                int cost = Integer.parseInt(line[2]);
                int damage = Integer.parseInt(line[3]);
                int life = Integer.parseInt(line[4]);
                String type = line[5];
                totalCards.add(new Card(id, name, cost, damage, life, type));
            }
            csvReader.close();
            //fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }*/
    }

    private static final String[][] data = {{"0","Trotowild","1","2","5","Nature"},
        {"1","PlantBot","2","6","2","Technology"},
        {"2","ElectroRazz","1","2","4","Science"},
        {"3","TechnoLight","5","10","2","Technology"},
        {"4","DuckWind","4","2","9","Nature"},
        {"5","PlasticKiller","6","11","3","Science"}};

    private int rowsData = data.length;

    public void compareDecks(){
        for (int i = 0; i < personalDeck.size(); i++){
            int idPD = Integer.parseInt(personalDeck.get(i).getId());
            for (int j = 0; j < totalCards.size(); j++){
                if (totalCards.get(j).getId() == idPD){
                    Card cardj = totalCards.get(j);
                    Log.d(myClassTag, cardj.getName());
                    deckCards.add(cardj);
                } else {
                    System.err.println("Error of Cards");
                }
            }
        }
        for (int i = 0; i < deckCards.size(); i++) {
            System.out.println(deckCards.get(i).getName());
        }
        numCards = deckCards.size();
    }

    public interface OnDataLoadedListener {
        void onDataLoaded(List<CardHelper> personalDeck);
    }
}

