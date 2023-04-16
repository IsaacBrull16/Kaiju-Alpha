package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projecte.kaiju.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference myRef;

    EditText userNameP;
    EditText userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userNameP = findViewById(R.id.userNameP);
        userEmail = findViewById(R.id.userEmail);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        userEmail.setText(user.getEmail());

        myRef = db.getReference();

        findViewById(R.id.logOutButton).setOnClickListener(v -> logOut());
        findViewById(R.id.homeButtonP).setOnClickListener(v -> home());
        findViewById(R.id.saveButton).setOnClickListener(v -> save());
        findViewById(R.id.editNameButton).setOnClickListener(v -> rename());
        findViewById(R.id.editPhotoButton).setOnClickListener(v -> changePhoto());
        findViewById(R.id.changePswd).setOnClickListener(v -> changePswd());
    }

    public void logOut(){
        mAuth.signOut();
        finish();
    }

    public void home(){
        mAuth.signOut();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void save(){
        mAuth.signOut();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void rename(){
        String newname = userNameP.getText().toString();
    }

    public void changePswd(){
        Intent i = new Intent(this, ChangePasswordActivity.class);
        startActivity(i);
    }

    public void changePhoto(){
        Intent i = new Intent(this, ChangePhotoActivity.class);
        startActivity(i);
    }
}