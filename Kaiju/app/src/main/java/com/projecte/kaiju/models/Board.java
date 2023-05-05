package com.projecte.kaiju.models;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projecte.kaiju.helpers.GlobalInfo;

/**
 * @author: Álex Pellitero García
 * @version: 17/02/2023/A
 * Esta clase define el tablero del juego, que serán 3 cartas por jugador
 */

public class Board {

    private boolean cardTableP1 = false;
    private boolean cardTableP2 = false;
    private boolean diceRolledP1 = false;
    private boolean diceRolledP2 = false;
    private static Player player1;
    private static Player player2;

    private FirebaseAuth mAuth;

    private FirebaseDatabase db;

    private DatabaseReference nameRef;

    public Board() {
        mAuth = FirebaseAuth.getInstance();
        String url= GlobalInfo.getInstance().getFB_DB();
        db = FirebaseDatabase.getInstance(url);

        if(mAuth.getCurrentUser() != null){
            nameRef = db.getReference("users").child(mAuth.getCurrentUser().getUid()).child("name");
            nameRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.getValue(String.class);
                    player1 = new Player(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        player2 = new Player("J2");
        player1.setLife(25);
        player2.setLife(25);
    }

    /**
     * Método getter de jugador 1
     * @return
     */

    public Player getPlayer1(){
        return this.player1;
    }

    /**
     * Método getter de jugador 2
     * @return
     */

    public Player getPlayer2(){
        return this.player2;
    }

    /**
     * Método que nos permitirá decidir si hay una carta o no en el tablero del jugador 1
     */

    public void changeCardOnTableP1(){
        if (cardTableP1 == true){
            this.cardTableP1 = false;
        } else {
            this.cardTableP1 = true;
        }
    }

    /**
     * Método que nos retornará el estado de la carta en el tablero del jugador 1
     * @return
     */

    public boolean isCardOnTableP1(){
        return this.cardTableP1;
    }

    /**
     * Método que nos permitirá decidir si hay una carta o no en el tablero del jugador 2
     */

    public void changeCardOnTableP2(){
        if (cardTableP2 == true){
            this.cardTableP2 = false;
        } else {
            this.cardTableP2 = true;
        }
    }

    /**
     * Método que nos retornará el estado de la carta en el tablero del jugador 2
     * @return
     */

    public boolean isCardOnTableP2(){
        return this.cardTableP2;
    }

    /**
     * Método que nos retornará el estado del dado en el tablero del jugador 1
     * @return
     */

    public boolean getDiceRolledP1(){
        return this.diceRolledP1;
    }

    /**
     * Método que nos permitirá decidir si hay un dado o no en el tablero del jugador 1
     */

    public void changeDiceRolledP1(){
        if (diceRolledP1 == true){
            this.diceRolledP1 = false;
        } else {
            this.diceRolledP1 = true;
        }
    }

    /**
     * Método que nos retornará el estado del dado en el tablero del jugador 2
     * @return
     */

    public boolean getDiceRolledP2(){
        return this.diceRolledP2;
    }

    /**
     * Método que nos permitirá decidir si hay un dado o no en el tablero del jugador 2
     */

    public void changeDiceRolledP2(){
        if (diceRolledP2 == true){
            this.diceRolledP2 = false;
        } else {
            this.diceRolledP2 = true;
        }
    }

}