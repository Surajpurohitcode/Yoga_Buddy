package com.example.yogabuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.yogabuddy.databinding.ActivityMainBinding;
import com.example.yogabuddy.fragments.ChatBotFragment;
import com.example.yogabuddy.fragments.DailyTaskFragment;
import com.example.yogabuddy.fragments.HomeFragment;
import com.example.yogabuddy.fragments.MeditationFragment;
import com.example.yogabuddy.fragments.MyProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        getSupportFragmentManager().beginTransaction().add(R.id.frame_container_main,new HomeFragment()).commit();

       binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if (item.getItemId() == R.id.home)
               {
                   getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,new HomeFragment()).commit();
               }
               if (item.getItemId() == R.id.task)
               {
                   getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,new DailyTaskFragment()).commit();
               }
               if (item.getItemId() == R.id.meditation)
               {
                   getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,new MeditationFragment()).commit();
               }

               if (item.getItemId() == R.id.profile)
               {
                   getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,new MyProfileFragment()).commit();
               }
               if (item.getItemId() == R.id.chatbot)
               {
                   getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,new ChatBotFragment()).commit();
               }
               return true;
           }
       });

    }
}