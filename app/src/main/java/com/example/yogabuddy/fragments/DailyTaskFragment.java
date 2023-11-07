package com.example.yogabuddy.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yogabuddy.databinding.FragmentDailyTaskBinding;

public class DailyTaskFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepSensor;

    private int totalSteps = 0;
    private int previousSteps = 0;

    private FragmentDailyTaskBinding binding;

    private Handler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = getActivity().getSystemService(SensorManager.class);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        // Register the sensor listener
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Create a handler to update the UI in the onSensorChanged() method
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDailyTaskBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            // Calculate the current steps
            int currentSteps = (int) sensorEvent.values[0];

            // Post a runnable to the handler to update the UI on the main thread
            handler.post(new Runnable() {
                @Override
                public void run() {
                    binding.currentSteps.setText(String.valueOf(currentSteps));
                    binding.stepsProgress.setProgress(currentSteps);
                }
            });

            // Save the current steps
            previousSteps = currentSteps;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Unregister the sensor listener
        sensorManager.unregisterListener(this);

        totalSteps = 0;

        binding.stepsProgress.setProgress(totalSteps);
        binding.currentSteps.setText("0");

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("step_count", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("previous_steps", previousSteps);
        editor.apply();


    }
}
