package com.example.my_grocery_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class welcome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btnReg = findViewById(R.id.btnReg);
        Button btnSign = findViewById(R.id.btnSign);

        btnReg.setOnClickListener(this);
        btnSign.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnReg:
                Intent intent = new Intent(this, register.class);
                startActivity(intent);
                break;
            case R.id.btnSign:
                Intent intent1 = new Intent(this, sign_in.class);
                startActivity(intent1);
                break;
        }
    }
}