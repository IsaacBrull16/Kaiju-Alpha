package com.projecte.kaiju.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference usrRef;
    private String name;

    private String lastLogin;


    EditText userNameP;
    TextView userEmail;

    TextView LastLoginText;

    ImageView usrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userNameP = findViewById(R.id.userNameP);
        userEmail = findViewById(R.id.userEmail);
        usrImage = findViewById(R.id.imageView);
        LastLoginText = findViewById(R.id.LastLoginText);

        String url = GlobalInfo.getInstance().getFB_DB();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance(url);

        FirebaseUser usr = mAuth.getCurrentUser();
        if (usr != null) {
            userEmail.setText(usr.getEmail());
            String id = usr.getUid();

            usrRef = db.getReference("users").child(id);
            usrRef.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    name = snapshot.getValue(String.class);
                    userNameP.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            usrRef.child("profile_image").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String image = snapshot.getValue(String.class);

                    if (image != null){
                        usrImage.setImageDrawable(idDrawable(getApplicationContext(), image));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            usrRef.child("last_login").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    lastLogin = snapshot.getValue(String.class);
                    LastLoginText.setText(R.string.last_login);
                    String lLogin = LastLoginText.getText().toString();
                    LastLoginText.setText(lLogin + ": " + lastLogin);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            Toast.makeText(this, R.string.Error_Email, Toast.LENGTH_SHORT).show();
        }



        findViewById(R.id.logOutButton).setOnClickListener(v -> logOut());
        findViewById(R.id.homeButtonP).setOnClickListener(v -> home());
        findViewById(R.id.editNameButton).setOnClickListener(v -> rename());
        findViewById(R.id.editPhotoButton).setOnClickListener(v -> changePhoto());
        findViewById(R.id.changePswd).setOnClickListener(v -> changePswd());
    }

    public void logOut(){
        mAuth.signOut();
        finish();
    }

    public void home(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void rename(){
        String newname = userNameP.getText().toString();
        usrRef.child("name").setValue(newname).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ProfileActivity.this, "Your name has been changed succesfully! :D", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changePswd(){
        Intent i = new Intent(this, ChangePasswordActivity.class);
        startActivity(i);
    }

    public void changePhoto(){
        Intent i = new Intent(this, ChangePhotoActivity.class);
        startActivity(i);
        finish();
    }

    public static Drawable idDrawable(Context cont, String profile){
        switch (profile){
            case "user":
                return ContextCompat.getDrawable(cont, R.drawable.component_11);
            case "estrella":
                return ContextCompat.getDrawable(cont, R.drawable.component_1);
            case "user__1_":
                return ContextCompat.getDrawable(cont, R.drawable.component_2);
            case "caritaperfil":
                return ContextCompat.getDrawable(cont, R.drawable.carita);
            default:
                return null;
        }
    }
}