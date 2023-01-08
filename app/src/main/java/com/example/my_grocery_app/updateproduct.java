package com.example.my_grocery_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class updateproduct extends AppCompatActivity {

    ImageView ivAddProductImg;
    Button btnUpload, btnSelect, btnSave2, btnBack5;
    EditText etProName, etProId, etProDesc, etProPrice;
    Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateproduct);

        etProId = findViewById(R.id.etProId);
        etProName = findViewById(R.id.etProName);
        etProDesc = findViewById(R.id.etProDesc);
        etProPrice = findViewById(R.id.etProPrice);

        btnSave2 = findViewById(R.id.btnSave2);
        btnSelect = findViewById(R.id.btnSelect);
        btnBack5 = findViewById(R.id.btnBack5);
        btnUpload = findViewById(R.id.btnUpload);

        btnBack5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        showAllUserData();
    }
    public void back() {
        Intent intent = new Intent(this, admin.class);
        startActivity(intent);
    }

    private void showAllUserData(){
        Intent intent = getIntent();
//        etProId = intent.getStringExtra("proId");
//        etProName = intent.getStringExtra("proName");
//        etProDesc = intent.getStringExtra("proDesc");
//        etProPrice = intent.getStringExtra("proPrice");


    }

//    public void updateUserData(View view){
//        if(isNameChanged() || isPasswordChanged()){
//
//        }
//    }

//    private boolean isPasswordChanged() {
//    }
//
//    private boolean isNameChanged() {
//    }
}