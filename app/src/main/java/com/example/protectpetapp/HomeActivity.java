package com.example.protectpetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    // for home page find pet access
    private ImageView menu, tick, home, veterinaryHelp, uploadPost, shop, userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menu = (ImageView) findViewById(R.id.HomeMenu);
        menu.setOnClickListener(this);
        tick = (ImageView) findViewById(R.id.HomeTick);
        tick.setOnClickListener(this);

        home = (ImageView) findViewById(R.id.HomeHome);
        home.setOnClickListener(this);
        veterinaryHelp = (ImageView) findViewById(R.id.HomeVeterinaryHelp);
        veterinaryHelp.setOnClickListener(this);
        uploadPost = (ImageView) findViewById(R.id.HomeUploadPost);
        uploadPost.setOnClickListener(this);
        shop = (ImageView) findViewById(R.id.HomeShop);
        shop.setOnClickListener(this);
        userProfile = (ImageView) findViewById(R.id.HomeUserProfile);
        userProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // HomeMenu button
            case R.id.HomeMenu:
                //startActivity(new Intent(this, RegisterActivity.class));
                break;

            // HomeUploadPost button
            case R.id.HomeVeterinaryHelp:
                startActivity(new Intent(this, VeterinaryHelpActivity.class));
                break;
            // HomeVeterinaryHelp button
            case R.id.HomeUploadPost:
                //startActivity(new Intent(this, RegisterActivity.class));
                break;
            // HomeShop button
            case R.id.HomeShop:
                //startActivity(new Intent(this, RegisterActivity.class));
                break;
            // HomeUserProfile button
            case R.id.HomeUserProfile:
                startActivity(new Intent(this, UserProfileActivity.class));
                break;

        }
    }
}