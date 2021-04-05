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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    // for Sign Up page access
    private Button registerButton;
    private EditText nameInput, emailInput, usernameInput, phoneInput, passwordInput, confirmPasswordInput;
    private ProgressBar progressBar;

    // firebase email and password authentication
    private FirebaseAuth mAuth;

    static int i;
    final static int SIZE = 100;
    static String petID []  = new String[SIZE];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // initialize the FirebaseAuth instance or variable
        mAuth = FirebaseAuth.getInstance();

        // for Sign Up page access
        registerButton = (Button) findViewById(R.id.RegisterRegisterButton);
        registerButton.setOnClickListener(this);

        nameInput = (EditText) findViewById(R.id.RegisterNameInput);
        emailInput = (EditText) findViewById(R.id.RegisterEmailInput);
        usernameInput = (EditText) findViewById(R.id.RegisterUsernameInput);
        phoneInput = (EditText) findViewById(R.id.RegisterPhoneInput);
        passwordInput = (EditText) findViewById(R.id.RegisterPasswordInput);
        confirmPasswordInput = (EditText) findViewById(R.id.RegisterConfirmPasswordInput);

        progressBar = (ProgressBar) findViewById(R.id.RegisterProgressBar);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Register button
            case R.id.RegisterRegisterButton:
                registerAccount();
                break;
            // login button
            case R.id.RegisterNameInput:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    private void registerAccount() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String username = usernameInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();
        String pets = "none";
        for (int k=0; k<SIZE; k++){
            petID[k] = "none";
        }

        // warning message if any input box is empty
        if (name.isEmpty()) {
            nameInput.setError("Name is required!");
            nameInput.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailInput.setError("Email is required!");
            emailInput.requestFocus();
            return;
        }
        // check if the user has provided a valid email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailInput.setError("Please provide a valid email!");
            emailInput.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            usernameInput.setError("Username is required!");
            usernameInput.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            phoneInput.setError("Phone number is required!");
            phoneInput.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordInput.setError("Password is required!");
            passwordInput.requestFocus();
            return;
        }
        // check if the password is less than 6 characters
        if (password.length() < 6){
            passwordInput.setError("Minimum password length should be 6 characters!");
            passwordInput.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordInput.setError("confirmPassword is required!");
            confirmPasswordInput.requestFocus();
            return;
        }
        // check if the user has provided the same password
        if (!confirmPassword.equals(password)){
            confirmPasswordInput.setError("Password didn't match!");
            confirmPasswordInput.requestFocus();
            return;
        }

        Query usernameQuery = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("username").equalTo(username);
        usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount()>0){
                    usernameInput.setError("Username is already taken!");
                    usernameInput.requestFocus();
                }

                else{
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override                                         //checking if the user has been registered
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Users user = new Users(name, email, username, phone, password, pets);

                                // connecting to realtime database
                                FirebaseDatabase.getInstance().getReference("Users")
                                        // (FirebaseAuth.getInstance().getCurrentUser().getUid())___this will return the id of the user registered
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);

                                            // redirect user to login page
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        }
                                        else{
                                            Toast.makeText(RegisterActivity.this, "Failed to register! Try Again!", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Failed to register! Try Again!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }

    // for adding pet id in pets in user table
    public static void updatePet(String id) {
        i++;
        petID[i-1] = id;

        for (int j=0; j<i; j++) {
            FirebaseDatabase.getInstance().getReference("Users")
                    // (FirebaseAuth.getInstance().getCurrentUser().getUid())___this will return the id of the user registered
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("pets").child("petid"+i).setValue(petID[j]);
        }
    }

}