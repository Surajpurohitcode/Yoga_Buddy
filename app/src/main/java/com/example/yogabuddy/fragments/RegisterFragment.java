package com.example.yogabuddy.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.yogabuddy.model.UserModel;
import com.example.yogabuddy.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterFragment extends Fragment {

    FirebaseAuth mAuth;

    FirebaseDatabase database;

    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentRegisterBinding binding  = FragmentRegisterBinding.inflate(inflater,container,false);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = binding.signUpUsernameInput.getText().toString();
                String useremail = binding.signUpEmailInput.getText().toString();
                String userpassword = binding.signUpPasswordInput.getText().toString();
                
                
                if (userName.isEmpty() && useremail.isEmpty() && userpassword.isEmpty())
                {
                    Toast.makeText(getActivity(), "Please fill all required fields!", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("user_data", MODE_PRIVATE).edit();
                    editor.putString("name", userName);
                    editor.putInt("idName", 12);
                    editor.apply();

                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);

                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                    String formattedDate = df.format(c);

                    dialog = new Dialog(getActivity());
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.loading_view);
                    dialog.show();

                    userSignup(useremail,userpassword,userName,formattedDate);


                }

            }
        });

        return binding.getRoot();
    }

    private void userSignup(String email,String password,String username,String joinDate) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //dialog.dismiss();
                            uploadUserData(username,email,joinDate);
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void uploadUserData(String username,String email,String joinDate) {

        DatabaseReference databaseReference = database.getReference();

        UserModel model = new UserModel(username,email,joinDate);

        databaseReference.child("users").child(mAuth.getCurrentUser().getUid()).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
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
                    Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

    }
}