package com.projecte.kaiju.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.GlobalInfo;

public class ChangePhotoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference usrRef;
    private String img;

    private final String[] images = new String[] {"user", "estrella", "caritaperfil", "user__1_"};

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
            usrRef.child("profile_image").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String img = snapshot.getValue(String.class);
                    noImages();
                    paint(img);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        findViewById(R.id.savePhotoButton).setOnClickListener(v -> save());
        findViewById(R.id.returnButton).setOnClickListener(v -> notSave());
        findViewById(R.id.profImgButton1).setOnClickListener(v -> selectImg("user"));
        findViewById(R.id.profImgButton2).setOnClickListener(v -> selectImg("estrella"));
        findViewById(R.id.profImgButton3).setOnClickListener(v -> selectImg("caritaperfil"));
        findViewById(R.id.profImgButton4).setOnClickListener(v -> selectImg("user__1_"));
    }

    public void save(){
        usrRef.child("profile_image").setValue(img).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent i = new Intent (ChangePhotoActivity.this, ProfileActivity.class);
                startActivity(i);
                Toast.makeText(ChangePhotoActivity.this, "Your photo has been changed succesfully! :D", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void notSave(){
        Intent i = new Intent (ChangePhotoActivity.this, ProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void selectImg(String newImg){
        img = newImg;
        noImages();
        paint(newImg);
    }

    public void noImages(){
        for (int i = 0; i < images.length; i++) {
            String img = images[i];
            depaint(img);
        }
    }

    public void depaint(String dpimg){
        switch(dpimg){
            case "user":
                findViewById(R.id.profImgButton1).setAlpha(0.3f);
                break;
            case "estrella":
                findViewById(R.id.profImgButton2).setAlpha(0.3f);
                break;
            case "caritaperfil":
                findViewById(R.id.profImgButton3).setAlpha(0.3f);
                break;
            case "user__1_":
                findViewById(R.id.profImgButton4).setAlpha(0.3f);
                break;
        }
    }

    public void paint(String pimg){
        switch(pimg){
            case "user":
                findViewById(R.id.profImgButton1).setAlpha(1f);
                break;
            case "estrella":
                findViewById(R.id.profImgButton2).setAlpha(1f);
                break;
            case "caritaperfil":
                findViewById(R.id.profImgButton3).setAlpha(1f);
                break;
            case "user__1_":
                findViewById(R.id.profImgButton4).setAlpha(1f);
                break;
        }
    }
}