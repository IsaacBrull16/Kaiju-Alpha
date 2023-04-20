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

public class PersonalDeckActivity extends AppCompatActivity {

    private String[] personalDeck = new String[0];
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
            mRef = mDatabase.getReference("users").child(id);
            getPersonalDeck();
        }

        findViewById(R.id.ReturnbuttonDeck).setOnClickListener(v ->returnMain());
        findViewById(R.id.logoImageButton1).setOnClickListener(v -> addImage("image1"));
        findViewById(R.id.SavebuttonDeck).setOnClickListener(v -> saveDeck());
    }

    public void returnMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void addImage(String image){
        boolean found = false;
        for (String s : personalDeck) {
            if (s.equals(image)) {
                found = true;
            }
        }
        if(!found){
            personalDeck[0] = "";
            for(int j = 0; j < personalDeck.length; j++){
                personalDeck[j] = personalDeck[j+1];
            }
            personalDeck[personalDeck.length-1] = image;
        }
    }
    public void saveDeck(){

    }

    public void getPersonalDeck(){
        mRef.child("personalDeck").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int nChildren = (int) snapshot.getChildrenCount();
                personalDeck = new String[nChildren];
                for(DataSnapshot ds : snapshot.getChildren()){
                    personalDeck[Integer.parseInt(ds.getKey())] = ds.getValue(String.class);
                }
                personalDeck = snapshot.getValue(String[].class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}

