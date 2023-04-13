/*package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projecte.kaiju.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    }
}*/
package com.projecte.kaiju.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.projecte.kaiju.R;
import com.projecte.kaiju.helpers.ActivityHelper;

public class Login extends AppCompatActivity {

    protected String myClassTag = this.getClass().getSimpleName();

    EditText etName;
    EditText etPassword;
    private FirebaseAuth mAuth;

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
                            Toast.makeText(Login.this, "Authentication failed.",
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
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(Login.this, R.string.VerifyEmail,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}