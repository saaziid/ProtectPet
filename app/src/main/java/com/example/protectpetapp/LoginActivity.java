package com.example.protectpetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button registerButton;

    // for Login page access
    private EditText emailInput, passwordInput;
    private Button loginButton;

    // firebase email and password authentication
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    // for forgot password and reset
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // for Login page access
        registerButton = (Button) findViewById(R.id.LoginSignup);
        registerButton.setOnClickListener(this);
        loginButton = (Button) findViewById(R.id.LoginLoginButton);
        loginButton.setOnClickListener(this);

        emailInput = (EditText) findViewById(R.id.LoginEmailorPhoneInput);
        passwordInput = (EditText) findViewById(R.id.LoginPasswordInput);

        progressBar = (ProgressBar) findViewById(R.id.LoginProgressBar);

        // initialize the FirebaseAuth instance or variable
        mAuth = FirebaseAuth.getInstance();

        // for forgot password and reset
        forgotPassword = (TextView) findViewById(R.id.ForgotPassword);
        forgotPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // SignUp button
            case R.id.LoginSignup:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            // login button
            case R.id.LoginLoginButton:
                userLogin();
                break;
            // for forgot password button
            case R.id.ForgotPassword:
                // redirect to resetting page
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;

        }
    }

    private void userLogin() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

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

        if (password.isEmpty()) {
            passwordInput.setError("Name is required!");
            passwordInput.requestFocus();
            return;
        }
        // check if the password is less than 6 characters
        if (password.length() < 6){
            passwordInput.setError("Minimum password length should be 6 characters!");
            passwordInput.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                    if (user.isEmailVerified()){
                        // redirect to user home
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Check your email to verify your account!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


    }
}