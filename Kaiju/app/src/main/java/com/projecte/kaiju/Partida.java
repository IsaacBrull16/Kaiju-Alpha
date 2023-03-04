package com.projecte.kaiju;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Partida extends AppCompatActivity {
    public static Game game;

    Button dado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        dado = (Button) findViewById(R.id.dado);
        dado.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Dice dice = new Dice();
                int int_random = dice.getInt_random();
                System.out.println(int_random);
            }
        });


    }
}