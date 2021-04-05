package com.example.protectpetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VeterinaryHelpActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView veterinaryHospitals, clinics, medicineStores, vets, vaccines, petCare;
    // private TextView veterinaryHospitalsText, clinicsText, home, veterinaryHelp, uploadPost, shop, userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinary_help);

        veterinaryHospitals = (ImageView) findViewById(R.id.VeterinaryHelpHospitals);
        veterinaryHospitals.setOnClickListener(this);
        clinics = (ImageView) findViewById(R.id.VeterinaryHelpClinics);
        clinics.setOnClickListener(this);
        medicineStores = (ImageView) findViewById(R.id.VeterinaryHelpMedicineStores);
        medicineStores.setOnClickListener(this);
        vets = (ImageView) findViewById(R.id.VeterinaryHelpVets);
        vets.setOnClickListener(this);
        vaccines = (ImageView) findViewById(R.id.VeterinaryHelpVaccines);
        vaccines.setOnClickListener(this);
        petCare = (ImageView) findViewById(R.id.VeterinaryHelpPetCare);
        petCare.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.VeterinaryHelpHospitals:
                //startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.VeterinaryHelpClinics:
                //startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.VeterinaryHelpMedicineStores:
                startActivity(new Intent(this, VeterinaryHelpActivity.class));
                break;
            case R.id.VeterinaryHelpVets:
                //startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.VeterinaryHelpVaccines:
                //startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.VeterinaryHelpPetCare:
                //startActivity(new Intent(this, UserProfileActivity.class));
                break;

        }
    }
}