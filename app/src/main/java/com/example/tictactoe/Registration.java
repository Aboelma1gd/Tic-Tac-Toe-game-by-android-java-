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

public class Registration extends AppCompatActivity {
    EditText regEmail,regPassword,conPassword;
    Button create,gotoLogin;
    private DatabaseHandler db;
    private String Email,Password,regPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db=new DatabaseHandler(this);

        regEmail=findViewById(R.id.reg_email_et);
        regPassword=findViewById(R.id.reg_password_et);
        conPassword=findViewById(R.id.reg_confirm_password_et);
        create=findViewById(R.id.reg_btn);
        gotoLogin=findViewById(R.id.goto_login_btn);




        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Email=regEmail.getText().toString();
                 Password=regPassword.getText().toString();
                 regPass=conPassword.getText().toString();

                if (Email.isEmpty()) {
                    Toast.makeText(Registration.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.isEmpty()) {
                    Toast.makeText(Registration.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!(Password.equals(regPass))) {
                    Toast.makeText(Registration.this, "Please enter same password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (db.insertUser(new User(Email, Password))) {
                    Intent intent = new Intent(Registration.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Registration.this, "This email already has an account.", Toast.LENGTH_LONG).show();
                }


            }
        });

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,Login.class);
                startActivity(intent);
            }
        });

    }
}