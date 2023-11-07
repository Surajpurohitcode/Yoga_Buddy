package com.example.yogabuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.example.yogabuddy.databinding.ActivityExerciseBinding;

public class ExerciseActivity extends AppCompatActivity {

    ActivityExerciseBinding binding;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExerciseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String yogaTitle = getIntent().getStringExtra("yogaTitle");
        String yogaTime = getIntent().getStringExtra("yogaMinutes");
        String yogaInfo = getIntent().getStringExtra("yoga_info");

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.yogaTitle.setText(yogaTitle);
        binding.yogaInfo.setText(yogaInfo);

        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(binding.videoPlayerView);

        if (yogaTitle.equals("Cat-Cow Pose"))
        {
             uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.catcow_video);
        }
        if (yogaTitle.equals("Downward-Facing Dog"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.downward_facing_dog);
        }
        if (yogaTitle.equals("Child's Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.child_poses);
        }
        if (yogaTitle.equals("Mountain Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mountain_poses);
        }

        if (yogaTitle.equals("Tree Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tree_poses);
        }

        if (yogaTitle.equals("Bridge Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bridge_pose);
        }

        if (yogaTitle.equals("Warrior II Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.warrior_ll);
        }

        if (yogaTitle.equals("Triangle Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.triangle_pose);
        }

        if (yogaTitle.equals("Legs-Up-the-Wall Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.legs_up_wall);
        }

        if (yogaTitle.equals("Corpse Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.corpse_pose);
        }
        if (yogaTitle.equals("Chaturanga"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.plank);
        }
        if (yogaTitle.equals("Upward-Facing Dog"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.upword_facing_dog);
        }
        if (yogaTitle.equals("Standing Forward Bend"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.standing_forward_bend);
        }
        if (yogaTitle.equals("Wide-Legged Forward Bend"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.wideleggedforwardbend);
        }
        if (yogaTitle.equals("Warrior I Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.warriorfirstpose);
        }
        if (yogaTitle.equals("Chair Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.chairpose);
        }
        if (yogaTitle.equals("Chair Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.chairpose);
        }
        if (yogaTitle.equals("Pigeon Pose"))
        {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pigeonpose);
        }


        binding.videoPlayerView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });


        binding.yogaVideoPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.videoPlayerView.setVideoURI(uri);
                binding.videoPlayerView.start();

                new Thread(() -> {
                    int i = 0;
                    binding.yogaVideoPlayBtn.setClickable(false);
                    while (i <= 120) {
                        updateSnackbarProgress(i);

                        // Sleep for 100 milliseconds before updating the snackbar progress again.
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }

                    // When the video is finished playing, stop the loop and cancel the thread.
                    cancelThread();
                }).start();

            }
        });

    }

    private void updateSnackbarProgress(int i) {


        binding.yogaProgress.setMax(120);

        // Update the snackbar progress.
        binding.yogaProgress.setProgress(i);
        if (binding.yogaProgress.getProgress() == binding.yogaProgress.getMax())
        {
            finish();
        }
    }

    private void cancelThread() {
        // Stop the loop.
        boolean stopLoop = true;

        // Cancel the thread.
        Thread.interrupted();
    }

}