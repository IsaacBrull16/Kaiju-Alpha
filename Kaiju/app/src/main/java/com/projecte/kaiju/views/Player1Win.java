package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projecte.kaiju.R;
import com.projecte.kaiju.viewmodels.PartidaViewModel;

public class Player1Win extends AppCompatActivity {

    Button homeButton;
    Button playButton;

    TextView winnerConditionsText;

    private PartidaViewModel partidaviewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player1_win);
        partidaviewModel = new ViewModelProvider(this).get(PartidaViewModel.class);
        homeButton = (Button) findViewById(R.id.homeButton);
        playButton = (Button) findViewById(R.id.playButton);

        partidaviewModel.getWinner().observe(this, Boolean -> {
            if (Boolean == true){
                winnerConditionsText.setText(R.string.YouWin);
            } else {
                winnerConditionsText.setText(R.string.YouLose);
            }
        });
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