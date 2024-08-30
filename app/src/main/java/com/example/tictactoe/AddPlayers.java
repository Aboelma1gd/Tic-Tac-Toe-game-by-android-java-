package com.example.tictactoe;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
public class AddPlayers extends AppCompatActivity {


    private EditText playerOne;
    private EditText playerTwo;
    private Button startGameButton;

    private ImageView backBtn;

    String PlayerOneName,PlayerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        backBtn=findViewById(R.id.back_btn);
         playerOne = findViewById(R.id.playerOne);
         playerTwo = findViewById(R.id.playerTwo);
         startGameButton = findViewById(R.id.startGameButton);


        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 PlayerOneName = playerOne.getText().toString();
                 PlayerTwoName = playerTwo.getText().toString();
                if (PlayerOneName.isEmpty() || PlayerTwoName.isEmpty()) {
                    Toast.makeText(AddPlayers.this, "Please enter player name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(AddPlayers.this, MainActivity.class);
                    intent.putExtra("playerOne", PlayerOneName);
                    intent.putExtra("playerTwo", PlayerTwoName);
                    startActivity(intent);
                }
            }
        });
        backBtn.setOnClickListener(v -> {
            Intent i = new Intent(AddPlayers.this, Choose_game.class);
            startActivity(i);
        });
    }
}