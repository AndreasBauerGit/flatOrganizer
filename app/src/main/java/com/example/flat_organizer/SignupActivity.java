package com.example.flat_organizer;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import android.content.Intent;
import android.media.MediaPlayer;
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
import com.example.flat_organizer.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flat_organizer.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail,editTextPassword,editTextUsername;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        editTextUsername=findViewById(R.id.username);
        progressBar=findViewById(R.id.loading);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("record");

        findViewById(R.id.signupButton).setOnClickListener(this);
        findViewById(R.id.alreadyhaveanaccount).setOnClickListener(this);
    }
    private void registerUser() {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        final String username=editTextUsername.getText().toString().trim();

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
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                    /*if (mAuth.getCurrentUser() == null){
                        Log.d("notsignin", "notsighin");
                        startActivities(new Intent[]{new Intent(getApplicationContext(), LoginActivity.class)});
                    }else {
                        Log.d("signin", "signin");
                    }*/
                    FirebaseUser currentUser=mAuth.getCurrentUser();
                    String email=currentUser.getEmail();

                    Map<String, Object> user = new HashMap<>();
                    user.put("username", username);
                    user.put("email",email);

                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("ID", "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("Error", "Error adding document", e);
                                }
                            });
                    startActivities(new Intent[]{new Intent(getApplicationContext(),MainActivity.class)});
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"This email is already registered.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signupButton:
                Log.d("Signup","SignupClicked");
                registerUser();
                break;
            case R.id.alreadyhaveanaccount:
                Log.d("alreadyhave","alreadyhaveanaccountClicked");
                startActivities(new Intent[]{new Intent(this, LoginActivity.class)});
                break;
        }

    }


}
