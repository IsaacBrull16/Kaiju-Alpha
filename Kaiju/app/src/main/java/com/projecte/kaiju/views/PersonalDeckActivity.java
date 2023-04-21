package com.projecte.kaiju.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.GlobalInfo;

import java.util.ArrayList;

public class PersonalDeckActivity extends AppCompatActivity {

    private ArrayList<String> personalDeck = new ArrayList<>();
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
        findViewById(R.id.logoImageButton1).setOnClickListener(v -> addCard("image1"));
        findViewById(R.id.SavebuttonDeck).setOnClickListener(v -> saveDeck());
    }

    public void returnMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void addCard(String image){
        boolean found = false;
        for (String s : personalDeck) {
            if (s.equals(image)) {
                found = true;
                break;
            }
        }
        if(!found){
            for(int i = 1; i < personalDeck.size(); i++){
                personalDeck.set(i - 1, personalDeck.get(i));
            }
            personalDeck.set(personalDeck.size() - 1, image);
            System.out.println(personalDeck);
        }
    }
    public void saveDeck(){

    }

    public void getPersonalDeck(){
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()){
                    personalDeck.add(ds.getKey());
                    System.out.println(personalDeck);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}

