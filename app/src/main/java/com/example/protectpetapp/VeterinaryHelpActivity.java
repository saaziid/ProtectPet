package com.example.protectpetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VeterinaryHelpActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView hospitals, shelters, medicineStores,foodShops, vaccines, petCare;
    // private TextView veterinaryHospitalsText, clinicsText, home, veterinaryHelp, uploadPost, shop, userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinary_help);

        hospitals = (ImageView) findViewById(R.id.Hospitals);
        hospitals.setOnClickListener(this);
        shelters = (ImageView) findViewById(R.id.Shelter);
        shelters.setOnClickListener(this);
        medicineStores = (ImageView) findViewById(R.id.MedicineStores);
        medicineStores.setOnClickListener(this);
        foodShops = (ImageView) findViewById(R.id.FoodShops);
        foodShops.setOnClickListener(this);
        vaccines = (ImageView) findViewById(R.id.VeterinaryHelpVaccines);
        vaccines.setOnClickListener(this);
        petCare = (ImageView) findViewById(R.id.VeterinaryHelpPetCare);
        petCare.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Hospitals:
                startActivity(new Intent(this, MapsActivity.class));
                break;
            case R.id.Shelter:
                startActivity(new Intent(this, MapsActivity.class));
                break;
            case R.id.MedicineStores:
                startActivity(new Intent(this, MapsActivity.class));
                break;
            case R.id.FoodShops:
                startActivity(new Intent(this, MapsActivity.class));
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