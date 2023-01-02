package com.example.digitalbrochureapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitalbrochureapp.client.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_CALL_LOG = 1;
    TextView username;
    TextView password;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("client")){
                    Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(myIntent);
                }
                if(username.getText().toString().equals("admin")){
                    Intent myIntent = new Intent(getBaseContext(), com.example.digitalbrochureapp.admin.MainActivity.class);
                    startActivity(myIntent);
                }

                Log.d("balugaw", "hadouken pdf"+findViewById(R.id.aaaaa));
                Log.d("balugaw", "hadouken "+findViewById(R.id.username));
                Log.d("balugaw", "hadouken "+findViewById(R.id.brochureTitle));


            }
        });







    }


}