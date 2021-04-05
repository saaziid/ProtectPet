package com.example.protectpetapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NewPetActivity extends AppCompatActivity implements View.OnClickListener {

    // for new pet adding page access
    private ImageView imageInput, goBack, tick;
    private TextView addLocation;
    private EditText pnameInput, speciesInput, birthdayInput, genderInput, breedInput, foodHabitInput, statusInput, medicalHistoryInput;
    private ProgressBar progressBar;


    private static final int GALLERYPICK = 1;
    private Uri imageUri;

    // write to realtime database firebase
    private DatabaseReference mRef,mRefUser;
    // upload image to cloud storage firebase
    private StorageReference mStorageRef;

    private String downloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);


        // write to realtime database firebase
        mRef = FirebaseDatabase.getInstance().getReference().child("Pets");
        // upload image to cloud storage firebase
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Pet Images");

        //update user with pet id
        mRefUser = FirebaseDatabase.getInstance().getReference().child("Users");


        // for new pet adding page access // initializing
        goBack = (ImageView) findViewById(R.id.NewPetGoBack);
        goBack.setOnClickListener(this);
        tick = (ImageView) findViewById(R.id.NewPetTick);
        tick.setOnClickListener(this);

        imageInput = (ImageView) findViewById(R.id.NewPetImageInsert);
        imageInput.setOnClickListener(this);

        addLocation = (TextView) findViewById(R.id.NewPetAddLocation);
        addLocation.setOnClickListener(this);

        pnameInput = (EditText) findViewById(R.id.NewPetNameInput);
        speciesInput = (EditText) findViewById(R.id.NewPetSpeciesInput);
        birthdayInput = (EditText) findViewById(R.id.NewPetBirthdayInput);
        genderInput = (EditText) findViewById(R.id.NewPetGenderInput);
        breedInput = (EditText) findViewById(R.id.NewPetBreedInput);
        foodHabitInput = (EditText) findViewById(R.id.NewPetFoodHabitInput);
        statusInput = (EditText) findViewById(R.id.NewPetStatusInput);
        medicalHistoryInput = (EditText) findViewById(R.id.NewPetMedicalHistoryInput);

        progressBar = (ProgressBar) findViewById(R.id.NewPetProgressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // go back button
            case R.id.NewPetGoBack:
                startActivity(new Intent(this, MainActivity.class));
                break;

            // tick or done button
            case R.id.NewPetTick:
                addNewPet();
                break;
            // for inserting image
            case R.id.NewPetImageInsert:
                openGallery();
                break;
            // for adding location on map
            case R.id.NewPetAddLocation:
                // redirect to  page
                startActivity(new Intent(this, MainActivity.class));
                break;


        }
    }

    // for inserting image
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERYPICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GALLERYPICK && resultCode==RESULT_OK && data!=null) {
            imageUri = data.getData();
            // display it on screen
            imageInput.setImageURI(imageUri);

        }
    }

    // for adding the new pet
    private void addNewPet() {
        // generating unique key with time and date
        String saveCurrentDate, saveCurrentTime, randomValue;
        // String location = emailInput.getText().toString().trim();
        String pname = pnameInput.getText().toString().trim();
        String species = speciesInput.getText().toString().trim();
        String birthday = birthdayInput.getText().toString().trim();
        String gender = genderInput.getText().toString().trim();
        String breed = breedInput.getText().toString().trim();
        String foodHabit = foodHabitInput.getText().toString().trim();
        String status = statusInput.getText().toString().trim();
        String medicalHistory = medicalHistoryInput.getText().toString().trim();

        // warning if any input box is empty
        if (imageUri == null) {
            Toast.makeText(NewPetActivity.this, "Pet Photo!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pname.isEmpty()) {
            pnameInput.setError("Provide a pet name!");
            pnameInput.requestFocus();
            return;
        }

        if (species.isEmpty()) {
            speciesInput.setError("Provide species!");
            speciesInput.requestFocus();
            return;
        }

        if (birthday.isEmpty()) {
            birthdayInput.setError("Provide birthday!");
            birthdayInput.requestFocus();
            return;
        }

        if (gender.isEmpty()) {
            genderInput.setError("Provide gender!");
            genderInput.requestFocus();
            return;
        }

        if (breed.isEmpty()) {
            breedInput.setError("Provide breed!");
            breedInput.requestFocus();
            return;
        }

        if (foodHabit.isEmpty()) {
            foodHabitInput.setError("Provide food habit!");
            foodHabitInput.requestFocus();
            return;
        }

        if (status.isEmpty()) {
            statusInput.setError("Provide status!");
            statusInput.requestFocus();
            return;
        }

        if (medicalHistory.isEmpty()) {
            medicalHistoryInput.setError("Provide medical history!");
            medicalHistoryInput.requestFocus();
            return;
        }


        // generating unique key with time andd date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
        randomValue = saveCurrentDate + saveCurrentTime;

        // upload image to cloud storage firebase
        StorageReference ImageName = mStorageRef.child("image " + imageUri.getLastPathSegment() + randomValue + ".jpg");
        //mStorageRef.child("Pet Images").child(imageUri)

        progressBar.setVisibility(View.VISIBLE);
        ImageName.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(NewPetActivity.this, "Photo uploaded successfully!", Toast.LENGTH_SHORT).show();

                ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        savePetInfo(uri,pname,species,birthday,gender,breed,foodHabit,status,medicalHistory);
                    }
                });
            }
        });
    }

    private void savePetInfo(Uri uri, String pname, String species, String birthday, String gender, String breed, String foodHabit, String status, String medicalHistory) {
        HashMap<String, Object> petMap = new HashMap<>();
        petMap.put("image", String.valueOf(uri));
        petMap.put("pname", pname);
        petMap.put("species", species);
        petMap.put("birthday", birthday);
        petMap.put("gender", gender);
        petMap.put("breed", breed);
        petMap.put("food habit", foodHabit);
        petMap.put("status", status);
        petMap.put("medical history", medicalHistory);


        //addLocation;


        // read the index key
        String petID = mRef.push().getKey();
        // create a child with index value                                                     // write to realtime database firebase
        mRef.child(petID).updateChildren(petMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            //mRef.child("Pets").push().setValue(petMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    //String petID = mRef.child("Pets").getKey();
                    //mRefUser.child("Pet").child("petid").setValue(petID);

                    RegisterActivity.updatePet(petID);

                    Toast.makeText(NewPetActivity.this, "New pet is added successfully!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    String ex = task.getException().toString();
                    Toast.makeText(NewPetActivity.this, "Error: " + ex, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }
        });

    }
}