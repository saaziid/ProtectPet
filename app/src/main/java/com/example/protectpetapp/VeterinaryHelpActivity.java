package com.example.protectpetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VeterinaryHelpActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView maphelper, mypet, vaccines, petCare;
    // private TextView veterinaryHospitalsText, clinicsText, home, veterinaryHelp, uploadPost, shop, userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinary_help);

        maphelper = (ImageView) findViewById(R.id.Maphelper);
        maphelper.setOnClickListener(this);
        mypet = (ImageView) findViewById(R.id.mypet);
        mypet.setOnClickListener(this);

        vaccines = (ImageView) findViewById(R.id.Vaccines);
        vaccines.setOnClickListener(this);
        petCare = (ImageView) findViewById(R.id.petcare);
        petCare.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Maphelper:
                startActivity(new Intent(this, MapsActivity.class));
                break;
            case R.id.petcare:
                //startActivity(new Intent(this, MapsActivity.class));
                break;
            case R.id.Vaccines:
                //startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.mypet:
                //startActivity(new Intent(this, UserProfileActivity.class));
                break;

        }
    }
}