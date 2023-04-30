package com.projecte.kaiju.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.GlobalInfo;
import com.projecte.kaiju.viewmodels.PartidaViewModel;

public class Player1Win extends AppCompatActivity {

    Button homeButton;
    Button playButton;

    TextView winnerConditionsText;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference playRef;

    private String result;

    private PartidaViewModel partidaviewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player1_win);
        partidaviewModel = new ViewModelProvider(this).get(PartidaViewModel.class);
        homeButton = (Button) findViewById(R.id.homeButton);
        playButton = (Button) findViewById(R.id.playButton);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            String id = mAuth.getCurrentUser().getUid();
            String url = GlobalInfo.getInstance().getFB_DB();
            db = FirebaseDatabase.getInstance(url);
            playRef = db.getReference(id).child("last_game_result");
        }

        /*playRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                result = snapshot.getValue(String.class);
                if (result == "win"){
                    winnerConditionsText.setText(R.string.YouWin);
                } else {
                    winnerConditionsText.setText(R.string.YouLose);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        /**
         * Si el usuario quiere volver al menú pulsará esta opción
         */

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Player1Win.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        /**
         * Si el usuario quiere volver a jugar, pulsará esta opción
         */

        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent (Player1Win.this, Partida.class);
                startActivity(i2);
                finish();
            }
        });
    }
}