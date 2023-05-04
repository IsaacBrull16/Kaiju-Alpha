package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.projecte.kaiju.R;

public class PlayModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_mode);

        findViewById(R.id.localButton).setOnClickListener(v -> toPlay());
        findViewById(R.id.multijugadorButton).setOnClickListener(v -> toPlay());
        findViewById(R.id.IAButton).setOnClickListener(v -> toPlay());
        findViewById(R.id.homePModeButton).setOnClickListener(v -> toHome());
        findViewById(R.id.multijugadorButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.IAButton).setVisibility(View.INVISIBLE);
    }
    public void toPlay(){
        Intent intent = new Intent(this, Partida.class);
        startActivity(intent);
        finish();
    }
    public void toHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}