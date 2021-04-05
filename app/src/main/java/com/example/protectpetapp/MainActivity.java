package com.example.protectpetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // for HomeScreen button access
    private Button registerNowButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // for HomeScreen button access
        registerNowButton = (Button) findViewById(R.id.MainRegisterButton);
        registerNowButton.setOnClickListener(this);
        loginButton = (Button) findViewById(R.id.MainLoginButton);
        loginButton.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            // Registernow button
            case R.id.MainRegisterButton:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            // login button
            case R.id.MainLoginButton:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

}