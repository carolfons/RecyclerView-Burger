package com.caroline.recyclerview02.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.caroline.recyclerview02.R;
import com.caroline.recyclerview02.model.User;

import java.util.Objects;

public class UserLogin extends Activity {

    private static final int USER_DETAIL_ACTIVITY = 1;
    public static final String BURGER_DETAIL = "BURGER DETAIL";
    private EditText username;
    private EditText password;
    private Button buttonLogin;
    private final String userCarol = "carol@toodoo.com.br";
    private final String passwordUser = "123";
    private String name;
    private String price;
    private String calories;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        buttonLogin = findViewById(R.id.bt_login);

        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");
        calories = getIntent().getStringExtra("calories");


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals(userCarol) && password.getText().toString().equals(passwordUser)){
                    Intent intent = new Intent(getApplicationContext(),UserDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("price", price);
                    bundle.putString("calories",calories);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    //startActivityForResult(intent, USER_DETAIL_ACTIVITY);

                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Login Incorreto", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });

    }
}
