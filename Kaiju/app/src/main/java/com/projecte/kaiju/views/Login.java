package com.projecte.kaiju.views;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    private boolean isConnected;

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
        cm = (ConnectivityManager) getSystemService(GlobalInfo.getContext().CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();


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
        if (isConnected){
            reload();
        } else {
            Toast.makeText(this, "You must have be connected to the internet!", Toast.LENGTH_SHORT).show();
        }
    }

    public void tochangePswd(){
        Intent i = new Intent(this, ChangePasswordActivity.class);
        startActivity(i);
    }

    private void login() {
        cm = (ConnectivityManager) getSystemService(GlobalInfo.getContext().CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
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
        } else {
            Toast.makeText(this, "You must be connected to the internet!", Toast.LENGTH_SHORT).show();
        }
    }

    private void reload(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            if (currentUser.isEmailVerified()){
                etPassword.setText("");
                etName.setText("");
                String id= currentUser.getUid();
                userRef = db.getReference("users").child(id);

                catchPreviousLogin();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(Login.this, R.string.VerifyEmail,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void catchPreviousLogin(){
        userRef.child("last_login").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    DataSnapshot ds = task.getResult();
                    if (ds.exists()){
                        previousLogin = ds.getValue(String.class);
                        userRef.child("previous_login").setValue(previousLogin);
                        String time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        userRef.child("last_login").setValue(time);
                        String loggedText = "The last time you LoggedIn was at: "; //Frase a traducir
                        String previousLoginText = loggedText + previousLogin;
                        Toast.makeText(Login.this, previousLoginText, Toast.LENGTH_SHORT).show();
                    } else {
                        String time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        userRef.child("last_login").setValue(time);
                        Log.d(myClassTag, "error: datasnapshot does not exist:");
                        Toast.makeText(Login.this, "Congratulations it's your first LogIn! =)", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d(myClassTag, "error: task not successful:");
                }
            }
        });
    }
}