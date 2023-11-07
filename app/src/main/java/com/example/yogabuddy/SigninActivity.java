package com.example.yogabuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yogabuddy.databinding.ActivitySigninBinding;
import com.example.yogabuddy.fragments.RegisterFragment;
import com.example.yogabuddy.fragments.SigninFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {


    @Override
    public void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySigninBinding binding = ActivitySigninBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportFragmentManager().beginTransaction().add(R.id.framelayout_sign_in,new SigninFragment()).commit();

       binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               int tabID = tab.getPosition();
               switch (tabID)
               {
                   case 0:
                       binding.frameTitle.setText("Sign In");
                       getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_sign_in,new SigninFragment()).commit();
                       break;
                   case 1:
                       binding.frameTitle.setText("Register");
                       getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_sign_in,new RegisterFragment()).commit();
                       break;


               }
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });

    }
}