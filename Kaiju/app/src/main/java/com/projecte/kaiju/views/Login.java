package com.projecte.kaiju.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Locale;

public class Login extends AppCompatActivity {

    protected String myClassTag = this.getClass().getSimpleName();
    EditText etName;
    EditText etPassword;
    private FirebaseAuth mAuth;

    private FirebaseDatabase db;

    private DatabaseReference userRef;
    private String previousLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = findViewById(R.id.etEmailL);
        etPassword = findViewById(R.id.etPassword);
        findViewById(R.id.btLogin).setOnClickListener(v -> login());
        findViewById(R.id.btSignUp).setOnClickListener(v -> loginToSignUp());
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        String url= GlobalInfo.getInstance().getFB_DB();
        db = FirebaseDatabase.getInstance(url);

        findViewById(R.id.changePswd2).setOnClickListener(v -> tochangePswd());
    }

    private void loginToSignUp() {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        reload();
    }

    public void tochangePswd(){
        Intent i = new Intent(this, ChangePasswordActivity.class);
        startActivity(i);
    }

    private void login() {
        String email = etName.getText().toString();
        String password = etPassword.getText().toString();

        ActivityHelper.hideKeyboard(this);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(myClassTag, "signInWithEmail:success");
                            reload();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(myClassTag, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, R.string.authentication_failed,
                                    Toast.LENGTH_SHORT).show();
                            etPassword.setText("");
                        }
                    }
                });
    }

    private void reload(){
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null) {
            if (currentUser.isEmailVerified()){
                etPassword.setText("");
                etName.setText("");
                String id= currentUser.getUid();
                userRef = db.getReference("users").child(id);
                OnDataLoadedListener listener = new OnDataLoadedListener() {
                    @Override
                    public void onDataLoaded(String previousLogin) {
                        catchPreviousLogin();
                    }
                };
                newLastLogin(listener);
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(Login.this, R.string.VerifyEmail,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public interface OnDataLoadedListener {
        void onDataLoaded(String previousLogin);
    }

    public void catchPreviousLogin(){
        userRef.child("last_login").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                previousLogin = snapshot.getValue(String.class);
                userRef.child("previous_login").setValue(previousLogin);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void newLastLogin (final OnDataLoadedListener listener){
        String time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault()).format(new Date());
        userRef.child("last_login").setValue(time);
        listener.onDataLoaded(previousLogin);
    }
}