package com.projecte.kaiju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText termsId;
    Button acceptTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        termsId = (EditText) findViewById(R.id.termsId);
        acceptTerms = (Button) findViewById(R.id.acceptTerms);

        /*Button a = findViewById(R.id.aboutbutton);
        Intent i = new Intent(this, About.class);
        startActivity(i);
        finish();*/
    }
    public void invisible(View v){

        termsId.setVisibility(View.INVISIBLE);
        acceptTerms.setVisibility(View.INVISIBLE);

    }


}