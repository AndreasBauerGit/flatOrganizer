package com.example.flatOrganizer.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.flatOrganizer.R;
import com.google.firebase.auth.FirebaseAuth;

// Work in progress on AccountSetting
public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private FirebaseAuth mAuth;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // sign out button
        Log.d("sign_out1", "test");

        // ########## get this button to work ########### ---> fragment might be implemented incorrectly
        root.findViewById(R.id.signoutButton).setOnClickListener(this);

        return root;

    }

    @Override
    public void onClick(View v) {
        Log.d("sign_out1", "test2");
        Log.d("sign_out1", mAuth.getCurrentUser().getEmail());
        mAuth.signOut();
        if (mAuth.getCurrentUser() == null){
            Log.d("sign_out2", "sucess");
        } else {
            Log.d("sign_out2", mAuth.getCurrentUser().getEmail());
        }


    }
}
