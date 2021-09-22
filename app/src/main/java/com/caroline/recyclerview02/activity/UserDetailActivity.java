package com.caroline.recyclerview02.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.caroline.recyclerview02.R;
import com.caroline.recyclerview02.adapter.RecyclerViewAdapter;
import com.caroline.recyclerview02.model.User;

public class UserDetailActivity extends Activity  {
    private TextView name;
    private TextView price;
    private TextView calories;
    private User user;
    private User user2;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //user = (User) getIntent().getSerializableExtra(MainActivity.BURGER_DETAIL);
        //user2 = (User) getIntent().getSerializableExtra(UserLogin.BURGER_DETAIL);
        setContentView(R.layout.activity_burger_detail);
        name = findViewById(R.id.tv_burger_name);
        price = findViewById(R.id.tv_burger_price);
        calories = findViewById(R.id.tv_burger_calories);

        name.setText(getIntent().getStringExtra("name"));
        price.setText(getIntent().getStringExtra("price"));
        calories.setText(getIntent().getStringExtra("calories"));

    }


}
