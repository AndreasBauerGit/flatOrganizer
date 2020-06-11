package com.example.flat_organizer;

import android.content.Intent;
import android.os.Bundle;

import com.example.flat_organizer.ui.main.CustomViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.flat_organizer.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        CustomViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addAdapterRef(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refRecord = database.getReference("flatorganizer/groups/admins");

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
        databaseTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("databaseTestButton","databaseTestButtonclicked");
                //String test="test";
                //String id=refRecord.push().getKey();
                refRecord.setValue("test");

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