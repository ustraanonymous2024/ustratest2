package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splashscrn extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscrn);




        new Handler().postDelayed(() -> {
            Intent spalsh = new Intent(splashscrn.this, registration.class);
            startActivity(spalsh);
            finish();
        },2000);


    }
}