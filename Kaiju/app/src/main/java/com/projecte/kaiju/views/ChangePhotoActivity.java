package com.projecte.kaiju.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.GlobalInfo;

public class ChangePhotoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference usrRef;
    private String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_photo);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usr = mAuth.getCurrentUser();

        if (usr != null){
            String url = GlobalInfo.getInstance().getFB_DB();
            db = FirebaseDatabase.getInstance(url);
            String id = usr.getUid();

            usrRef = db.getReference("users").child(id);
        }

        findViewById(R.id.savePhotoButton).setOnClickListener(v -> save());
        findViewById(R.id.returnButton).setOnClickListener(v -> notSave());
        findViewById(R.id.profImgButton1).setOnClickListener(v -> img = "ic_icono");
        findViewById(R.id.profImgButton2).setOnClickListener(v -> img = "plactbot");
        findViewById(R.id.profImgButton3).setOnClickListener(v -> img = "logogame1");
        findViewById(R.id.profImgButton4).setOnClickListener(v -> img = "fondo");
    }

    public void save(){
        usrRef.child("profile_image").setValue(img).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent i = new Intent (ChangePhotoActivity.this, ProfileActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void notSave(){
        Intent i = new Intent (ChangePhotoActivity.this, ProfileActivity.class);
        startActivity(i);
        finish();
    }
}