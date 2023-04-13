package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.projecte.kaiju.R;

public class TermsConditionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        findViewById(R.id.understoodButton).setOnClickListener(v -> finish());
    }


}