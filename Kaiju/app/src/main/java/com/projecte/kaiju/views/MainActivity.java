package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.projecte.kaiju.R;

public class MainActivity extends AppCompatActivity {
    TextView termsId;
    Button acceptTerms;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

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

        findViewById(R.id.LogOutButton).setOnClickListener(v -> logOut());

        termsId = (TextView) findViewById(R.id.termsId);
        acceptTerms = (Button) findViewById(R.id.acceptTerms);
    }
    public void invisible(View v){

        termsId.setVisibility(View.INVISIBLE);
        acceptTerms.setVisibility(View.INVISIBLE);

    }

    public void logOut(){
        mAuth.signOut();
        finish();
    }
}