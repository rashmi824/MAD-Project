package com.example.my_grocery_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnRegUsers = findViewById(R.id.btnRegUsers);
        Button btnAddPro = findViewById(R.id.btnAddPro);
        Button btnAllPro = findViewById(R.id.btnAllPro);
        Button btnDelivery = findViewById(R.id.btnDelivery);
        Button btnFeedback = findViewById(R.id.btnFeedback);
        Button btnBack3 = findViewById(R.id.btnBack3);

        btnRegUsers.setOnClickListener(this);
        btnAddPro.setOnClickListener(this);
        btnAllPro.setOnClickListener(this);
//        btnDelivery.setOnClickListener(this);
//        btnFeedback.setOnClickListener(this);
        btnBack3.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegUsers:
                Intent intent = new Intent(this, viewusers.class);
                startActivity(intent);
                break;
            case R.id.btnAddPro:
                Intent intent1 = new Intent(this, addproduct.class);
                startActivity(intent1);
                break;
            case R.id.btnAllPro:
                Intent intent2 = new Intent(this, viewpro.class);
                startActivity(intent2);
                break;
            case R.id.btnBack3:
                Intent intent5 = new Intent(this, sign_in.class);
                startActivity(intent5);
                break;
//            case R.id.btnDelivery:
//                Intent intent3 = new Intent(this, addproduct.class);
//                startActivity(intent3);
//                break;
//            case R.id.btnFeedback:
//                Intent intent4 = new Intent(this, addproduct.class);
//                startActivity(intent4);
//                break;
        }
    }
}