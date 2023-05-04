package com.projecte.kaiju.models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projecte.kaiju.helpers.CardHelper;
import com.projecte.kaiju.helpers.GlobalInfo;

import com.projecte.kaiju.helpers.CardsFactoryHelper;
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
        totalCards = CardsFactoryHelper.readData();
    }

    public void compareDecks(){
        for (int i = 0; i < personalDeck.size(); i++){
            int idPD = Integer.parseInt(personalDeck.get(i).getId());
            for (int j = 0; j < totalCards.size(); j++){
                if (totalCards.get(j).getId() == idPD) {
                    Card cardj = totalCards.get(j);
                    Log.d(myClassTag, cardj.getName());
                    deckCards.add(cardj);
                }
            }
        }
        Shuffle();
    }

    public interface OnDataLoadedListener {
        void onDataLoaded(List<CardHelper> personalDeck);
    }
}

