package com.example.flatOrganizer;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

//import com.example.flatOrganizer.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.widget.Toast;

import com.example.flatOrganizer.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail,editTextPassword;
    ProgressBar progressBar;
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    public boolean auto_login = true;   // skipping login, only for development!
    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // avoid auto login when sign out button is used
        Intent intent = getIntent();
        if (intent.getStringExtra("source") != null){
            if (intent.getStringExtra("source").equals("AccountSettingActivity")){
                auto_login = AccountSettingActivity.auto_login;
            }
        }

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        // try automatic login if user is signed in already or debug options are on
        attemptAppStart(currentUser, auto_login);

        // create layout and connect to listeners
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.dontHaveAccount).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // switch to register page
            case R.id.dontHaveAccount:
                startActivities(new Intent[]{new Intent(this, SignupActivity.class)});
                break;
            // attempt login
            case R.id.loginButton:
                getCredentials();
                if ((email != null) && (password != null)) {
                    userLogin(email, password);
                }
                break;

        }
    }

    public void attemptAppStart(FirebaseUser user, boolean auto_login){

        if ((user == null) && auto_login){
            Log.d("autosign in", "true");
            userLogin("andreas@gmx.de", "111111");
        }
        if ((user != null)){
            // some logging
            Log.d("user info", user.getEmail());
            Log.d("userid",  mAuth.getCurrentUser().getUid().toString());
            startActivities(new Intent[]{new Intent(getApplicationContext(),MainActivity.class)});
            }
        }



    // read the login credential, check for validity, print message if problematic
    // if any problems occure both email and password are set to null
    private void getCredentials(){
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.loading);

        // TODO add check if user name exists
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            setCredentialsNull();
            return;
        }
        //if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        //   editTextEmail.setError("Please Enter a valid email");
        //   editTextEmail.requestFocus();
        //  return;
        //}
        // TODO merge with login check in SignupActivity
        int min_pw_length = 1;

        if(password.isEmpty() && (min_pw_length != 0)) {
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            setCredentialsNull();
            return;
        }

        if (password.length() < min_pw_length){
            editTextPassword.setError(String.format("Minimum length of password should be %d", min_pw_length));
            editTextPassword.requestFocus();
            setCredentialsNull();
            return;
        }

    }

    private void setCredentialsNull(){
        email = null;
        password = null;
    }
    private void setPbarVisibility(int visibility){
        if (progressBar != null){
            progressBar.setVisibility(visibility);
        }

    }


    // login to firebase with string
    private void userLogin(String email, String password){
        setPbarVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                setPbarVisibility(View.GONE);
                if (task.isSuccessful()){
                    Log.d("login","login successfully");
                    Log.d("login","password");
                    Log.d("login","email");
                    currentUser = mAuth.getCurrentUser();
                    attemptAppStart(currentUser, false);  // synthax allows to use attemptLogin also for "checking if already signed up"


                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    //attemptAppStart(null, false);
                }
            }
        });
    }
}
