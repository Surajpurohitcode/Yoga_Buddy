package com.example.yogabuddy.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yogabuddy.MainActivity;
import com.example.yogabuddy.R;
import com.example.yogabuddy.databinding.FragmentSigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.function.IntFunction;

public class SigninFragment extends Fragment {

    FirebaseAuth mAuth;

    FirebaseDatabase database;

    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSigninBinding binding = FragmentSigninBinding.inflate(inflater,container,false);


        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();


        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signInEmailInput.getText().toString();
                String password = binding.signInPasswordInput.getText().toString();

                if (email.isEmpty() && password.isEmpty())
                {
                    Toast.makeText(getActivity(), "Please fill all required fields!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dialog = new Dialog(getActivity());
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.loading_view);
                    dialog.show();

                    userSignin(email,password);

                }

            }
        });




        return binding.getRoot();

    }

    private void userSignin(String email,String password) {

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "Welcome to yoga buddy", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                            dialog.dismiss();
                        }
                    },5000);
                }
                else
                {
                    Toast.makeText(getActivity(), "Check your email and password!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

    }
}