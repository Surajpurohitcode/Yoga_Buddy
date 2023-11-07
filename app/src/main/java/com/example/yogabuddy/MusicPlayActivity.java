package com.example.yogabuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

import com.example.yogabuddy.databinding.ActivityMusicPlayBinding;

public class MusicPlayActivity extends AppCompatActivity {

    MediaPlayer mp;

    ActivityMusicPlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusicPlayBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String title = getIntent().getStringExtra("songtitle");
        int songImage = getIntent().getIntExtra("songimage",0);
        String songDes = getIntent().getStringExtra("songdes");
        String songTime = getIntent().getStringExtra("songtime");


        binding.songBanner.setImageResource(songImage);
        binding.songTitle.setText(title);
        binding.musicEndtime.setText(songTime);


        if (title.equals("TVARI - Tokyo Cafe"))
       {
           mp = MediaPlayer.create(this,R.raw.tokyocafe);
       }
       if (title.equals("Happy Day"))
       {
           mp = MediaPlayer.create(this,R.raw.happyday);
       }
        if (title.equals("One Last Time"))
        {
            mp = MediaPlayer.create(this,R.raw.onelasttime);
        }
        if (title.equals("Peaceful Garden"))
        {
            mp = MediaPlayer.create(this,R.raw.peacefullgardern);
        }
        if (title.equals("Enchanting Flute"))
        {
            mp = MediaPlayer.create(this,R.raw.enchantingflute);
        }

        mp.start();



        updateSeekbar(binding.musicProgress);

        binding.musicProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b)
                {
                    mp.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mp.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.start();
            }
        });

        binding.musicPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp.isPlaying())
                {
                    mp.pause();
                }
                else
                {
                    mp.start();
                }
            }
        });



    }

    private void updateSeekbar(SeekBar musicProgress) {
        new Thread(() -> {
            int i = mp.getCurrentPosition();
            while (i <= mp.getDuration()) {
                //updateSnackbarProgress(i);

                binding.musicProgress.setProgress(i);

                // Sleep for 100 milliseconds before updating the snackbar progress again.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }

            // When the video is finished playing, stop the loop and cancel the thread.
            //cancelThread();
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}