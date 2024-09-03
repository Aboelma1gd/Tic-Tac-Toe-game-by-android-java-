package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import controller.DatabaseHandler;
import controller.User;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button login,gotoReg;
    private DatabaseHandler db;

    private String Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db=new DatabaseHandler(this);

        email=findViewById(R.id.login_email_et);
        password=findViewById(R.id.login_password_et);
        login=findViewById(R.id.login_btn);
        gotoReg=findViewById(R.id.goto_signUP_btn);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Email=email.getText().toString();
                 Password=password.getText().toString();

                if (Email.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if email is empty
                }
                if (Password.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if password is empty
                }
                if (db.checkUser(Email,Password)) {
                    Intent intent=new Intent(Login.this,Choose_game.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Login.this, "your data not found", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


        gotoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });



    }
}