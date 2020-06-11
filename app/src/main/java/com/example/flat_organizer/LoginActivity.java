package com.example.flat_organizer;
import java.util.List;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;

//import com.example.flat_organizer.ui.login.LoginActivity;
import com.example.flat_organizer.LoginActivity;
import com.example.flat_organizer.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flat_organizer.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail,editTextPassword;
    ProgressBar progressBar;
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Log.d("user info", currentUser.getEmail());
            Log.d("user info", currentUser.getPhoneNumber());
        }
        attemptLogin(currentUser);
        //updateUI(currentUser);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        progressBar=findViewById(R.id.loading);


        Log.d("login1","place1");
        findViewById(R.id.loginButton).setOnClickListener(this);
        // text bellow Eddit texts
        findViewById(R.id.dontHaveAccount).setOnClickListener(this);
        Log.d("login2","place2");

    }

    private void userLogin(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Email is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Log.d("login","login successfully");
                    FirebaseUser user = mAuth.getCurrentUser();
                    attemptLogin(user);  // synthax allows to use attemptLogin also for "checking if already signed up"


                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    attemptLogin(null);
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dontHaveAccount:
                startActivities(new Intent[]{new Intent(this, SignupActivity.class)});
                break;
            case R.id.loginButton:
                userLogin();
                break;

        }
    }
    public void attemptLogin(FirebaseUser u){
        if (u != null){
            startActivities(new Intent[]{new Intent(getApplicationContext(),MainActivity.class)});

        }



    }
}
// FirebaseAuth.getInstance().signOut(); to sign out