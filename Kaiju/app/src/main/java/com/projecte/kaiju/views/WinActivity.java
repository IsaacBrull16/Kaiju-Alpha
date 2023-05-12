package com.projecte.kaiju.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.projecte.kaiju.R;

public class WinActivity extends AppCompatActivity {

    Button homeButton;
    Button playButton;

    TextView winnerConditionsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        winnerConditionsText = (TextView) findViewById(R.id.winnerConditionsText);
        homeButton = (Button) findViewById(R.id.homeButton);
        playButton = (Button) findViewById(R.id.playButton);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getString("result") != null) {
            String result = bundle.getString("result");
            if (result.equals("J1")) {
                winnerConditionsText.setText(R.string.YouWin);
            } else {
                winnerConditionsText.setText(R.string.YouLose);
            }

        }


        /**
         * Si el usuario quiere volver al menú pulsará esta opción
         */

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent (WinActivity.this, MainActivity.class);
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
                Intent i2 = new Intent (WinActivity.this, Partida.class);
                startActivity(i2);
                finish();
            }
        });
    }
}