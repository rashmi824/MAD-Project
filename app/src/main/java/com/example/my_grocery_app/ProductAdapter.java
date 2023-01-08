package com.example.my_grocery_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.viewHolder> {
    ArrayList<ProductModel> list;
    Context context;
    ProductModel model;

    public ProductAdapter(ArrayList<ProductModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductAdapter.viewHolder(view);
    }



    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        model = list.get(position);

        holder.txt_id.setText(model.getProId());
        holder.txt_proName.setText(model.getProName());
        holder.txt_proDesc.setText(model.getProDesc());
        holder.txt_proPrice.setText(model.getProPrice());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proName = holder.txt_proName.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Products").child(proName);
                reference.removeValue();
                Intent intent = new Intent(context.getApplicationContext(), viewpro.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);

            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proName = holder.txt_proName.getText().toString();
                String proDesc = holder.txt_proDesc.getText().toString();
                String proPrice = holder.txt_proPrice.getText().toString();

                Intent intent = new Intent(context.getApplicationContext(), updateproduct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("txt_proName",proName);
                intent.putExtra("txt_proDesc",proDesc);
                intent.putExtra("txt_proPrice",proPrice);
                context.getApplicationContext().startActivity(intent);

            }
        });

        holder.txt_id.setText(model.getProId());
        holder.txt_proName.setText(model.getProName());
        holder.txt_proDesc.setText(model.getProDesc());
        holder.txt_proPrice.setText(model.getProPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {


        TextView txt_proName, txt_proDesc, txt_proPrice, txt_id;
        ImageView btnDelete, btnEdit;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            txt_id = itemView.findViewById(R.id.txt_id);
            txt_proName = itemView.findViewById(R.id.txt_proName);
            txt_proDesc = itemView.findViewById(R.id.txt_proDesc);
            txt_proPrice = itemView.findViewById(R.id.txt_proPrice);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}

