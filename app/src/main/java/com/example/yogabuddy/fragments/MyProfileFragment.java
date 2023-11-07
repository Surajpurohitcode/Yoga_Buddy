package com.example.yogabuddy.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yogabuddy.R;
import com.example.yogabuddy.databinding.FragmentMyProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyProfileFragment extends Fragment {

    FirebaseAuth mAuth;

    FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentMyProfileBinding binding = FragmentMyProfileBinding.inflate(inflater,container,false);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = database.getReference();


        databaseReference.child("users").child(mAuth.getCurrentUser().getUid()).child("username").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful())
                {
                    String uername = task.getResult().getValue().toString();
                    binding.username.setText(uername);
                }

            }
        });

        databaseReference.child("users").child(mAuth.getCurrentUser().getUid()).child("email").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful())
                {
                    String email = task.getResult().getValue().toString();
                    binding.userEmail.setText(email);
                }

            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("step_count", MODE_PRIVATE);
        int totalSeps =  sharedPreferences.getInt("previous_steps",0);

        binding.totalSteps.setText(Integer.toString(totalSeps));


        return binding.getRoot();
    }
}