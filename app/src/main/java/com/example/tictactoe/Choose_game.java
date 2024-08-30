package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Choose_game extends AppCompatActivity {
    Button Ai,friend;


    ImageButton myImageButton;

    public MediaPlayer backgroundMusic;
   public static MediaPlayer soundEffect;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);
        Ai=findViewById(R.id.withai_btn);
        friend=findViewById(R.id.withfriend_btn);
        Ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choose_game.this,AiGetName.class);
                startActivity(intent);
            }
        });
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choose_game.this,AddPlayers.class);
                startActivity(intent);
            }
        });

        myImageButton = findViewById(R.id.set_btn);
        myImageButton.setOnClickListener(v -> {
            Intent i = new Intent(Choose_game.this, Settings.class);
            startActivity(i);
            Log.d("MainActivity", "ImageButton clicked.");
        });

        // Initialize and play background music
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isMusicOn = sharedPreferences.getBoolean("music_on", true);
        //boolean isSoundOn = sharedPreferences.getBoolean("sound_on", true);

        if (backgroundMusic == null) {
            backgroundMusic = MediaPlayer.create(this, R.raw.background);
            backgroundMusic.setLooping(true);
        }

        if (isMusicOn && !backgroundMusic.isPlaying()) {
            backgroundMusic.start();
        } else if (!isMusicOn && backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }

        if (soundEffect == null) {
            soundEffect = MediaPlayer.create(this, R.raw.click);
        }



    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isMusicOn = sharedPreferences.getBoolean("music_on", true);
        boolean isSoundOn = sharedPreferences.getBoolean("sound_on", true);

        if (isMusicOn && backgroundMusic != null && !backgroundMusic.isPlaying()) {
            backgroundMusic.start();
        } else if (!isMusicOn && backgroundMusic != null && backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }

        if (isSoundOn && soundEffect != null && !soundEffect.isPlaying()) {
            soundEffect.start();
        } else if (!isSoundOn && soundEffect != null && soundEffect.isPlaying()) {
            soundEffect.pause();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (backgroundMusic != null) {
            backgroundMusic.release();
            backgroundMusic = null;
        }

        if (soundEffect != null) {
            soundEffect.release();
            soundEffect = null;
        }
    }
}