package com.example.yogabuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.yogabuddy.adpter.SongsPlayListAdpter;
import com.example.yogabuddy.databinding.ActivityPlayListBinding;
import com.example.yogabuddy.model.SongsModel;

import java.util.ArrayList;

public class PlayListActivity extends AppCompatActivity {

    ArrayList<SongsModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPlayListBinding binding = ActivityPlayListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String title = getIntent().getStringExtra("title");

        binding.toolbar.setTitle(title);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.songsList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        SongsPlayListAdpter adpter = new SongsPlayListAdpter(arrayList,this);
        binding.songsList.setAdapter(adpter);

        if (title.equals("Stress Relief"))
        {
           arrayList.add(new SongsModel("TVARI - Tokyo Cafe","Reduce fea, anxiety and stress","2:33",R.drawable.tokyocafe));
           arrayList.add(new SongsModel("Happy Day","Reduce fea, anxiety and stress","1:48",R.drawable.happyday));
           arrayList.add(new SongsModel("One Last Time","Reduce fea, anxiety and stress","2:40",R.drawable.midnightrelaxation));
           arrayList.add(new SongsModel("Peaceful Garden","Healing Light Piano for meditation","2:59",R.drawable.joggingcycling));
           arrayList.add(new SongsModel("Enchanting Flute","Natural sense of creativity","3:17",R.drawable.kirshna_flute));
        }



    }
}