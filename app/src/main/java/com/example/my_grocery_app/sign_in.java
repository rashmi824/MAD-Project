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

public class sign_in extends AppCompatActivity {
    EditText etUsername1, etPassword1;
    Button btnReg1, btnSignIn1, btnBack1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        btnBack1 = findViewById(R.id.btnBack1);
        btnReg1 = findViewById(R.id.btnReg1);

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnReg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        onCreateObjects();
        btnSignIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername1.getText().toString();
                String Password = etPassword1.getText().toString();

                etUsername1.getText().clear();
                etPassword1.getText().clear();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            String username = snapshot.child("username").getValue().toString();
                            String name = snapshot.child("name").getValue().toString();
                            String email = snapshot.child("email").getValue().toString();
                            String telNo = snapshot.child("telNo").getValue().toString();
                            String password = snapshot.child("password").getValue().toString();

                            if (password.equals(Password)) {
                                Intent intent = new Intent(getApplicationContext(), profile.class);
                                intent.putExtra("username",username);
                                intent.putExtra("name",name);
                                intent.putExtra("email",email);
                                intent.putExtra("telNo",telNo);
                                intent.putExtra("password",password);
                                startActivity(intent);
                            } else {
                                Toast.makeText(sign_in.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(sign_in.this, "Username incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void back() {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }

    public void register() {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }

    public void onCreateObjects() {
        etPassword1 = findViewById(R.id.etPassword1);
        etUsername1 = findViewById(R.id.etUsername1);
        btnSignIn1 = findViewById(R.id.btnSignIn1);
    }
}