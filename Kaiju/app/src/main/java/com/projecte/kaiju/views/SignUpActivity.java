package com.projecte.kaiju.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.ActivityHelper;
import com.projecte.kaiju.helpers.GlobalInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference myRef;

    private long count;

    EditText etEmail;
    EditText etPasswordS;

    CheckBox checkTC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etPasswordS = findViewById(R.id.etPasswordS);
        checkTC = findViewById(R.id.checkTC);

        String url = GlobalInfo.getInstance().getFB_DB();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance(url);
        myRef = db.getReference("users");


        findViewById(R.id.signUpButton).setOnClickListener(v -> signUp());
        findViewById(R.id.TCButton).setOnClickListener(v -> readTC());
    }

    private void signUp(){
        String email = etEmail.getText().toString();
        String password = etPasswordS.getText().toString();

        ActivityHelper.hideKeyboard(this);

        if (email.equals("") || password.equals("")){
            Toast.makeText(SignUpActivity.this, R.string.FillGasps, Toast.LENGTH_SHORT).show();
        } else if (!checkTC.isChecked()){
            Toast.makeText(SignUpActivity.this, R.string.AcceptTerms, Toast.LENGTH_SHORT).show();
        }else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser usr = mAuth.getCurrentUser();
                        if (usr != null){
                            usr.sendEmailVerification();
                            String id = usr.getUid();

                            DatabaseReference usrRef = myRef.child(id);

                            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    count = snapshot.getChildrenCount();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            long nUsers = count + 1;
                            String newName = "user" + nUsers;

                            Map<String, String> deck = new HashMap<>();
                            deck.put("0", "Trotowild");
                            deck.put("1", "PlantBot");
                            deck.put("2", "ElectroRazz");
                            deck.put("3", "TechnoLight");
                            deck.put("4", "DuckWind");
                            deck.put("5", "PlasticKiller");

                            usrRef.child("name").setValue(newName);
                            usrRef.child("profile_image").setValue("ic_icono");
                            usrRef.child("personal_deck").setValue(deck);
                            String time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault()).format(new Date());
                            usrRef.child("first_login").setValue(time);

                            mAuth.signOut();
                            Toast.makeText(SignUpActivity.this, R.string.VerifyEmail, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, R.string.ErrorEmail, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }


    }

    public void readTC(){
        Intent i = new Intent(this, TermsConditionsActivity.class);
        startActivity(i);
    }
}