package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.GlobalInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GlobalInfo.getInstance().setContext(this.getApplicationContext());

        findViewById(R.id.jugar).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Partida.class);
                startActivity(i);
                finish();

            }
        });

        findViewById(R.id.aboutbutton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent (MainActivity.this, About.class);
                startActivity(i2);
                finish();
            }
        });

        findViewById(R.id.usrButton).setOnClickListener(v -> toProfile());
        findViewById(R.id.usrDeckButton).setOnClickListener(v -> toPersonalDeck());
    }

    public void toProfile(){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void toPersonalDeck(){
        Intent i = new Intent(this, PersonalDeckActivity.class);
        startActivity(i);
        finish();
    }
}