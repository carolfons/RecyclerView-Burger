package com.caroline.recyclerview02.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.caroline.recyclerview02.R;
import com.caroline.recyclerview02.model.User;

import java.util.ArrayList;

public class UserViewHolder extends ViewHolderBinder<User> {

    private ArrayList<User> listBurgers;
    private TextView name;
    private TextView price;
    private TextView calories;
    private LinearLayout linearLayoutContent;
    private TextView vegetarian;
    private ItemListener listener;


    public interface ItemListener{
        void onItemClick(User user);
    }

    public UserViewHolder(RecyclerView parent){
        super(parent, R.layout.adapter_lista);

        name = itemView.findViewById(R.id.tvName);
        price = itemView.findViewById(R.id.tvPrice);
        calories = itemView.findViewById(R.id.tvCalories);
        linearLayoutContent = itemView.findViewById(R.id.ll_content);
        vegetarian = itemView.findViewById(R.id.tv_vegeterian_label);

    }

    @Override
    public void onBindViewHolder(User model, int position) {
        name.setText(model.getName());
        price.setText(model.getPrice());
        calories.setText(model.getCalories());
        linearLayoutContent.setOnClickListener(view -> {
            listener.onItemClick(model);
        });

        if(model.isVegetarian()){
            linearLayoutContent.setBackgroundResource(R.color.green);
            vegetarian.setVisibility(View.VISIBLE);
        }

    }
}
