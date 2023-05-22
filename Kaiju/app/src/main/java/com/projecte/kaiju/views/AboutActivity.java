package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.projecte.kaiju.R;

public class AboutActivity extends AppCompatActivity {

    ImageButton homebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        /**
         * Creamos botón para ir a la MainActivity (Home)
         */

        homebutton = (ImageButton) findViewById(R.id.homebutton);

        homebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent (AboutActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.TCButton2).setOnClickListener(v -> loadTC());
    }

    public void loadTC(){
        Intent i = new Intent(this, TermsConditionsActivity.class);
        startActivity(i);
    }
}