package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.projecte.kaiju.R;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //findViewById(R.id.exitGame).setOnClickListener(v -> toExit());
    }

  /*  public void toExit(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }*/
}