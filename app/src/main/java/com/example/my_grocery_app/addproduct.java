package com.example.my_grocery_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class addproduct extends AppCompatActivity {

    ImageView ivAddProductImg;
    Button btnUpload, btnSelect, btnSave2, btnBack4;
    EditText etProName, etProId, etProDesc, etProPrice;
    Uri filePath;

    private final int PICK_IMAGE_REQUEST = 22;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;

    ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        etProId = findViewById(R.id.etProId);
        etProName = findViewById(R.id.etProName);
        etProDesc = findViewById(R.id.etProDesc);
        etProPrice = findViewById(R.id.etProPrice);

        btnSave2 = findViewById(R.id.btnSave2);
        btnSelect = findViewById(R.id.btnSelect);
        btnBack4 = findViewById(R.id.btnBack4);
        btnUpload = findViewById(R.id.btnUpload);

        ivAddProductImg = findViewById(R.id.ivAddProductImg);

        storage = FirebaseStorage.getInstance();

        storageReference = storage.getReference();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Products");

        productModel = new ProductModel();

        btnBack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String proId = etProId.getText().toString();
                String proName = etProName.getText().toString();
                String proDesc = etProDesc.getText().toString();
                String proPrice = etProPrice.getText().toString();

                etProId.getText().clear();
                etProName.getText().clear();
                etProDesc.getText().clear();
                etProPrice.getText().clear();

                if (TextUtils.isEmpty(proId) && TextUtils.isEmpty(proName) && TextUtils.isEmpty(proDesc) && TextUtils.isEmpty(proPrice)) {

                    Toast.makeText(addproduct.this, "Please add data.", Toast.LENGTH_SHORT).show();
                } else {

                    addDatatoFirebase(proId, proName, proDesc, proPrice);
                }
            }
        });
    }

    public void back() {
        Intent intent = new Intent(this, viewpro.class);
        startActivity(intent);
    }

    private void SelectImage() {
//
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            filePath = data.getData();
            try {

                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                ivAddProductImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        if (filePath != null) {

            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(addproduct.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            progressDialog.dismiss();
                            Toast
                                    .makeText(addproduct.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }
    }


    private void addDatatoFirebase(String proId, String proName, String proDesc, String proPrice) {

        productModel.setProId(proId);
        productModel.setProName(proName);
        productModel.setProDesc(proDesc);
        productModel.setProPrice(proPrice);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databaseReference.child(proId).setValue(productModel);

                Toast.makeText(addproduct.this, "Product added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(addproduct.this, "Fail to add Product " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}