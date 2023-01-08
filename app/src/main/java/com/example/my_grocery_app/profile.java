package com.example.my_grocery_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class profile extends AppCompatActivity {

    ImageView ivProfile;
    EditText profile_name, profile_email, profile_phone, profile_username;
    Button btnUpdate, btnNext, btnBack2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));
        ivProfile = findViewById(R.id.ivProfile);
        profile_name = findViewById(R.id.profile_name);
        profile_email = findViewById(R.id.profile_email);
        profile_phone = findViewById(R.id.profile_phone);
        profile_username = findViewById(R.id.profile_username);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnNext = findViewById(R.id.btnNext);
        btnBack2 = findViewById(R.id.btnBack2);
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("telNo");
        String username = getIntent().getStringExtra("username");
        String password=getIntent().getStringExtra("password");

        profile_name.setText(name);
        profile_email.setText(email);
        profile_phone.setText(phone);
        profile_username.setText(username);

//        btnNext.setOnClickListener(this);
//        btnBack2.setOnClickListener(this);

        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

    }

    public void back() {
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
    }
}