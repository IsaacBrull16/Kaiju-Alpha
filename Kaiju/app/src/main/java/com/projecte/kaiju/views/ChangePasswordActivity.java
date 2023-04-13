package com.projecte.kaiju.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.ActivityHelper;

public class ChangePasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    EditText emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.eMailTextchangev);

        findViewById(R.id.returnCButton).setOnClickListener(v -> returnbutton());
        findViewById(R.id.sendButton).setOnClickListener(v -> changePswd());
    }

    public void changePswd(){
        String email = emailText.getText().toString();

        ActivityHelper.hideKeyboard(this);

        if (email.equals("")){
            Toast.makeText(this, "You must fill all the gaps!", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ChangePasswordActivity.this, "Email sent for changing password :)", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "Error with the EMail :(", Toast.LENGTH_SHORT).show();
                        emailText.setText("");
                    }
                }
            });
        }
    }
    private void returnbutton(){
        mAuth.signOut();
        finish();
    }
}