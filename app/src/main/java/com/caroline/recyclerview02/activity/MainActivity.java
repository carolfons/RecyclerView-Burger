package com.caroline.recyclerview02.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.caroline.recyclerview02.R;
import com.caroline.recyclerview02.adapter.Adapter;
import com.caroline.recyclerview02.adapter.RecyclerViewAdapter;
import com.caroline.recyclerview02.adapter.UserViewHolder;
import com.caroline.recyclerview02.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnInteractionListener{

    private final int USER_DETAIL_ACTIVITY=1;
    public static final String BURGER_DETAIL = "BURGER_DETAIL";
    private RecyclerView recyclerView;
    private final List<User> listHamburguer = new ArrayList<>();
    private RecyclerViewAdapter<User> adapter;
    private ArrayList<User> users;
    private Toolbar toolbar;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);
        btn = findViewById(R.id.btn_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.purple_700));
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        setupRecyclerView();
    }

    private void setupRecyclerView(){
        createBurger();
        adapter = new RecyclerViewAdapter<>(UserViewHolder.class,users,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayout.VERTICAL));

    }

    private void goToBurgerDetailActivity(User user){
        Intent intent = new Intent(this,UserDetailActivity.class);
        intent.putExtra(BURGER_DETAIL, user);
        startActivity(intent);
    }
    private void createBurger(){
        users =  new ArrayList<>();
        users.add(new User("Whopper", "$30", "680 calorias",false));
        users.add(new User("Whopper Duplo", "$33", "1000 calorias",false));
        users.add(new User("Whopper Rodeio", "$35", "960 calorias",false));
        users.add(new User("Whopper Furioso", "$34", "840 calorias",false));
        users.add(new User("Whopper Barbecue Bacon", "$35", "850 calorias",false));
        users.add(new User("Stacker Duplo Bacon", "$35", "1575 calorias",false));
        users.add(new User("MegaStacker Cheddar 4.0", "$45", "1920 calorias",false));
        users.add(new User("MegaStacker Atômico 2.0", "$38", "1180 calorias",false));
        users.add(new User("MegaStacker Insano 4.0", "$40", "1653 calorias",false));
        users.add(new User("Whopper de Plantas", "$40", "540 calorias",true));
        users.add(new User("Chicken Crisp", "$32", "610 calorias",false));
        users.add(new User("Veggie Burger", "$42", "400 calorias",true));

    }

    @Override
    public <M> void onItemClicked(M item) {

        if(item instanceof User){
            User userItem = (User) item;
            Intent intent = new Intent(this,UserDetailActivity.class);
            Intent intentLogin = new Intent(this,UserLogin.class);
            if(!userItem.isVegetarian()) {
                Bundle bundle = new Bundle();
                bundle.putString("name", userItem.getName());
                bundle.putString("price", userItem.getPrice());
                bundle.putString("calories", userItem.getCalories());

                intent.putExtras(bundle);
                startActivity(intent);
                //startActivityForResult(intent, USER_DETAIL_ACTIVITY);
            }
            else{
                Bundle bundle = new Bundle();
                bundle.putString("name", userItem.getName());
                bundle.putString("price", userItem.getPrice());
                bundle.putString("calories", userItem.getCalories());

                intentLogin.putExtras(bundle);
                startActivity(intentLogin);
                //startActivityForResult(intentLogin, USER_DETAIL_ACTIVITY);

            }
        }

    }
}