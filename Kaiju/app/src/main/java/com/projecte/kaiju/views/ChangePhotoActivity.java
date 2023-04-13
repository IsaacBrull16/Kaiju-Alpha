package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.projecte.kaiju.R;

public class ChangePhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_photo);

        findViewById(R.id.savePhotoButton).setOnClickListener(v -> save());
        findViewById(R.id.returnButton).setOnClickListener(v -> finish());
    }

    public void save(){

    }
}