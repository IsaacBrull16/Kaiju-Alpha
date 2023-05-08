package com.projecte.kaiju.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.GlobalInfo;

public class PlayModeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseDatabase db;

    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_mode);
        mAuth = FirebaseAuth.getInstance();
        String url= GlobalInfo.getInstance().getFB_DB();
        db = FirebaseDatabase.getInstance(url);
        if (mAuth.getCurrentUser() != null) {
            String id = mAuth.getCurrentUser().getUid();
            userRef = db.getReference("users").child(id).child("play_mode");
        }

        findViewById(R.id.localButton).setOnClickListener(v -> toPlay());
        findViewById(R.id.multijugadorButton).setOnClickListener(v -> toPlay());
        findViewById(R.id.IAButton).setOnClickListener(v -> iaMode());
        findViewById(R.id.homePModeButton).setOnClickListener(v -> toHome());
        findViewById(R.id.multijugadorButton).setVisibility(View.INVISIBLE);
        //findViewById(R.id.IAButton).setVisibility(View.INVISIBLE);

    }
    public void toPlay(){
        userRef.setValue("player");
        Intent intent = new Intent(this, Partida.class);
        startActivity(intent);
        finish();
    }
    public void toHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void iaMode(){
        userRef.setValue("IA");
        Intent intent = new Intent(this, Partida.class);
        startActivity(intent);
        finish();
    }
}