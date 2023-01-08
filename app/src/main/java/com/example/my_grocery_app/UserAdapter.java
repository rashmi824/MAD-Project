package com.example.my_grocery_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder>{
    ArrayList<UserModel> list;
    Context context;
    UserModel model;
    public UserAdapter(ArrayList<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new viewHolder(view);
    }

    public  void onBindViewHolder(@NonNull viewHolder holder,int position)
    {
        model=list.get(position);
        if(model.getName().equals("Users"))
        {
            holder.btn_delete.setVisibility(View.INVISIBLE);
            holder.btn_edit.setVisibility(View.INVISIBLE);
        }
        else
        {

        }
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.telNo.setText(model.getTelNo());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {


        TextView name,email,telNo;
        Button btn_delete,btn_edit;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.txtName);
            email=itemView.findViewById(R.id.txtEmail);
            telNo=itemView.findViewById(R.id.txtTelNo);
            btn_delete=itemView.findViewById(R.id.btn_delete);
            btn_edit=itemView.findViewById(R.id.btn_edit);
        }
    }

}
