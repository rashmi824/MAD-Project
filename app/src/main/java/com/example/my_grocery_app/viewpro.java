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

public class viewpro extends AppCompatActivity {

    RecyclerView rv1;
    Button btnBack;
    ArrayList<ProductModel> ProductsList = new ArrayList<>();
    ArrayList<ProductModel> ProductList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpro);

        setContentView(com.example.my_grocery_app.R.layout.activity_viewpro);
        rv1 = findViewById(com.example.my_grocery_app.R.id.rv1);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        getAllProducts();
    }

    public void back() {
        Intent intent = new Intent(this, admin.class);
        startActivity(intent);
    }

    private void getAllProducts() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Products");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                collectProducts((Map<String, Object>) snapshot.getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void displayProducts() {
        ProductList2 = new ArrayList<>();
        for (int i = 0; i < ProductsList.size(); i++) {
            ProductList2.add(new ProductModel("",ProductsList.get(i).getProId(), ProductsList.get(i).getProName(), ProductsList.get(i).getProDesc(), ProductsList.get(i).getProPrice()));
            ProductAdapter adapter = new ProductAdapter(ProductList2, getApplicationContext());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true);
            rv1.setLayoutManager(layoutManager);
            rv1.setAdapter(adapter);
        }
    }

    private void collectProducts(Map<String, Object> Products) {
        for (Map.Entry<String, Object> entry : Products.entrySet()) {

            Map singleProduct = (Map) entry.getValue();
            //Get phone field and append to list
            String proId = "P001";
            String proName = singleProduct.get("proName").toString();
            String proDesc = singleProduct.get("proDesc").toString();
            System.out.println("proDesc" + proDesc);
            String proPrice = singleProduct.get("proPrice").toString();
            ProductModel tempProduct = new ProductModel("", proId, proName, proDesc, proPrice);
            ProductsList.add((tempProduct));
        }

        displayProducts();

    }
}