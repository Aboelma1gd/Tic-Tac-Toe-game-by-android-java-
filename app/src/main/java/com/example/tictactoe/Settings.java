package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // Initialize toggle buttons
         Switch musicSwitch = findViewById(R.id.switch_music);
         Switch soundSwitch = findViewById(R.id.switch_sound);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Set initial states from SharedPreferences
        musicSwitch.setChecked(sharedPreferences.getBoolean("music_on", true));
        soundSwitch.setChecked(sharedPreferences.getBoolean("sound_on", true));

        // Set listeners to save preferences
        musicSwitch.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            sharedPreferences.edit().putBoolean("music_on", isChecked).apply();
        });

        soundSwitch.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            sharedPreferences.edit().putBoolean("sound_on", isChecked).apply();
        });
    }
}