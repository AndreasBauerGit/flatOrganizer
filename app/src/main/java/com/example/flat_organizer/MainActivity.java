package com.example.flat_organizer;

import android.content.Intent;
import android.os.Bundle;

import com.example.flat_organizer.ui.main.CustomViewPager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flat_organizer.ui.main.SectionsPagerAdapter;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView textViewtest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        CustomViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addAdapterRef(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        textViewtest=findViewById(R.id.textViewtest);
        tabs.setupWithViewPager(viewPager);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Button accountSettingButton=findViewById(R.id.accountSettingButton);
        Log.d("accountSettingButton","accountSettingButtonfound");
        accountSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(getApplicationContext(), AccountSettingActivity.class)});
            }
        });
        Button databaseTestButton=findViewById(R.id.databaseTestButton);
        Log.d("databaseTestButton","databaseTestButtonfound");
        FirebaseUser currentUser=mAuth.getCurrentUser();
        String email=currentUser.getEmail();
        textViewtest.setText(email);//working
        databaseTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("databaseTestButton","databaseTestButtonclicked");
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("first", "Ada");
                user.put("last", "Lovelace");
                user.put("born", 1815);

                // Add a new document with a generated ID
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

            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);//what's this?//It is the email button on the bottom-right!
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}