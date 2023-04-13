package com.projecte.kaiju.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.ActivityHelper;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.signUpButton).setOnClickListener(v -> signUp());
        findViewById(R.id.TCButton).setOnClickListener(v -> readTC());
    }

    private void signUp(){
        String email = etEmail.getText().toString();
        String password = etPasswordS.getText().toString();

        ActivityHelper.hideKeyboard(this);

        if (email.equals("") || password.equals("")){
            Toast.makeText(SignUp.this, "You must fill all the gaps!", Toast.LENGTH_SHORT).show();
        } else if (!checkTC.isChecked()){
            Toast.makeText(SignUp.this, "You must accept the Terms and Conditions!", Toast.LENGTH_SHORT).show();
        }else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        user.sendEmailVerification();
                        mAuth.signOut();
                        Toast.makeText(SignUp.this, R.string.VerifyEmail, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SignUp.this, "No s'ha pogut verificar l'email", Toast.LENGTH_SHORT).show();
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