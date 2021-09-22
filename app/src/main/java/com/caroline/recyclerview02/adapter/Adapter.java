package com.caroline.recyclerview02.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.caroline.recyclerview02.R;
import com.caroline.recyclerview02.model.User;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<User> listBurgers;
    private ItemListener listener;

    public Adapter(ArrayList<User> users, ItemListener listener) {
        this.listBurgers = users;
        this.listener = listener;

    }

    public interface ItemListener{
        void onItemClick(User user);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = listBurgers.get(position);
        holder.name.setText(user.getName());
        holder.price.setText(user.getPrice());
        holder.calories.setText(user.getCalories());
        holder.linearLayoutContent.setOnClickListener(view -> {
            listener.onItemClick(user);
        });

        if(user.isVegetarian()){
            holder.linearLayoutContent.setBackgroundResource(R.color.green);
            holder.vegetarian.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return listBurgers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView calories;
        LinearLayout linearLayoutContent;
        TextView vegetarian;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            price = itemView.findViewById(R.id.tvPrice);
            calories = itemView.findViewById(R.id.tvCalories);
            linearLayoutContent = itemView.findViewById(R.id.ll_content);
            vegetarian = itemView.findViewById(R.id.tv_vegeterian_label);
        }
    }
}
