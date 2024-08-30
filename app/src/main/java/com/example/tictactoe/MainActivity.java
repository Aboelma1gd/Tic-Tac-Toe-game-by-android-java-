package com.example.tictactoe;

import static com.example.tictactoe.Choose_game.soundEffect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.preference.PreferenceManager;

import android.widget.ImageView;
import com.example.tictactoe.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private final List<int[]>  winningPositions = new ArrayList<>();
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0}; // 9 zero
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        winningPositions.add(new int[]{0, 1, 2});
        winningPositions.add(new int[]{3, 4, 5});
        winningPositions.add(new int[]{6, 7, 8});
        winningPositions.add(new int[]{0, 3, 6});
        winningPositions.add(new int[]{1, 4, 7});
        winningPositions.add(new int[]{2, 5, 8});
        winningPositions.add(new int[]{2, 4, 6});
        winningPositions.add(new int[]{0, 4, 8});

        String getPlayerOneName = getIntent().getStringExtra("playerOne");
        String getPlayerTwoName = getIntent().getStringExtra("playerTwo");
        binding.playerOneName.setText(getPlayerOneName);
        binding.playerTwoName.setText(getPlayerTwoName);

        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.image1.setOnClickListener(view -> {
            if (isBoxSelectable(0)) {
                performAction(binding.image1, 0);
            }
        });
        binding.image2.setOnClickListener(view -> {
            if (isBoxSelectable(1)) {
                performAction(binding.image2, 1);
            }
        });
        binding.image3.setOnClickListener(view -> {
            if (isBoxSelectable(2)) {
                performAction(binding.image3, 2);
            }
        });
        binding.image4.setOnClickListener(view -> {
            if (isBoxSelectable(3)) {
                performAction(binding.image4, 3);
            }
        });
        binding.image5.setOnClickListener(view -> {
            if (isBoxSelectable(4)) {
                performAction(binding.image5, 4);
            }
        });
        binding.image6.setOnClickListener(view -> {
            if (isBoxSelectable(5)) {
                performAction(binding.image6, 5);
            }
        });
        binding.image7.setOnClickListener(view -> {
            if (isBoxSelectable(6)) {
                performAction(binding.image7, 6);
            }
        });
        binding.image8.setOnClickListener(view -> {
            if (isBoxSelectable(7)) {
                performAction(binding.image8, 7);
            }
        });
        binding.image9.setOnClickListener(view -> {
            if (isBoxSelectable(8)) {
                performAction(binding.image9, 8);
            }
        });
    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        // Play sound effect when a box is selected
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isSoundOn = sharedPreferences.getBoolean("sound_on", true);
        if (isSoundOn && soundEffect != null) {
            if (soundEffect.isPlaying()) {
                soundEffect.seekTo(0); // Reset to the start if it's already playing
            }
            soundEffect.start();
        }

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);
            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, binding.playerOneName.getText().toString()
                        + " is a Winner!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if (totalSelectedBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.oimage);
            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, binding.playerTwoName.getText().toString()
                        + " is a Winner!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if (totalSelectedBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box);
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean checkResults() {
        for (int[] combination : winningPositions) {
            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0; // Return true if the box is not selected
    }

    public void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}; // Reset to 9 zeros
        playerTurn = 1;
        totalSelectedBoxes = 1;
        binding.image1.setImageResource(R.drawable.white_box);
        binding.image2.setImageResource(R.drawable.white_box);
        binding.image3.setImageResource(R.drawable.white_box);
        binding.image4.setImageResource(R.drawable.white_box);
        binding.image5.setImageResource(R.drawable.white_box);
        binding.image6.setImageResource(R.drawable.white_box);
        binding.image7.setImageResource(R.drawable.white_box);
        binding.image8.setImageResource(R.drawable.white_box);
        binding.image9.setImageResource(R.drawable.white_box);
    }
}
