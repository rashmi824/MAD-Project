package com.example.my_grocery_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {

    Button btnReg, btnSignIn, btnBack;
    EditText txtName, txtEmail, txtTelNo, txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));
        onCreateObjects();

        txtUsername = findViewById(R.id.txtUsername);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelNo = findViewById(R.id.txtTelNo);
        txtPassword = findViewById(R.id.txtPassword);

        btnReg = findViewById(R.id.btnReg);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnBack = findViewById(R.id.btnBack);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
    }

    public void back() {
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
    }

    public void signin() {
        Intent intent = new Intent(this, sign_in.class);
        startActivity(intent);
    }

    private void onCreateObjects() {

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelNo = findViewById(R.id.txtTelNo);
        txtPassword = findViewById(R.id.txtPassword);
        btnReg = findViewById(R.id.btnReg);
    }

    public void SignUp() {


        String username = txtUsername.getText().toString();
        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String telNo = txtTelNo.getText().toString();
        String password = txtPassword.getText().toString();

        txtUsername.getText().clear();
        txtName.getText().clear();
        txtEmail.getText().clear();
        txtTelNo.getText().clear();
        txtPassword.getText().clear();

        if (username.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Name ", Toast.LENGTH_LONG);
        } else if (email.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_LONG);
        } else if (telNo.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Contact Number ", Toast.LENGTH_LONG);
        } else if (password.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Password ", Toast.LENGTH_LONG);
        } else if (username.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Username ", Toast.LENGTH_LONG);
        } else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChildren()) {
                        Toast.makeText(register.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    } else {

                        FirebaseDatabase rootNode;
                        DatabaseReference reference;
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("Users");
                        UserModel user = new UserModel(username, name, email, telNo, password);
                        reference.child(username).setValue(user);
                        Toast.makeText(register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}