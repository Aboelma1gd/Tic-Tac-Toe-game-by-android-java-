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
    Button Ai, friend;
    ImageButton myImageButton;

    private MediaPlayer backgroundMusic;
    public static MediaPlayer soundEffect;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);

        myImageButton = findViewById(R.id.set_btn);
        Ai = findViewById(R.id.withai_btn);
        friend = findViewById(R.id.withfriend_btn);

        Ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose_game.this, AiGetName.class);
                startActivity(intent);
            }
        });
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose_game.this, AddPlayers.class);
                startActivity(intent);
            }
        });

        myImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Choose_game.this, Settings.class);
                startActivity(i);
                Log.d("Choose_game", "Settings button clicked.");
            }
        });

        // Initialize and play background music
        handleMusicPlayback();
        handleSoundEffectInitialization();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handleMusicPlayback();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayers();
    }

    private void handleMusicPlayback() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isMusicOn = sharedPreferences.getBoolean("music_on", true);

        if (backgroundMusic == null) {
            backgroundMusic = MediaPlayer.create(this, R.raw.background);
            backgroundMusic.setLooping(true);
        }

        // Only start music if it is not already playing
        if (isMusicOn && !backgroundMusic.isPlaying()) {
            backgroundMusic.start();
        } else if (!isMusicOn && backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }
    }

    private void handleSoundEffectInitialization() {
        if (soundEffect == null) {
            soundEffect = MediaPlayer.create(this, R.raw.click);
        }
    }

    private void releaseMediaPlayers() {
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

