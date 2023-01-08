package com.example.my_grocery_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class viewusers extends AppCompatActivity {

    Button btnBack6;

    RecyclerView rv;
    ArrayList<UserModel> usersList = new ArrayList<>();
    ArrayList<UserModel> usersList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewusers);

        setContentView(com.example.my_grocery_app.R.layout.activity_viewusers);
        rv = findViewById(com.example.my_grocery_app.R.id.rv);

        btnBack6 = findViewById(R.id.btnBack6);
        btnBack6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        getAllUsers();
    }

    public void back() {
        Intent intent = new Intent(this, admin.class);
        startActivity(intent);
    }

    private void getAllUsers(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    collectUsers((Map<String, Object>) snapshot.getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void displayUsers() {
        usersList2 = new ArrayList<>();
        for (int i = 0; i < usersList.size(); i++) {
            usersList2.add(new UserModel(usersList.get(i).getUsername(), usersList.get(i).getTelNo(), "", usersList.get(i).getEmail(), usersList.get(i).getPassword()));
//            System.out.println("User Full Name" + usersList.get(i).getName());
            UserAdapter adapter = new UserAdapter(usersList2, getApplicationContext());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true);
            rv.setLayoutManager(layoutManager);
            rv.setAdapter(adapter);
        }
    }

    private void collectUsers(Map<String,Object> Users) {
        for (Map.Entry<String, Object> entry : Users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            System.out.println("tel no" + singleUser.get("telNo"));
            //Get phone field and append to list
            String telNo=singleUser.get("telNo").toString();
            String userName=singleUser.get("username").toString();
            String  email=singleUser.get("email").toString();
            String  password=singleUser.get("password").toString();
            UserModel tempUser=new UserModel(userName,telNo,"",email,password);
            usersList.add((tempUser) );
        }

        displayUsers();

    }
}