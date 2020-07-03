package com.example.flat_organizer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AccountSettingActivity extends AppCompatActivity {
    TextView textViewEmail,textViewUserName;
    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_account_setting);
        //textViewEmail=(TextView) findViewById(R.id.textEmail);
        // setting up a tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // floating action button in right buttom corner
        FloatingActionButton fab = findViewById(R.id.fab);
        // sign out onclick listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                if (mAuth.getCurrentUser() == null){
                    Log.d("sign_out2", "sucess");
                    startActivities(new Intent[]{new Intent(getApplicationContext(), LoginActivity.class)});
                } else {
                    Log.d("sign_out2", "logout failes, why though?");
                }
            }
        });
        FirebaseUser currentUser = mAuth.getCurrentUser();
        final String email = currentUser.getEmail();
        //CollectionReference citiesRef = db.collection("users");
        //Query query = citiesRef.whereEqualTo("email", email);
        //final String[] username = new String[1];//need to learn this


    /*    DocumentReference docRef = db.collection("users").document("SF");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);

        //textViewUserName.setText("username");
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //View header = LayoutInflater.from(this).inflate(R.layout.nav_header_account_setting, null);
        //navigationView.addHeaderView(header);

        Log.d("tag2", "teesttstttsteststst ");
        db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override

                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("tag1", document.getId() + " => " + document.getData());
                                String username = document.getString("username");

                                View navigation_header_view = navigationView.getHeaderView(0);
                                textViewEmail = navigation_header_view.findViewById(R.id.textEmail);
                                textViewUserName = navigation_header_view.findViewById(R.id.textUsername);

                                textViewEmail.setText(email);
                                Log.d("username", String.valueOf(username==""));
                                textViewUserName.setText(username);
                                //textViewUserName.setText("username[0]");

                            }
                        } else {
                            Log.d("tag2", "Error getting documents: ", task.getException());
                        }
                        //Log.d("username", username[0]);
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_setting, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
