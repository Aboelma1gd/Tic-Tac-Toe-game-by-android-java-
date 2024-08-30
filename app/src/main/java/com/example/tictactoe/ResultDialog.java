package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultDialog extends Dialog {
    private final String message;
    private final AppCompatActivity activity;

    public ResultDialog(@NonNull Context context, String message, AppCompatActivity activity) {
        super(context);
        this.message = message;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dialog);
        TextView messageText = findViewById(R.id.messageText);
        Button startAgainButton = findViewById(R.id.startAgainButton);
        messageText.setText(message);

        startAgainButton.setOnClickListener(view -> {
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).restartMatch();
            } else if (activity instanceof WithAI) {
                ((WithAI) activity).restartMatch();
            }
            dismiss();
        });
    }
}
