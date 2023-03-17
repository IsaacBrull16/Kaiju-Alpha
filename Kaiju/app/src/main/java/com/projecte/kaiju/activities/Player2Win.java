package com.projecte.kaiju.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projecte.kaiju.R;

public class Player2Win extends AppCompatActivity {
    Button homeButton;
    Button playButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2_win);

        homeButton = (Button) findViewById(R.id.homeButton);
        playButton = (Button) findViewById(R.id.playButton);

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Player2Win.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent (Player2Win.this, Partida.class);
                startActivity(i2);
                finish();
            }
        });
    }
}