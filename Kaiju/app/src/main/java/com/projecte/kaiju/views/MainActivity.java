package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projecte.kaiju.R;

public class MainActivity extends AppCompatActivity {
    TextView termsId;
    Button acceptTerms;

    Button jugar;

    Button aboutbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jugar=(Button)findViewById(R.id.jugar);

        jugar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Partida.class);
                startActivity(i);
                finish();

            }
        });

        aboutbutton = (Button) findViewById(R.id.aboutbutton);

        aboutbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent (MainActivity.this, About.class);
                startActivity(i2);
                finish();
            }
        });


        termsId = (TextView) findViewById(R.id.termsId);
        acceptTerms = (Button) findViewById(R.id.acceptTerms);
    }
    public void invisible(View v){

        termsId.setVisibility(View.INVISIBLE);
        acceptTerms.setVisibility(View.INVISIBLE);

    }
}