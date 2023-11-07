package com.example.yogabuddy.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yogabuddy.ExerciseDetailsActivity;
import com.example.yogabuddy.R;
import com.example.yogabuddy.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    FirebaseAuth mAuth;

    FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater,container,false);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = database.getReference();

        databaseReference.child("users").child(mAuth.getCurrentUser().getUid()).child("username").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful())
                {
                    String uername = task.getResult().getValue().toString();
                    binding.usernameHomeFragment.setText(uername);
                }
                else
                {
                    SharedPreferences prefs = getActivity().getSharedPreferences("user_data", MODE_PRIVATE);
                    String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
                    int idName = prefs.getInt("idName", 0); //0 is the default value.

                    binding.usernameHomeFragment.setText(name);

                }
            }
        });


        binding.basicYogaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExerciseDetailsActivity.class);
                intent.putExtra("exerciseName","Basic Yoga Exercise");
                startActivity(intent);
            }
        });

        binding.fullBodyYogaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExerciseDetailsActivity.class);
                intent.putExtra("exerciseName","Full Body Exercise");
                startActivity(intent);
            }
        });

        binding.upperBodyYogaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExerciseDetailsActivity.class);
                intent.putExtra("exerciseName","Upper Body");
                startActivity(intent);
            }
        });

        binding.lowerBodyYogaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExerciseDetailsActivity.class);
                intent.putExtra("exerciseName","Lower body");
                startActivity(intent);
            }
        });



        return binding.getRoot();
    }
}