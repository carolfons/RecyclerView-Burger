package com.caroline.recyclerview02.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;

import com.caroline.recyclerview02.R;

import org.w3c.dom.Text;

public class LocationActivity extends Activity {

    private ConstraintLayout constraintLayout;
    private Button bt_foreground;
    private Button bt_background;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setting constraint layout container
        setContentView(R.layout.activity_location);
        constraintLayout = findViewById(R.id.cl_container);

        TextView tv = createText();
         bt_foreground = createButton("Localização em 1° Plano");
         bt_background = createButton("Localização em 2° Plano");

        // adding textview and buttons to constraint layout container
        constraintLayout.addView(tv);
        constraintLayout.addView(bt_foreground);
        constraintLayout.addView(bt_background);

        //1st button pressed
        bt_foreground.setOnClickListener(view -> {
            runtimePermissionCheck();

        });

        //2nd button pressed
        bt_background.setOnClickListener(view -> {
           backgroundPermissionCheck();
        });

        //constraints
        ConstraintSet constraint = new ConstraintSet();
        constraint.clone(constraintLayout);
        constraint.connect(tv.getId(),ConstraintSet.TOP,constraintLayout.getId(),ConstraintSet.TOP);
        constraint.connect(tv.getId(),ConstraintSet.START,constraintLayout.getId(),ConstraintSet.START);
        constraint.connect(tv.getId(),ConstraintSet.END,constraintLayout.getId(),ConstraintSet.END);

        constraint.connect(bt_foreground.getId(),ConstraintSet.TOP,tv.getId(),ConstraintSet.BOTTOM);
        constraint.connect(bt_foreground.getId(),ConstraintSet.START,constraintLayout.getId(),ConstraintSet.START);
        constraint.connect(bt_foreground.getId(),ConstraintSet.END,constraintLayout.getId(),ConstraintSet.END);


        constraint.connect(bt_background.getId(),ConstraintSet.TOP,bt_foreground.getId(),ConstraintSet.BOTTOM);
        constraint.connect(bt_background.getId(),ConstraintSet.START,constraintLayout.getId(),ConstraintSet.START);
        constraint.connect(bt_background.getId(),ConstraintSet.END,constraintLayout.getId(),ConstraintSet.END);
        constraint.applyTo(constraintLayout);

    }
    @Override
    protected void onStart() {
        super.onStart();
        setVisibilitySecondButton();
    }

    @Override
    public void onRequestPermissionsResult(int request, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(request,permissions,grantResults);
        setVisibilitySecondButton();
    }

    public void runtimePermissionCheck(){
        //checking the android version if it is compatible
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                setVisibilitySecondButton();
            }else{
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION
                }, 10);
            }
        }else{
            bt_background.setVisibility(View.GONE);
        }
    }

    public void backgroundPermissionCheck(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},10);
            }else{
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 10);
            }
        }else{
            bt_background.setVisibility(View.GONE);
        }
    }

    private void setVisibilitySecondButton() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            bt_background.setVisibility(View.VISIBLE);
        }else{
            bt_background.setVisibility(View.GONE);
        }
    }

    private TextView createText(){
        TextView tv = new TextView(this);
        tv.setText("Notificação de Localização");
        tv.setId(View.generateViewId());
        tv.setTextColor(getResources().getColor(R.color.black));
        tv.setGravity(Gravity.CENTER);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.WRAP_CONTENT );
        tv.setLayoutParams(params);
        return tv;

    }

    private Button createButton(String btn_text){
        Button bt = new Button(this);
        bt.setText(btn_text);
        bt.setId(View.generateViewId());
        ConstraintLayout.LayoutParams paramsA = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.WRAP_CONTENT );
        bt.setLayoutParams(paramsA);
        return bt;
    }

}
