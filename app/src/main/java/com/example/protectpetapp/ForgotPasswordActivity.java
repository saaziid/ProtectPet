package com.example.protectpetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    // for forgot password and reset
    private EditText emailInput;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        // for forgot password and reset
        // initializing
        emailInput = (EditText) findViewById(R.id.ForgotPasswordEmailInput);

        resetPasswordButton = (Button) findViewById(R.id.ForgotPasswordResetButton);
        // resetPasswordButton.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.ForgotPasswordProgressBar);
        // initialize the FirebaseAuth instance or variable
        mAuth = FirebaseAuth.getInstance();


        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = emailInput.getText().toString().trim();

        // warning if any input box is empty
        if (email.isEmpty()) {
            emailInput.setError("Email is required!");
            emailInput.requestFocus();
            return;
        }
        // check if the user has provided a valid email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailInput.setError("Please enter a valid email!");
            emailInput.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Check your email to reset your password!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else {
                    Toast.makeText(ForgotPasswordActivity.this, "Try again! An error occcured!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}