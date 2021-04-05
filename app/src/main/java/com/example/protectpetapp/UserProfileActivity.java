package com.example.protectpetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener{

    // for retrieving from firebase database
    private FirebaseUser user;
    private DatabaseReference mRef;

    private String userID;

    private TextView editProfile;
    private ImageView uploadPost;

    //for showing pets
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        user = FirebaseAuth.getInstance().getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference("Users"); // we are referencing Users here so
        userID = user.getUid();

        // creating textview object
        final TextView usernameTextView = (TextView) findViewById(R.id.UserProfileUsername);
        final TextView nameTextView = (TextView) findViewById(R.id.UserProfileName);

        editProfile = (TextView) findViewById(R.id.UserProfileEditProfile);
        editProfile.setOnClickListener(this);
        uploadPost = (ImageView) findViewById(R.id.UserProfileUploadPost);
        uploadPost.setOnClickListener(this);

        //for showing pets
        recyclerView = (RecyclerView) findViewById(R.id.UserProfileRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<UsersModel> options = new FirebaseRecyclerOptions.Builder<UsersModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"), UsersModel.class)
                        .build();


        /*
        mRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userProfile = snapshot.getValue(Users.class);

                userProfile.name.toString();
                if (userProfile!=null){
                    String name = userProfile.name;
                    String username = userProfile.username;

                    //set all these text to layout
                    usernameTextView.setText(username);
                    nameTextView.setText(name);
                }
                /*for (DataSnapshot ds : snapshot.getChildren()) {
                    //Users userProfile = snapshot.getValue(Users.class);

                    String name = ds.child("name").getValue(String.class);
                    String username = ds.child("username").getValue(String.class);
                    usernameTextView.setText(username);
                    nameTextView.setText(name);
                }
                if (userProfile!=null){
                    String name = userProfile.name;
                    String username = userProfile.username;

                    //set all these text to layout
                    usernameTextView.setText(username);
                    nameTextView.setText(name);
                }

                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference hotelRef = rootRef.child("hotel");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            String address = ds.child("address").getValue(String.class);
                            String name = ds.child("name").getValue(String.class);
                            Log.d("TAG", address + " / " + name);
                        }
                    }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfileActivity.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.UserProfileEditProfile:
                startActivity(new Intent(this, EditProfileActivity.class));
                break;
            case R.id.UserProfileUploadPost:
                // NEED TO CHANGE ACTIVITY!!!!!!!!!!!!!!!!!!!!!!!!!!!
                startActivity(new Intent(this, NewPetActivity.class));
                break;
        }
    }
}