package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class splashscrn extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscrn);




        new Handler().postDelayed(() -> {
            Intent spalsh = new Intent(splashscrn.this, registration.class);
            startActivity(spalsh);
        },2000);


    }
}